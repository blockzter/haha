package org.blockzter.mqservice.model.dto;

import org.apache.commons.beanutils.BeanUtils;
import org.blockzter.mqservice.model.NodeType;
import org.blockzter.mqservice.model.zwave.ZWaveNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mblock2 on 2/3/17.
 */
public class NodeDTO extends BaseDTO {
	private static Logger LOGGER = LoggerFactory.getLogger(NodeDTO.class);
	private NodeType nodeType;
	private List<ZWaveNode> zwaveNode;	// BY: nodeId, commandClass, command index
	// add other supported types here:
	// private ZigbeeNode zigbeeNode;
	//


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NodeDTO nodeDTO = (NodeDTO) o;

		return nodeType == nodeDTO.nodeType;
	}

	@Override
	public int hashCode() {
		return nodeType != null ? nodeType.hashCode() : 0;
	}

	public NodeDTO(Integer id) {
		super(id);
		zwaveNode = new ArrayList<>();
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	public List<ZWaveNode> getZwaveNode() {
		return zwaveNode;
	}

	public void setZwaveNode(List<ZWaveNode> zwaveNode) {
		this.zwaveNode = zwaveNode;
	}

	public void addUpdateZwaveNode(ZWaveNode zWaveNode) {
		boolean done = false;
		for (ZWaveNode z : this.zwaveNode) {
			if (z.equals(zWaveNode)) {
				// update existing...
				done = true;
				try {
					BeanUtils.copyProperties(z, zWaveNode);
				} catch (IllegalAccessException | InvocationTargetException e) {
					LOGGER.error("Failed to update zwave node {}: {}", z.getNodeid(), z.getName(), e);
				}

				if (!done) {
					zwaveNode.add(zWaveNode);
				}
			}
		}
	}
}
