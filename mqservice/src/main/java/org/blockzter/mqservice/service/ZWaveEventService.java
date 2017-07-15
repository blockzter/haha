package org.blockzter.mqservice.service;

import org.blockzter.mqservice.model.EventType;
import org.blockzter.mqservice.model.NodeType;
import org.blockzter.mqservice.model.dto.EventDTO;
import org.blockzter.mqservice.model.dto.NodeDTO;
import org.blockzter.mqservice.model.zwave.ZWaveNode;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by blockm on 7/9/17.
 */
public class ZWaveEventService extends BaseEventService implements EventService {
	private static Logger LOGGER = LoggerFactory.getLogger(ZWaveEventService.class);

	public ZWaveEventService(MqttClient client) {
		super(client);
	}

	@Override
	public EventDTO createDTO(EventType envType, NodeDTO node) {
		if (node == null) return null;

		EventDTO ret = new EventDTO(node.getId());
		ret.setEventType(envType);
		ret.setSourceType(NodeType.ZWAVE);
		if (node.getZwaveNode() != null && !node.getZwaveNode().isEmpty()) {
			ZWaveNode n = node.getZwaveNode().get(0);
			ret.setNodeId(n.getNodeid());
			ret.setUuid(n.getUuid());
			ret.setCurrState(n.getCurrState());
			ret.setLabel(n.getLabel());
			ret.setUnits(n.getUnits());
		}

		return ret;
	}

}
