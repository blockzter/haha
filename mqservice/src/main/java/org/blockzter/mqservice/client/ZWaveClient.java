package org.blockzter.mqservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.blockzter.mqservice.model.EventType;
import org.blockzter.mqservice.model.db.ZWaveNodeRepository;
import org.blockzter.mqservice.model.dto.EventDTO;
import org.blockzter.mqservice.model.dto.NodeDTO;
import org.blockzter.mqservice.model.gen.Behaviour;
import org.blockzter.mqservice.model.gen.DBRepository;
import org.blockzter.mqservice.model.gen.Dal;
import org.blockzter.mqservice.model.zwave.*;
import org.blockzter.mqservice.model.gen.Broker;
import org.blockzter.mqservice.service.*;
import org.blockzter.mqservice.utils.AppUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by blockm on 9/7/16.
 */
public class ZWaveClient implements ClientNodeCallback{
	private static Logger LOGGER = LoggerFactory.getLogger(ZWaveClient.class);
	private String brokerUrl;
	private String userId;
	private String passwd;
	private String clientId;
	private MqttConnectOptions connOpt;
	private MqttClient client;
	private Broker broker;
	private CacheService cacheService;
	private ZWaveNodeRepository repo = null;
	private ObjectMapper mapper = new ObjectMapper();
	private RepositoryService repoService;
	private EventService zwaveEventService;
	private CaptureService captureService;


	public ZWaveClient(Broker broker, DBRepository repository, Dal dal, Behaviour behaviour) {
		LOGGER.info("*** new({}, {})", broker, repository);
		this.broker = broker;
		brokerUrl = broker.getConnection().getHost();
		userId = broker.getConnection().getUser();
		passwd = broker.getConnection().getPassword();
		clientId = broker.getConnection().getClientId();
		cacheService = CacheServiceImpl.getInstance();
		repoService = new RepositoryServiceImpl(repository);
		captureService = new CaptureServiceImpl(dal.getConfig(), behaviour.getNetCapture());
	}

	public void run(String... subTopics) {
		connOpt = new MqttConnectOptions();
		connOpt.setCleanSession(true);
		connOpt.setKeepAliveInterval(30);
		connOpt.setUserName(userId);
		connOpt.setPassword(passwd.toCharArray());

		try {
			LOGGER.info("Creating client for brokerUrl={} clientId={}", brokerUrl, clientId);
			client = new MqttClient(brokerUrl, clientId);
			client.setCallback(this);
			client.connect(connOpt);
			zwaveEventService = new ZWaveEventService(client);
		} catch (MqttException e) {
			LOGGER.error("Failed to connect to {}", brokerUrl, e);
			System.exit(-1);
		}

		LOGGER.info("Connected to {}", brokerUrl);


		if (subTopics != null) {
			MqttTopic[] myTopics = new MqttTopic[subTopics.length];
			int[] qoses = new int[subTopics.length];
			int i = 0;
			for (String topic : subTopics) {
				myTopics[i] = client.getTopic(topic);
				qoses[i] = 0;
			}
			try {
				client.subscribe(subTopics, qoses);
			} catch(MqttException e) {
				LOGGER.error("Failed to subscribe to one of the topics...", e);
			}
		}

//		MqttMessage msg = new MqttMessage("{topic: 'switchOn', payload: {'nodeid':2}}".getBytes());
//		try {
//			client.publish("switchOn", msg);
//		} catch(Exception e) {
//			LOGGER.error("Failed to switchOn", e);
//		}

		refreshTodays();
		List<ZWaveNode> nodes = repoService.getTodays();
		LOGGER.info("NODES={}", nodes);
	}

	private void refreshTodays() {
		LOGGER.info("Refreshing todays events...");
		List<ZWaveNode> nodes = repoService.getTodays();
		LOGGER.info("NODES={}", nodes);
		if (nodes != null) {
			for (ZWaveNode zwnode : nodes) {
				EventDTO dto = zwaveEventService.createDTO(EventType.UPDATE, zwnode);
				captureService.capture(dto);
			}
		}
	}

	public void publish(String topic, String payload) {
		LOGGER.info("PUB topic={} payload={}", topic, payload);
		MqttMessage msg = new MqttMessage(payload.getBytes());
		try {
			client.publish(topic, msg);
		} catch(MqttException e) {
			LOGGER.error("Failed to publish {}/{}", topic, payload, e);
		}
	}
	public void publish(String topic, NodeDTO payload) {
		LOGGER.info("PUB topic={} payload={}", topic, payload);
		publish(topic, AppUtils.nodeDTOToJson(payload));
	}


