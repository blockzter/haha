package org.blockzter.haha.dal.service;

import org.blockzter.haha.dal.model.Device;
import org.blockzter.haha.dal.model.DeviceType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by blockm on 7/17/17.
 */
public class TestDBService {
	private static Logger LOGGER = LoggerFactory.getLogger(TestDBService.class);
	private static final String CONTEXT = "cayenne-haha.xml";
	@Test
	public void testGetDevices() throws Exception {
		DBService dbService = new DBServiceImpl(CONTEXT);
		assertThat(dbService).isNotNull();

		List<Device> devices = dbService.getDevices();
		assertThat(devices).as("List of Devices").isEmpty();
		LOGGER.info("DEVICES={}", devices);
	}

	@Test
	public void testGetDevicesType() throws Exception {
		DBService dbService = new DBServiceImpl(CONTEXT);
		assertThat(dbService).isNotNull();

		List<DeviceType> types = dbService.getDeviceTypes();
		assertThat(types).as("List of Device Types").isNotEmpty();
		LOGGER.info("DEVICES={}", types);

	}

	@Test
	public void testGetDeviceTypeById() throws Exception {
		DBService dbService = new DBServiceImpl(CONTEXT);
		assertThat(dbService).isNotNull();

		DeviceType dt = dbService.getDeviceType(0);
		LOGGER.info("GOT={}", dt);
		assertThat(dt).as("Device Type 0").isNotNull();
	}
}
