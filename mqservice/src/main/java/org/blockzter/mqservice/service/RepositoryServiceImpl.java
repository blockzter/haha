package org.blockzter.mqservice.service;

import org.blockzter.mqservice.model.db.ZWaveNodeRepository;
import org.blockzter.mqservice.model.dto.NodeDTO;
import org.blockzter.mqservice.model.gen.DBRepository;
import org.blockzter.mqservice.model.zwave.ZWaveNode;
import org.blockzter.mqservice.utils.BeanCopy;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mblock2 on 3/11/17.
 */
public class RepositoryServiceImpl implements RepositoryService {
	private static Logger LOGGER = LoggerFactory.getLogger(RepositoryServiceImpl.class);
	private DBRepository repository;
	private ZWaveNodeRepository repo = null;
	private RedissonClient client = null;
	private static BeanCopy beanCopy;

	public RepositoryServiceImpl(DBRepository repository) {
		this.repository = repository;
		init();
	}
	private void init() {
		LOGGER.info("*** init");
		Config config = new Config();
		config.useSingleServer().setAddress(repository.getUrl());
//		config.useClusterServers().addNodeAddress(repository.getUrl());
		client = Redisson.create(config);
		beanCopy = new BeanCopy(false, false);
	}

	@Override
	public void save(NodeDTO node) {
		LOGGER.info("SAVE:node={}", node);

		for(String k :  client.getKeys().getKeys()) {
			LOGGER.info("CK={}", k);
		}

//		RList<ZWaveNode> zWaveNodeRList = client.getList(repository.getName());
//		LOGGER.info("RLIST={}", zWaveNodeRList);
//
		if (node != null && node.getZwaveNode() != null) {
			Date now = new Date();
			LOGGER.info("GET MAP...");
//			RMap<Integer, ZWaveNode> map = client.getMap(repository.getName()+ "_map");
			RMapCache<Integer, List<ZWaveNode>> map = client.getMapCache(repository.getName()+ "_map");

			LOGGER.info("GET MAP={}", map.size());
//			RSet<ZWaveNode> zWaveNodeRSet = client.getSet(repository.getName());

			if (map.get(node.getId()) == null) {
				List<ZWaveNode> nodes = new ArrayList<>();
				map.put(node.getId(), nodes, repository.getTtl(), TimeUnit.HOURS);
			}

			for (ZWaveNode n : node.getZwaveNode()) {
				boolean foundIt = false;
				for(ZWaveNode repoNode : map.get(node.getId())) {
					if (n.equals(repoNode)) {
						// update
						LOGGER.info("UPDATE existing node {}/{}", node.getNodeType(), node.getId());
						foundIt = true;
						try {
							beanCopy.copyProperties(repoNode, n);
						} catch(IllegalAccessException | InvocationTargetException e) {
							LOGGER.error("Failed to update node {}/{}", node.getNodeType(), node.getId(), e);
							break;
						}
					}
				}

				if (!foundIt) {
					// add
					LOGGER.info("ADD node {}/{} -- {}", node.getNodeType(), node.getId(), n);
					List<ZWaveNode> nodes = map.get(node.getId());
					nodes.add(n);
				}
			}
			for(ZWaveNode zWaveNode : node.getZwaveNode()) {
				zWaveNode.setLastUpdate(now);
				LOGGER.info("ADD={}", zWaveNode);
//				map.put(node.getId(), zWaveNode, repository.getTtl(), TimeUnit.HOURS);
			}
			map.put(node.getId(), node.getZwaveNode(), repository.getTtl(), TimeUnit.HOURS);

//			zWaveNodeRSet.addAll(node.getZwaveNode());
//
//			for (ZWaveNode zWaveNode : node.getZwaveNode()) {
//				zWaveNode.setLastUpdate(now);
//				zWaveNodeRSet.add(zWaveNode);
//			}
			LOGGER.info("SAVED={}", client.getMapCache(repository.getName()+ "_map"));
		}

		LOGGER.info("Trying OBJ...");
		RObject tmp = null;
		try {
			tmp = client.getMapCache(repository.getName() + "_map");
		} catch(Exception e) {
			LOGGER.error("Failed to get OBJ...", e);
		}
		LOGGER.info("OBJ={}", tmp);
	}

	@Override
	public List<ZWaveNode> getNodes(Date from, Date to) {
		List<ZWaveNode> all = client.getList(repository.getName());
		List<ZWaveNode> ret = new ArrayList<>();

		LOGGER.info("GETNODES(from={}, to={}): ALL={}", from, to, all);
		if (all != null) {
			for(ZWaveNode node : all) {
				LOGGER.info("NODE={}", node);
				if (node.getLastUpdate().after(from) && node.getLastUpdate().before(to)) {
					ret.add(node);
					LOGGER.info("*** ADD");
				}
			}
		}

		LOGGER.info("GETNODES:ret={}", ret);

		for(String k :  client.getKeys().getKeys()) {
			LOGGER.info("CK={}", k);
		}


//		LOGGER.info("Trying OBJ...");
//		RObject tmp = null;
//		try {
//			tmp = client.getMapCache(repository.getName() + "_map");
//		} catch(Exception e) {
//			LOGGER.error("Failed to get OBJ...", e);
//		}
//		LOGGER.info("OBJ={}", tmp);


		return ret;
	}

	@Override
	public List<ZWaveNode> getTodays() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date now = cal.getTime();

		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date later = cal.getTime();

		return getNodes(now, later);
	}

	@Override
	public void clear(RType type, String name) {
		if (type == null || name == null || name.isEmpty()) return;
		switch(type) {
			case LIST :
				RList<Object> list = client.getList(name);
				list.clear();
				break;
			case MAP:
				RMap<Object, Object> map = client.getMap(name);
				map.clear();
				break;
			case SET:
				RSet<Object> set = client.getSet(name);
				set.clear();
				break;
			default:
				LOGGER.error("Unsupported type {} for {}", type, name);
		}
	}

	public void clearAll() {
		clear(RType.LIST, repository.getName());
		clear(RType.MAP, repository.getName() + "_map");
	}


//	private void init() {
//		try {
//			LOGGER.info("Setting up repository connection...");
//			HttpClient httpClient = new StdHttpClient.Builder().url(repository.getUrl()).build();
//			CouchDbInstance couchDbInstance = new StdCouchDbInstance(httpClient);
//			CouchDbConnector db = new StdCouchDbConnector(repository.getName(), couchDbInstance);
//			repo = new ZWaveNodeRepository(ZWaveNode.class, db);
//
//		} catch (MalformedURLException e) {
//			LOGGER.error("Failed to establish DB connection {}", repository.getUrl(), e);
//		}
//		LOGGER.info("Repository {}/{} now available...", repository.getUrl(), repository.getName());
//	}
//
//	@Override
//	public void save(NodeDTO node) {
//		if (node != null && node.getZwaveNode() != null) {
//			Date now = new Date();
//			for(ZWaveNode zWaveNode : node.getZwaveNode()) {
//				zWaveNode.setLastUpdate(now);
//				repo.add(zWaveNode);
//			}
//		}
//	}
//
//	@Override
//	public List<ZWaveNode> getNodes(Date from, Date to) {
//		if (from == null || to == null) return null;
//
//		// TODO handle from/to lastUpdate fields...
//		return repo.getAll();
//	}
//
//	@Override
//	public List<ZWaveNode> getTodays() {
//		return repo.zwaveEventsForToday();
//	}
//
//
}
