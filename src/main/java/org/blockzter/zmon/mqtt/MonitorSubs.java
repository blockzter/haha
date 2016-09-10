package org.blockzter.zmon.mqtt;

import org.blockzter.zmon.exceptions.PropertyNotFoundException;
import org.blockzter.zmon.mqtt.client.ZWaveClient;
import org.blockzter.zmon.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by mblock2 on 8/19/16.
 *
 */
public class MonitorSubs {
	private static Logger LOGGER = LoggerFactory.getLogger(MonitorSubs.class);
	private static final String CLIENT_PROP_FILENAME_KEY = "CONFIG";
	private static final String CLIENT_PROP_FILENAME = "client.properties";
	private static final String MQTT_CLIENT_ID_KEY = "mqtt.client.id";
	private static final String MQTT_BROKER_URL_KEY = "mqtt.broker.url";

	private String brokerUrl;
	private String userId;
	private String password;
	private String clientId;


	private MonitorSubs(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public static void main(String[] args) {
		MonitorSubs mon = new MonitorSubs(args[0], args[1]);

		mon.startMonitoring();
		LOGGER.info("Laters...");
	}

	private void init() {
		Properties properties = null;
		try {
			properties = AppUtils.loadProperties(CLIENT_PROP_FILENAME_KEY);
			LOGGER.info("LOAD props={}", properties);
		} catch (Exception e) {
			LOGGER.error("Failed to load properties file {}", CLIENT_PROP_FILENAME, e);
			System.exit(-1);
		}
		try {
			clientId = AppUtils.getProperty(properties, MQTT_CLIENT_ID_KEY);
			brokerUrl = AppUtils.getProperty(properties, MQTT_BROKER_URL_KEY);
		} catch(PropertyNotFoundException e) {
			LOGGER.error("init failed ", e);
			System.exit(-2);
		}
	}

	private void startMonitoring() {
		init();
		ZWaveClient client = new ZWaveClient(brokerUrl, userId, password, clientId);

		try {
			client.run("zwave: node ready", "zwave: value changed");
		} catch(Exception e) {
			LOGGER.error("run exited...", e);
		} finally {
			LOGGER.info("Finished run...");
		}
	}
}
