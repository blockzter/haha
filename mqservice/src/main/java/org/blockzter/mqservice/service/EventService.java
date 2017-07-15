package org.blockzter.mqservice.service;

import org.blockzter.mqservice.model.EventType;
import org.blockzter.mqservice.model.dto.EventDTO;
import org.blockzter.mqservice.model.dto.NodeDTO;

/**
 * Created by blockm on 7/9/17.
 */
public interface EventService {
	EventDTO createDTO(EventType envType, NodeDTO node);
	void publish(String topic, EventDTO eventDTO);
}
