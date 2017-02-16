package org.blockzter.mqservice.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.blockzter.mqservice.exceptions.PropertyNotFoundException;
import org.blockzter.mqservice.model.dto.NodeDTO;
import org.blockzter.mqservice.model.gen.MQServiceConfig;
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
		Gson gson = new Gson();
		MQServiceConfig mqServiceConfig = null;	//new MQServiceConfig();
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get(fileName)), Charset.forName("UTF-8"));
			LOGGER.info("CONTENTS={}", contents);
			mqServiceConfig = gson.fromJson(contents, MQServiceConfig.class);
			LOGGER.info("mqServiceConfig={}", mqServiceConfig);
		} catch(FileNotFoundException e) {
			LOGGER.error("Cannot find file {}", fileName, e);
		} catch(IOException e) {
			LOGGER.error("Failed reading file {}", fileName, e);
		} catch(JsonSyntaxException e) {
			LOGGER.error("Bad JSon format in file {}", fileName, e);
		}

		return mqServiceConfig;
	}

	public static List<NodeDTO> loadCache(String fileName) {
		Gson gson = new Gson();
		List<NodeDTO> nodes = null;

		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get(fileName)), Charset.forName("UTF-8"));
			LOGGER.info("CONTENTS={}", contents);
			nodes = gson.fromJson(contents, new TypeToken<List<NodeDTO>>(){}.getType());
			LOGGER.info("nodes={}", nodes);
		} catch(FileNotFoundException e) {
			LOGGER.error("Cannot find file {}", fileName, e);
		} catch(IOException e) {
			LOGGER.error("Failed reading file {}", fileName, e);
		} catch(JsonSyntaxException e) {
			LOGGER.error("Bad JSon format in file {}", fileName, e);
		}

		return nodes;
	}

	public static boolean saveCache(String fileName, List<NodeDTO> nodes) {
		Gson gson = new Gson();
		String contents;
		boolean wrote = true;

		try {
			contents = gson.toJson(nodes, new TypeToken<List<NodeDTO>>(){}.getType());
			LOGGER.info("CONTENTS={}", contents);
			Files.write(Paths.get(fileName), contents.getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		} catch(IOException e) {
			LOGGER.error("Cannot save file {}", fileName);
			wrote = false;
		}

		return wrote;
	}
}
