package org.blockzter.mqservice.service;

import org.blockzter.mqservice.model.dto.EventDTO;

public interface CaptureService {
	void capture(EventDTO dto);
}
