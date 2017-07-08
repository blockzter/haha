package org.blockzter.mqservice.client;

import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;

/**
 * Created by blockm on 6/4/17.
 */
public abstract interface ClientNodeCallback extends MqttCallbackExtended {
	void run(String...subTopics);
}