	@Override
	public void connectionLost(Throwable cause) {
		LOGGER.error("Connection to {} lost", brokerUrl, cause);
		LOGGER.error("connOpt={} client={}", connOpt, client);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		LOGGER.info("GOT topic={} message={}", topic, message);
		Path path = FileSystems.getDefault().getPath("config", "nodes-conf.json");

		if (topic.contains("node ready")) {
//[MQTT Call: haha_1] INFO org.blockzter.mqservice.client.ZWaveClient -
// GOT topic=zwave: node ready
// 	 message={"nodeid":4,
// 		"nodeinfo":{"manufacturer":"GE","manufacturerid":"0x0063","product":"45606 2-Way Dimmer Switch","producttype":"0x4457",
// 					"productid":"0x3230","type":"Multilevel Power Switch","name":"","loc":""},
// 		"uuid":"b827eb501c51-0x16a1eda-4"
// 	 }
			ZWaveNodeAdded nodeReady = mapper.readValue(message.toString(), ZWaveNodeAdded.class);
			NodeDTO node = AppUtils.toNodeDTO(nodeReady);
			EventDTO dto = zwaveEventService.createDTO(EventType.UPDATE, node);
			captureService.capture(dto);
//			cacheService.addUpdateNode(node);
			repoService.save(node);
			LOGGER.info("nodeReady={}", nodeReady);

		} else if (topic.contains("change")) {
//[MQTT Call: haha_1] INFO org.blockzter.mqservice.client.ZWaveClient -
// GOT topic=zwave: value changed
// 	 message={"nodeid":4,"cmdclass":38,"instance":1,"cmdidx":0,"oldState":2,"currState":98,"label":"Level","units":"",
// 			  "value":{"value_id":"4-38-1-0","node_id":4,"class_id":38,"type":"byte","genre":"user","instance":1,"index":0,"label":"Level",
// 						"units":"","help":"","read_only":false,"write_only":false,"is_polled":false,"min":0,"max":255,"value":98},
// 			  "uuid":"b827eb501c51-0x16a1eda-4"}

			ZWaveNodeChangedValue nodeChangedValue = mapper.readValue(message.toString(), ZWaveNodeChangedValue.class);
			NodeDTO node = AppUtils.toNodeDTO(nodeChangedValue);
			cacheService.addUpdateNode(node);
			repoService.save(node);
			EventDTO dto = zwaveEventService.createDTO(EventType.UPDATE, node);
			captureService.capture(dto);
			zwaveEventService.publish("updates", dto);
//			publish("updates", node);
			LOGGER.info("nodeChangedValue={}", nodeChangedValue);

		} else if (topic.contains("driver ready")) {
			ZWaveDriverReady driverReady = mapper.readValue(message.toString(), ZWaveDriverReady.class);
			cacheService.load(path.toString());
			LOGGER.info("driverReady={}", driverReady);

		} else if (topic.contains("node added")) {
			ZWaveNodeAdded nodeAdded = mapper.readValue(message.toString(), ZWaveNodeAdded.class);
			NodeDTO node = AppUtils.toNodeDTO(nodeAdded);
			cacheService.addNode(node);
			repoService.save(node);
			LOGGER.info("nodeAdded={}", nodeAdded);

		} else if (topic.contains("value added")) {
			ZWaveValueAdded valueAdded = mapper.readValue(message.toString(), ZWaveValueAdded.class);
			NodeDTO node = AppUtils.toNodeDTO(valueAdded);
			cacheService.addNode(node);
			repoService.save(node);
			LOGGER.info("valueAdded={}", valueAdded);

		} else if (topic.contains("notification")) {
			ZWaveNotification notification = mapper.readValue(message.toString(), ZWaveNotification.class);
			LOGGER.info("notification={}", notification);

		} else if (topic.contains("scan complete")) {
			String scanComplete = mapper.readValue(message.toString(), String.class);

			cacheService.save(path.toString());
			LOGGER.info("scanComplete={}", scanComplete);
		}

		LOGGER.info("SO FAR={}", repoService.getTodays());
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		LOGGER.info("delivery complete token={}", token);
	}

	@Override
	public void connectComplete(boolean reconnect, String serverURI) {
		LOGGER.info("connection complete: reconn={} serverUI={}", reconnect, serverURI);
	}

