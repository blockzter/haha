package org.blockzter.mqservice;

import org.blockzter.mqservice.client.ClientNodeCallback;
import org.blockzter.mqservice.client.ZWaveClient;
import org.blockzter.mqservice.model.gen.*;
import org.blockzter.mqservice.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mblock2 on 10/9/16.
 *
 */
public class MQService {
	private static Logger LOGGER = LoggerFactory.getLogger(MQService.class);
	private MQServiceConfig mqServiceConfig;
	private ConcurrentHashMap<String, ZWaveClient> zWaveClients;
	private ConcurrentHashMap<String, ClientNodeCallback> mqClients;

	public static void main(String[] args) {
		MQService service = new MQService();

		service.init();
		service.run();
	}

	private void init() {
		mqServiceConfig = null;

		File cwd = new File(".");
		LOGGER.info("CWD={}", cwd.getAbsolutePath());
		Path path = FileSystems.getDefault().getPath("config", "mqservice-conf.json");
		LOGGER.info("CF PATH={}", path);
		mqServiceConfig = AppUtils.loadConfig(path.toString());
		LOGGER.info("CONFIG={}", mqServiceConfig);

		zWaveClients = new ConcurrentHashMap<>();
		mqClients = new ConcurrentHashMap<>();

	}

	private void run() {
		for(Broker b : mqServiceConfig.getBrokers()) {
			List<Subscriber> subs = b.getSubscriber();

			if (subs != null && !subs.isEmpty()) {
				try {
					ClientNodeCallback client = instantiate(b);
					mqClients.put(b.getName(), client);
					List<String> topics = new ArrayList<>(subs.size());
					for (Subscriber sub : subs) {
						topics.add(sub.getTopic());
					}
					client.run(topics.toArray(new String[0]));
//					testPubs(client);


//				ZWaveClient client = new ZWaveClient(b, mqServiceConfig.getRepository());
				} catch(ClassNotFoundException e) {
					LOGGER.error("Failed to find class {} for broker {}", b.getHandler().getClient(), b.getName(), e);
					break;
				} catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
					LOGGER.error("Failed to instantiate {} for broker {}", b.getHandler().getClient(), b.getName(), e);
					break;
				}

//				zWaveClients.put(b.getName(), client);
//				List<String> topics = new ArrayList<>(subs.size());
//				for (Subscriber sub : subs) {
//					topics.add(sub.getTopic());
//				}
//
//				client.run(topics.toArray(new String[0]));
//
//				testPubs(client);

			}
		}

		// Test...
//		for(ZWaveClient client : zWaveClients.values()) {
//			LOGGER.info("client={}", client);
//			if (client != null) {
//				client.publish("switchOn", "{ 'nodeId':7 }");
//			}
//		}


		//		ZWaveClient client = zWaveClients.get(0);
//		client.publish("switchOn", "{ 'nodeId':7 }");
	}

	private void testPubs(ZWaveClient client) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			int i=0;
			while(true) {
				System.out.print(">> ");
				String line = reader.readLine();
				i++;
				if (i > 100) break;
				LOGGER.info("PUB: line={}", line);
//				LOGGER.info("PUB: topic={} msg={}", topic, msg);

				if (line.contains("end") || line.contains("quit")) break;

				String[] topicAndMsg = line.split("//");
				if (topicAndMsg.length == 2) {
					client.publish(topicAndMsg[0], topicAndMsg[1]);
				}
//				client.publish("switchOn", line);
			}

		} catch(IOException e) {
			LOGGER.error("Failed to read stdin", e);
		}
	}

	private ClientNodeCallback instantiate(Broker broker) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		ClientNodeCallback ret = null;
		Handler handler = broker.getHandler();
		DBRepository dbRepository = mqServiceConfig.getRepository();

		if (handler != null) {
			String clientClassName = handler.getClient();
			if (clientClassName != null) {
				Class<?> clientClazz = Class.forName(clientClassName);
				Constructor<?> con = clientClazz.getConstructor(broker.getClass(), dbRepository.getClass());
				Object rr = con.newInstance(broker, dbRepository);
				ret = (ClientNodeCallback)rr;
			}
		}

		return ret;
	}
}
