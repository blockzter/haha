package org.blockzter.mqservice.model.dto;

import org.blockzter.mqservice.model.EventType;
import org.blockzter.mqservice.model.NodeType;

/**
 * Created by blockm on 7/9/17.
 */
public class EventDTO extends BaseDTO {
	private EventType eventType;
	private NodeType sourceType;
	private Integer deviceType;	// TODO map to ...
	private Integer nodeId;
	private String currState;
	private String label;
	private String units;
	private String uuid;

	public EventDTO(Integer id) {
		super(id);
	}

	@Override
	public String toString() {
		return "EventDTO{" +
				"eventType=" + eventType +
				", sourceType=" + sourceType +
				", deviceType=" + deviceType +
				", nodeId=" + nodeId +
				", currState='" + currState + '\'' +
				", label='" + label + '\'' +
				", units='" + units + '\'' +
				", uuid='" + uuid + '\'' +
				'}';
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public NodeType getSourceType() {
		return sourceType;
	}

	public void setSourceType(NodeType sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getCurrState() {
		return currState;
	}

	public void setCurrState(String currState) {
		this.currState = currState;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
