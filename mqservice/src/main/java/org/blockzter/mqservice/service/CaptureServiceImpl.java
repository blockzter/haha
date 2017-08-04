package org.blockzter.mqservice.service;

import org.blockzter.haha.dal.model.Device;
import org.blockzter.haha.dal.service.DBService;
import org.blockzter.haha.dal.service.DBServiceImpl;
import org.blockzter.mqservice.model.dto.EventDTO;
import org.blockzter.mqservice.utils.ModelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaptureServiceImpl implements CaptureService {
	private static Logger LOGGER = LoggerFactory.getLogger(CaptureServiceImpl.class);
	private DBService dbService;
	private Boolean netCapture;

	public CaptureServiceImpl(String config, Boolean netCapture) {
		this.dbService = new DBServiceImpl(config);
		this.netCapture = netCapture;
	}

	@Override
	public void capture(EventDTO dto) {
		LOGGER.info("CAPTURE: {}", dto);

		if (!netCapture) return;
		Device device = dbService.getDevice(dto.getUuid());
		if (device != null) {
			// update
			ModelUtils.updateDevice(device, dto, dbService);

		} else {
			device = ModelUtils.toDevice(dto, dbService);
			dbService.saveDevice(device);
		}
	}
}
