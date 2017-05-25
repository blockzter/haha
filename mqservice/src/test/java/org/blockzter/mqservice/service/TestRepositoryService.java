package org.blockzter.mqservice.service;

import org.blockzter.mqservice.model.NodeType;
import org.blockzter.mqservice.model.dto.NodeDTO;
import org.blockzter.mqservice.model.gen.DBRepository;
import org.blockzter.mqservice.model.zwave.ZWaveNode;
import org.junit.Before;
import org.junit.Test;
import org.redisson.api.RType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by mblock2 on 4/8/17.
 */
public class TestRepositoryService {
	private static Logger LOGGER = LoggerFactory.getLogger(TestRepositoryService.class);

	private static String DBURL="zwpi:6379";
	private static String DBNAME = "zwave_events";

	private RepositoryService repositoryService;
	private DBRepository dbRepo;

	@Before
	public void init() {
		dbRepo = new DBRepository();
		dbRepo.setUrl(DBURL);
		dbRepo.setName(DBNAME);
		repositoryService = new RepositoryServiceImpl(dbRepo);
	}

	@Test
	public void testSave() throws Exception {
		assertThat(repositoryService).isNotNull();
		NodeDTO node = mkNode(1, mkZWaveNode(1, 1, 1, "Fred"), mkZWaveNode(2, 1, 1, "Larry"));
		repositoryService.save(node);
		node = mkNode(1, mkZWaveNode(1, 1, 1, "Fred"), mkZWaveNode(2, 1, 1, "Larry"));
		repositoryService.save(node);

		LOGGER.info("SAVE:node={}", node);
		List<ZWaveNode> zWaveNodes = repositoryService.getTodays();
		LOGGER.info("GOT:zwaves={}", zWaveNodes);
	}

	@Test
	public void testClear() throws Exception {
		assertThat(repositoryService).isNotNull();
		repositoryService.clearAll();

	}

	private NodeDTO mkNode(Integer id, ZWaveNode...zWaveNodes) {
		NodeDTO ret = new NodeDTO(id);
		ret.setNodeType(NodeType.ZWAVE);
		for(ZWaveNode node : zWaveNodes) {
			ret.addUpdateZwaveNode(node);
		}

		return ret;
	}

	private ZWaveNode mkZWaveNode(Integer nodeid, Integer commandClass, Integer instance, String name) {
		ZWaveNode ret = new ZWaveNode(nodeid, commandClass, instance);
		ret.setName(name);

		return ret;
	}
}