	private void handleNodeChange(MqttMessage message) {
		ZWaveNodeChangedValue nodeChangedValue = null;
		try {
			nodeChangedValue = mapper.readValue(message.toString(), ZWaveNodeChangedValue.class);
			NodeDTO node = AppUtils.toNodeDTO(nodeChangedValue);
			cacheService.addUpdateNode(node);
			LOGGER.info("nodeChangedValue={}", nodeChangedValue);
		} catch(IOException e) {
			LOGGER.error("Failed to read Node CHanged Value...", e);
		}


	}

//	private String fromString(String value) {
//		if (StringUtils.isBlank(value)) return null;
//
//		return Long.valueOf(value);
//	}


//	Jan 21 21:16:23 raspberrypi Node-RED[8019]: { topic: 'zwave: value added',
//	Jan 21 21:16:23 raspberrypi Node-RED[8019]: payload: '{
// 		"nodeid":5,"cmdclass":39,"instance":1,"cmdidx":0,"currState":"Disabled","label":"Switch All","units":"",
// 		"value":{"value_id":"5-39-1-0","node_id":5,"class_id":39,"type":"list","genre":"system","instance":1,"index":0,"label":"Switch All","units":"","help":"",
// 			"read_only":false,"write_only":false,"is_polled":false,"min":0,"max":0,"values":["Disabled","Off Enabled","On Enabled","On and Off Enabled"],"value":"Disabled"},
// 		"uuid":"b827eb501c51-0x16a1eda-5"}',
//	Jan 21 21:16:23 raspberrypi Node-RED[8019]: _msgid: 'bd012f9.f42fed' }
//
//	Jan 21 21:16:25 raspberrypi Node-RED[8019]: { topic: 'zwave: value added',
//	Jan 21 21:16:25 raspberrypi Node-RED[8019]: payload: '{
// 		"nodeid":6,"cmdclass":37,"instance":1,"cmdidx":0,"currState":false,"label":"Switch","units":"",
// 		"value":{"value_id":"6-37-1-0","node_id":6,"class_id":37,"type":"bool","genre":"user","instance":1,"index":0,"label":"Switch","units":"","help":"",
// 			"read_only":false,"write_only":false,"is_polled":false,"min":0,"max":0,"value":false},
// 		"uuid":"b827eb501c51-0x16a1eda-6"}',
//	Jan 21 21:16:25 raspberrypi Node-RED[8019]: _msgid: 'f1f21706.0e0de8' }
//	Jan 21 21:16:25 raspberrypi Node-RED[8019]: 21 Jan 21:16:25 - [info] [debug:ffbbaced.18ad2]
//
//	Jan 21 21:16:25 raspberrypi Node-RED[8019]: { topic: 'zwave: value added',
//	Jan 21 21:16:25 raspberrypi Node-RED[8019]: payload: '{
// 		"nodeid":6,"cmdclass":39,"instance":1,"cmdidx":0,"currState":"Disabled","label":"Switch All","units":"",
// 		"value":{"value_id":"6-39-1-0","node_id":6,"class_id":39,"type":"list","genre":"system","instance":1,"index":0,"label":"Switch All","units":"","help":"",
// 			"read_only":false,"write_only":false,"is_polled":false,"min":0,"max":0,"values":["Disabled","Off Enabled","On Enabled","On and Off Enabled"],"value":"Disabled"},
// 		"uuid":"b827eb501c51-0x16a1eda-6"}',
//	Jan 21 21:16:25 raspberrypi Node-RED[8019]: _msgid: '2b51e33c.d4ae1c' }

//	Feb  8 15:23:59 raspberrypi Node-RED[8078]: payload: '{"nodeid":6,"cmdclass":37,"instance":1,"cmdidx":0,"oldState":false,"currState":true,"label":"Switch","units":"",
// 		"value":{"value_id":"6-37-1-0","node_id":6,"class_id":37,"type":"bool","genre":"user","instance":1,"index":0,"label":"Switch","units":"","help":"",
// 			"read_only":false,"write_only":false,"is_polled":false,"min":0,"max":0,"value":true},"uuid":"b827eb501c51-0x16a1eda-6"}',
//	Feb  8 15:23:59 raspberrypi Node-RED[8078]: _msgid: '2432245d.dbcddc' }
//
//	Feb  8 15:23:59 raspberrypi Node-RED[8078]: { topic: 'zwave: node ready',
//	Feb  8 15:23:59 raspberrypi Node-RED[8078]: payload: '{"nodeid":6,"nodeinfo":{"manufacturer":"GE","manufacturerid":"0x0063","product":"12722 On/Off Relay Switch","producttype":"0x4952","productid":"0x3032","type":"Binary Power Switch","name":"","loc":""},"uuid":"b827eb501c51-0x16a1eda-6"}',
//	Feb  8 15:23:59 raspberrypi Node-RED[8078]: _msgid: '4b8f723c.b4708c' }

}
