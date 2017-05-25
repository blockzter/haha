package org.blockzter.mqservice.service;

import org.blockzter.mqservice.model.dto.NodeDTO;
import org.blockzter.mqservice.model.zwave.ZWaveNode;
import org.redisson.api.RType;

import java.util.Date;
import java.util.List;

/**
 * Created by mblock2 on 3/11/17.
 */
public interface RepositoryService {
	void save(NodeDTO node);
	List<ZWaveNode> getNodes(Date from, Date to);
	List<ZWaveNode> getTodays();
	void clear(RType type, String name);
	void clearAll();

}
