package org.blockzter.zmon.mqtt.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.blockzter.zmon.model.HexDeserializer;
import org.blockzter.zmon.model.ZWaveNodeChangedValue;
import org.blockzter.zmon.model.ZWaveNodeReady;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by blockm on 9/7/16.
 */
public class ZWaveClient implements MqttCallback{
	private static Logger LOGGER = LoggerFactory.getLogger(ZWaveClient.class);
	private String brokerUrl;
	private String userId;
	private String passwd;
	private String clientId;
	private MqttConnectOptions connOpt;
	private MqttClient client;

	public ZWaveClient(String brokerUrl, String userId, String passwd, String clientId) {
		this.brokerUrl = brokerUrl;
		this.userId = userId;
		this.passwd = passwd;
		this.clientId = clientId;
	}

	public void run(String... topics) {
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
		} catch (MqttException e) {
			LOGGER.error("Failed to connect to {}", brokerUrl, e);
			System.exit(-1);
		}

		LOGGER.info("Connected to {}", brokerUrl);

		if (topics != null) {
			MqttTopic[] myTopics = new MqttTopic[topics.length];
			int[] qoses = new int[topics.length];
			int i = 0;
			for (String topic : topics) {
				myTopics[i] = client.getTopic(topic);
				qoses[i] = 0;
			}
			try {
				client.subscribe(topics, qoses);
			} catch(MqttException e) {
				LOGGER.error("Failed to subscribe to one of the topics...", e);
			}
		}
	}


	@Override
	public void connectionLost(Throwable cause) {
		LOGGER.error("Connection to {} lost", brokerUrl, cause);
		LOGGER.error("connOpt={} client={}", connOpt, client);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		LOGGER.info("GOT topic={} message={}", topic, message);
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Long.class, new HexDeserializer());
		Gson gson = builder.create();

		if (topic.contains("ready")) {
			ZWaveNodeReady nodeReady = gson.fromJson(message.toString(), ZWaveNodeReady.class);
			LOGGER.info("nodeReady={}", nodeReady);
		} else if (topic.contains("change")) {
			ZWaveNodeChangedValue nodeChangedValue = gson.fromJson(message.toString(), ZWaveNodeChangedValue.class);
			LOGGER.info("nodeChangedValue={}", nodeChangedValue);
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		LOGGER.info("delivery complete token={}", token);
	}
}
