package org.blockzter.zmon.mqtt;

import org.blockzter.zmon.mqtt.client.ZWaveClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mblock2 on 8/19/16.
 */
public class MonitorSubs {
	private static Logger LOGGER = LoggerFactory.getLogger(MonitorSubs.class);
	private String brokerUrl = "tcp://192.168.1.42:1883";
	private String userId;
	private String password;
	private String clientId = "MON";


	public MonitorSubs(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public static void main(String[] args) {
		MonitorSubs mon = new MonitorSubs(args[0], args[1]);

		mon.startMonitoring();
	}

	private void startMonitoring() {
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
