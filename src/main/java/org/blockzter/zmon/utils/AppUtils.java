package org.blockzter.zmon.utils;

import org.apache.commons.lang3.StringUtils;
import org.blockzter.zmon.exceptions.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
}
