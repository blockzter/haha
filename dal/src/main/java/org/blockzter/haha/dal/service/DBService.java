package org.blockzter.haha.dal.service;

import org.apache.cayenne.ObjectContext;
import org.blockzter.haha.dal.model.Device;
import org.blockzter.haha.dal.model.DeviceType;
import org.blockzter.haha.dal.model.Floor;
import org.blockzter.haha.dal.model.Room;

import java.util.List;

/**
 * Created by blockm on 7/17/17.
 */
public interface DBService {
	ObjectContext getMyContext();
	List<Device> getDevices();
	Device getDevice(String uuid);
	void saveDevice(Device device);

	List<DeviceType> getDeviceTypes();
	DeviceType getDeviceType(Integer id);

	List<Floor> getFloors();
	Floor getFloor(Integer id);

	List<Room> getRooms();
	Room getRoom(Integer id);

}
