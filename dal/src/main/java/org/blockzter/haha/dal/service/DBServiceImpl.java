package org.blockzter.haha.dal.service;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.query.SelectById;
import org.blockzter.haha.dal.model.Device;
import org.blockzter.haha.dal.model.DeviceType;
import org.blockzter.haha.dal.model.Floor;
import org.blockzter.haha.dal.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by blockm on 7/17/17.
 */
public class DBServiceImpl extends DBServiceBase implements DBService {
	private static Logger LOGGER = LoggerFactory.getLogger(DBServiceImpl.class);
	private ObjectContext context;
	public DBServiceImpl(String config) {
		super(config);
		context = getContext();
	}

	@Override
	public ObjectContext getMyContext() {
		LOGGER.info("CONTEXT={}", this.context); return this.context;
	}

	@Override
	public List<Device> getDevices() {
		return ObjectSelect.query(Device.class).select(context);
	}

	@Override
	public Device getDevice( String uuid) {
		return ObjectSelect.query(Device.class).where(Device.UUID.eq(uuid)).selectFirst(context);
	}

	// TODO or do we take a DTO of some type and save it...
	@Override
	public void saveDevice(Device device) {
		device.getObjectContext().commitChanges();
	}

	@Override
	public List<DeviceType> getDeviceTypes() {
		return ObjectSelect.query(DeviceType.class).select(context);
	}

	@Override
	public DeviceType getDeviceType(Integer id) {
		return SelectById.query(DeviceType.class, id).selectOne(context);
	}

	@Override
	public List<Floor> getFloors() {
		return ObjectSelect.query(Floor.class).select(context);
	}

	@Override
	public Floor getFloor(Integer id) {
		return SelectById.query(Floor.class, id).selectOne(context);
	}

	@Override
	public List<Room> getRooms() {
		return ObjectSelect.query(Room.class).select(context);
	}

	@Override
	public Room getRoom(Integer id) {
		return SelectById.query(Room.class, id).selectOne(context);
	}
}
