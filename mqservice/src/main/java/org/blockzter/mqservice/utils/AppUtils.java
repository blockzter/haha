package org.blockzter.mqservice.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.blockzter.mqservice.exceptions.PropertyNotFoundException;
import org.blockzter.mqservice.model.NodeType;
import org.blockzter.mqservice.model.dto.EventDTO;
import org.blockzter.mqservice.model.dto.NodeDTO;
import org.blockzter.mqservice.model.gen.MQServiceConfig;
import org.blockzter.mqservice.model.zwave.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Properties;

/**
 * Created by blockm on 9/9/16.
 *
 */
public class AppUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(AppUtils.class);

	public static Properties loadProperties(String key) throws IOException, IllegalArgumentException {
		String fileUriStr = System.getProperty(key);
		if (StringUtils.isEmpty(fileUriStr)) {
			LOGGER.error("Cannot find {} in properties", key);
			throw new IllegalArgumentException("Cannot find '" + key + "' in properties");
		}
		Properties properties = new Properties();

		try (FileReader reader = new FileReader(new File( (new URL(fileUriStr)).getFile() ))) {
			properties.load(reader);
		}

		return properties;
	}

	public static String getProperty(Properties prop, String key) throws PropertyNotFoundException {
		if (prop == null || prop.isEmpty() || StringUtils.isBlank(key)) return null;

		String ret = prop.getProperty(key);
		if (StringUtils.isEmpty(ret)) throw new PropertyNotFoundException("Cannot find '" + key + "'");

		return ret;
	}

	public static MQServiceConfig loadConfig(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		MQServiceConfig mqServiceConfig = null;

		try {
			mqServiceConfig = mapper.readValue(new File(fileName), MQServiceConfig.class);
		} catch(FileNotFoundException e) {
			LOGGER.error("Cannot find file {}", fileName, e);
		} catch(IOException e) {
			LOGGER.error("Failed reading file {}", fileName, e);
		}

		return mqServiceConfig;
	}

	public static List<NodeDTO> loadCache(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<NodeDTO>> listTypeRef = new TypeReference<List<NodeDTO>>() {};

		List<NodeDTO> nodes = null;

		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get(fileName)), Charset.forName("UTF-8"));
			LOGGER.info("CONTENTS={}", contents);
			nodes = mapper.readValue(contents, listTypeRef);
			LOGGER.info("nodes={}", nodes);
		} catch(FileNotFoundException e) {
			LOGGER.error("Cannot find file {}", fileName, e);
		} catch(IOException e) {
			LOGGER.error("Failed reading file {}", fileName, e);
		}

		return nodes;
	}

	public static boolean saveCache(String fileName, List<NodeDTO> nodes) {
		ObjectMapper mapper = new ObjectMapper();
		String contents;
		boolean wrote = true;

		try {
			contents = mapper.writeValueAsString(nodes);
			LOGGER.info("CONTENTS={}", contents);
			Files.write(Paths.get(fileName), contents.getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		} catch(IOException e) {
			LOGGER.error("Cannot save file {}", fileName);
			wrote = false;
		}

		return wrote;
	}

	public static String nodeDTOToJson(NodeDTO node) {
		ObjectMapper mapper = new ObjectMapper();
		String contents = null;
		try {
			contents = mapper.writeValueAsString(node);
		} catch(JsonProcessingException e) {
			LOGGER.error("Failed to convert node={} to JSON.", node, e);
		}
		return contents;
	}
	public static String eventDTOtoJson(EventDTO dto) {
		ObjectMapper mapper = new ObjectMapper();
		String contents = null;
		try {
			contents = mapper.writeValueAsString(dto);
		} catch(JsonProcessingException e) {
			LOGGER.error("Failed to convert event={} to JSON.", dto, e);
		}
		return contents;
	}

	public static NodeDTO toNodeDTO(ZWaveValueAdded value) {
		if (value == null) return null;

		NodeDTO node = new NodeDTO(value.getNodeid());
		node.setNodeType(NodeType.ZWAVE);
		ZWaveNode znode = new ZWaveNode(value.getNodeid(), value.getCmdclass(), value.getInstance());
		znode.setNodeid(value.getNodeid());
		znode.setUuid(value.getUuid());
		znode.setLabel(value.getLabel());
		znode.setCmdidx(value.getCmdidx());
		znode.setCurrState(value.getCurrState());
		znode.setLabel(value.getLabel());
		znode.setUnits(value.getUnits());
		znode.setZWaveValue(value.getValue());

		node.addUpdateZwaveNode(znode);
		return node;
	}
	public static NodeDTO toNodeDTO(ZWaveNodeChangedValue value) {
		NodeDTO node = new NodeDTO(value.getNodeid());
		node.setNodeType(NodeType.ZWAVE);
//		node.setId(va);
		ZWaveNode znode = new ZWaveNode(value.getNodeid(), value.getCmdclass(), value.getInstance());

		znode.setNodeid(value.getNodeid());
		znode.setUuid(value.getUuid());
		znode.setLabel(value.getLabel());
		znode.setCmdidx(value.getCmdidx());
		znode.setCurrState(value.getCurrState());
		znode.setLabel(value.getLabel());
		znode.setUnits(value.getUnits());
		znode.setZWaveValue(value.getValue());

		node.addUpdateZwaveNode(znode);
		return node;
	}


	public static NodeDTO toNodeDTO(ZWaveNodeAdded nodeAdded) {
		if (nodeAdded == null) return null;

		NodeDTO node = new NodeDTO(nodeAdded.getNodeid());
		node.setNodeType(NodeType.ZWAVE);
		ZWaveNode znode = new ZWaveNode(nodeAdded.getNodeid());
		znode.setNodeid(nodeAdded.getNodeid());
		znode.setUuid(nodeAdded.getUuid());
		updateZWaveNode(znode, nodeAdded);

		node.addUpdateZwaveNode(znode);
		return node;
	}
	private static void updateZWaveNode(ZWaveNode znode, ZWaveNodeAdded nodeAdded) {
		if (nodeAdded != null && nodeAdded.getNodeInfo() != null) {
			ZWaveNodeInfo nodeInfo = nodeAdded.getNodeInfo();
			LOGGER.info("NODEINFO={}", nodeInfo);

			if (StringUtils.isNotBlank(nodeInfo.getManufacturer())) znode.setManufacturer(nodeInfo.getManufacturer());
			if (StringUtils.isNotBlank(nodeInfo.getManufacturerid())) znode.setManufacturerid(nodeInfo.getManufacturerid());
			if (StringUtils.isNotBlank(nodeInfo.getProduct())) znode.setProduct(nodeInfo.getProduct());
			if (StringUtils.isNotBlank(nodeInfo.getProductid())) znode.setProductid(nodeInfo.getProductid());
			if (StringUtils.isNotBlank(nodeInfo.getProducttype())) znode.setProducttype(nodeInfo.getProducttype());
			if (StringUtils.isNotBlank(nodeInfo.getType())) znode.setType(nodeInfo.getType());
			if (StringUtils.isNotBlank(nodeInfo.getLoc())) znode.setLoc(nodeInfo.getLoc());
			if (StringUtils.isNotBlank(nodeInfo.getName())) znode.setName(nodeInfo.getName());
		}
	}
}
