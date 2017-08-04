package org.blockzter.haha.dal.model.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;
import org.blockzter.haha.dal.model.Device;
import org.blockzter.haha.dal.model.Floor;
import org.blockzter.haha.dal.model.FloorRoom;
import org.blockzter.haha.dal.model.RoomDevice;

/**
 * Class _Room was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Room extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ROOM_ID_PK_COLUMN = "room_id";

    public static final Property<String> NAME = Property.create("name", String.class);
    public static final Property<List<Device>> DEVICES = Property.create("devices", List.class);
    public static final Property<List<Floor>> FLOOR = Property.create("floor", List.class);
    public static final Property<RoomDevice> RM_RM_DEVICE = Property.create("rm_rm_device", RoomDevice.class);
    public static final Property<FloorRoom> ROOM_FLOOR = Property.create("room_floor", FloorRoom.class);

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    @SuppressWarnings("unchecked")
    public List<Device> getDevices() {
        return (List<Device>)readProperty("devices");
    }


    @SuppressWarnings("unchecked")
    public List<Floor> getFloor() {
        return (List<Floor>)readProperty("floor");
    }


    public void setRm_rm_device(RoomDevice rm_rm_device) {
        setToOneTarget("rm_rm_device", rm_rm_device, true);
    }

    public RoomDevice getRm_rm_device() {
        return (RoomDevice)readProperty("rm_rm_device");
    }


    public void setRoom_floor(FloorRoom room_floor) {
        setToOneTarget("room_floor", room_floor, true);
    }

    public FloorRoom getRoom_floor() {
        return (FloorRoom)readProperty("room_floor");
    }


}