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

	@Override
	public EventDTO createDTO(EventType envType, ZWaveNode node) {
		if (node == null) return null;

		EventDTO ret = new EventDTO(node.getId());
		ret.setEventType(envType);
		ret.setSourceType(NodeType.ZWAVE);
		ret.setNodeId(node.getNodeid());
		ret.setUuid(node.getUuid());
		ret.setCurrState(node.getCurrState());
		ret.setLabel(node.getLabel());
		ret.setUnits(node.getUnits());

		return ret;
	}

//NODES=[
// ZWaveNode{id='4', revision='null', lastUpdate=Sun Jul 23 20:34:07 EDT 2017, nodeid=4, commandclass=38, instance=1, cmdidx=0,
// 			 currState='98', label='Level', units='', uuid='b827eb501c51-0x16a1eda-4', manufacturer='null', manufacturerid=null,
// 			 product='null', producttype=null, productid=null, type='null', name='null', loc='null',
// 			 ZWaveValue=ZWaveValue{mClassId=38, mGenre='user', mHelp='', mIndex=0, mInstance=1, mIsPolled=false,
// 					mLabel='Level', mMax=255, mMin=0, mNodeId=4, mReadOnly=false, mType='byte', mUnits='', mValue='98',
// 					mValueId='4-38-1-0', mWriteOnly=false}},
// ZWaveNode{id='7', revision='null', lastUpdate=Sun Jul 23 17:27:00 EDT 2017, nodeid=7, commandclass=37, instance=1, cmdidx=0,
// 			 currState='true', label='Switch', units='', uuid='b827eb501c51-0x16a1eda-7', manufacturer='null', manufacturerid=null,
// 			 product='null', producttype=null, productid=null, type='null', name='null', loc='null',
// 			 ZWaveValue=ZWaveValue{mClassId=37, mGenre='user', mHelp='', mIndex=0, mInstance=1, mIsPolled=false,
// 					mLabel='Switch', mMax=0, mMin=0, mNodeId=7, mReadOnly=false, mType='bool', mUnits='', mValue='true',
// 					mValueId='7-37-1-0', mWriteOnly=false}}]

}
