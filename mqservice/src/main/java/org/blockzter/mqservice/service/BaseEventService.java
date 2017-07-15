package org.blockzter.mqservice.service;

import org.blockzter.mqservice.model.dto.EventDTO;
import org.blockzter.mqservice.utils.AppUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by blockm on 7/12/17.
 */
public class BaseEventService {
	private static Logger LOGGER = LoggerFactory.getLogger(BaseEventService.class);
	private MqttClient client;

	public BaseEventService(MqttClient client) {
		this.client = client;
	}

	public void publish(String topic, EventDTO eventDTO) {
		if (eventDTO != null) {
			MqttMessage msg = new MqttMessage(AppUtils.eventDTOtoJson(eventDTO).getBytes());
			try {
				client.publish(topic, msg);
			} catch(MqttException e) {
				LOGGER.error("Failed to publish {}/{}", topic, eventDTO, e);
			}
		}
	}

}
