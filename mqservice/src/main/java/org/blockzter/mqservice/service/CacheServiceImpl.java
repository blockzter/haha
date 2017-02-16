package org.blockzter.mqservice.service;

import org.apache.commons.beanutils.BeanUtils;
import org.blockzter.mqservice.model.dto.NodeDTO;
import org.blockzter.mqservice.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mblock2 on 2/5/17.
 */
public class CacheServiceImpl implements CacheService {
	private static Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);
	private List<NodeDTO> nodes;
	private static CacheServiceImpl instance;

	public static CacheServiceImpl getInstance() {
		if (instance == null) {
			instance = new CacheServiceImpl();
		}

		if (instance.nodes == null) instance.nodes = new ArrayList<>();
		return instance;
	}

	private CacheServiceImpl() {
//		if (nodes == null) nodes = new ArrayList<>();
	}

	@Override
	public void clear() {
		nodes.clear();
	}

	@Override
	public void addNode(NodeDTO node) {
		nodes.add(node);
	}

	@Override
	public NodeDTO removeNode(Integer id) {
		NodeDTO ret = getNode(id);
		LOGGER.info("remove({})={}", id, ret);
		if (ret != null) {
			nodes.remove(ret);
		}
		return ret;
	}

	@Override
	public NodeDTO removeNode(NodeDTO node) {
		if (node == null || node.getId() == null) return null;

		return removeNode(node.getId());
	}

	@Override
	public NodeDTO getNode(Integer id) {
		if (id != null) {
			LOGGER.info("id={} nodes={}", id, nodes);
			for (NodeDTO node : nodes) {
				if (node.getId().equals(id)) return node;
			}
		}
		return null;
	}

	@Override
	public NodeDTO getNode(NodeDTO node) {
		if (node == null || node.getId() == null) return null;

		return getNode(node.getId());
	}

	@Override
	public NodeDTO updateNode(NodeDTO node) {
		if (node == null || node.getId() == null) return null;

		NodeDTO ret = getNode(node.getId());
		if (ret != null) {
			try {
				BeanUtils.copyProperties(ret, node);
			} catch (IllegalAccessException | InvocationTargetException e) {
				LOGGER.error("Failed to copy node properties", e);
				ret = null;
			}
		}
		return ret;
	}

	@Override
	public NodeDTO addUpdateNode(NodeDTO node) {
		if (node == null || node.getId() == null) return null;
		NodeDTO ret = getNode(node.getId());
		if (ret == null) {
			addNode(node);
			ret = getNode(node.getId());
		} else {
			ret = updateNode(node);
		}
		return ret;
	}

	@Override
	public void save(String fileName) {
		boolean wrote = AppUtils.saveCache(fileName, nodes);
	}

	@Override
	public void load(String fileName) {
		clear();
		nodes = AppUtils.loadCache(fileName);
	}

}
