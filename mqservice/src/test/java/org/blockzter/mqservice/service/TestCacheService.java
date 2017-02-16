package org.blockzter.mqservice.service;

import org.blockzter.mqservice.model.NodeType;
import org.blockzter.mqservice.model.dto.NodeDTO;
import org.blockzter.mqservice.model.zwave.ZWaveNode;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by mblock2 on 2/5/17.
 */
public class TestCacheService {
	private static Logger LOGGER = LoggerFactory.getLogger(TestCacheService.class);
	private CacheService service = null;

	@Before
	public void init() {
		LOGGER.info("*** init");
		service = CacheServiceImpl.getInstance();
		service.clear();
	}

	@Test
	public void testServiceAddGet() throws Exception {
		LOGGER.info("**** testServiceAddGet");
		assertThat(service).isNotNull();

		NodeDTO node1 = new NodeDTO(1);
		node1.setNodeType(NodeType.ZWAVE);
		node1.addUpdateZwaveNode(mkZWNode(node1.getId(), 37, 1, "Test Node #1a"));
		node1.addUpdateZwaveNode(mkZWNode(node1.getId(), 39, 1, "Test Node #1b"));
		service.addNode(node1);

		NodeDTO gotNode = service.getNode(1);
		assertThat(gotNode.getId()).as("check Node ID").isEqualTo(1);

		NodeDTO node2 = new NodeDTO(2);
		node2.setNodeType(NodeType.ZWAVE);
		node2.addUpdateZwaveNode(mkZWNode(node2.getId(), 37, 1,"Test Node #2"));
		service.addNode(node2);
		assertThat(service.getNode(2).getId()).as("check node id 2").isEqualTo(2);

		NodeDTO node3 = service.removeNode(1);
		assertThat(node3.getId()).as("check Node ID").isEqualTo(1);
		assertThat(service.getNode(1)).isNull();

		service.clear();
		assertThat(service.getNode(2)).isNull();

		service.addNode(node1);
		node2.setId(1);
		gotNode = service.updateNode(node2);
		assertThat(gotNode).as("update node").isNotNull().isEqualTo(node2);

		LOGGER.info("**** testServiceAddGet");
	}

	@Test
	public void testServiceSaveLoad() throws Exception {
		LOGGER.info("**** testServiceSaveLoad");
		assertThat(service).isNotNull();

		NodeDTO node1 = new NodeDTO(1);
		node1.setNodeType(NodeType.ZWAVE);
		node1.addUpdateZwaveNode(mkZWNode(node1.getId(), 37, 1, "Test Node #1a"));
		node1.addUpdateZwaveNode(mkZWNode(node1.getId(), 39, 1, "Test Node #1b"));

//		NodeDTO node1b = new NodeDTO(1);
//		node1b.setNodeType(NodeType.ZWAVE);
//		node1b.addUpdateZwaveNode(mkZWNode(node1b.getId(), 39, 1, "Test Node #1b"));
//
		NodeDTO node2 = new NodeDTO(2);
		node2.setNodeType(NodeType.ZWAVE);
		node2.addUpdateZwaveNode(mkZWNode(node2.getId(), 37, 1,"Test Node #2"));

		service.addNode(node1);
		service.addNode(node2);

		String out = "testNodes.json";
		service.save(out);
		service.load(out);

		NodeDTO loaded1 = service.getNode(1);
		NodeDTO loaded2 = service.getNode(2);

		assertThat(node1).as("comparing loaded with saved for node 1").isEqualTo(loaded1);
		assertThat(node2).as("comparing loaded with saved for node 2").isEqualTo(loaded2);
		assertThat(loaded1).as("comparing loaded1 with loaded2").isEqualTo(loaded2);

		LOGGER.info("**** testServiceSaveLoad");
	}

	@Test
	public void testServiceLoadNonExistentFile() throws Exception {
		LOGGER.info("**** testServiceLoadNonExistentFile");
		assertThat(service).isNotNull();

		service.load("testLoad1.json");
		LOGGER.info("**** testServiceLoadNonExistentFile");
	}

	private ZWaveNode mkZWNode(Integer id, Integer commandClass, Integer instance, String label) {
		ZWaveNode node = new ZWaveNode(id, commandClass, instance);
		node.setLabel(label);

		return node;
	}
}
