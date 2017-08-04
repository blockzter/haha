package org.blockzter.mqservice.utils;

import org.apache.commons.lang3.StringUtils;
import org.blockzter.haha.dal.model.Device;
import org.blockzter.haha.dal.model.DeviceType;
import org.blockzter.haha.dal.service.DBService;
import org.blockzter.mqservice.model.dto.EventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ModelUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(ModelUtils.class);

	public static Device toDevice(EventDTO eventDTO, DBService dbService) {
		List<DeviceType> deviceTypes = dbService.getDeviceTypes();
//		DeviceType dt = dbService.getDeviceType(eventDTO.getNodeType()); // TODO - add nodeType to eventDTO...switch, dimmer, etc.

		Device device = dbService.getMyContext().newObject(Device.class);
		if (StringUtils.isNotEmpty(eventDTO.getLabel()))		device.setName(eventDTO.getLabel());
		if (StringUtils.isNotEmpty(eventDTO.getUuid()))			device.setUuid(eventDTO.getUuid());
		if (StringUtils.isNotEmpty(eventDTO.getCurrState()))	device.setStatus(eventDTO.getCurrState());
		device.setDevice_type(deviceTypes.get(0));		// TODO fix me...
		device.setType(eventDTO.getSourceType().getId());
//		device.setDevice_type(dt);

//		device.setType();

		return device;
	}

	public static void updateDevice(Device device, EventDTO eventDTO, DBService dbService) {
		if (device.getUuid().equals(eventDTO.getUuid())) {
			if (StringUtils.isNotEmpty(eventDTO.getLabel()))		device.setName(eventDTO.getLabel());
//			if (StringUtils.isNotEmpty(eventDTO.getUuid()))			device.setUuid(eventDTO.getUuid());
			if (StringUtils.isNotEmpty(eventDTO.getCurrState()))	device.setStatus(eventDTO.getCurrState());
		}
	}
}
