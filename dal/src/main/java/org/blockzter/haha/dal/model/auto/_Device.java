package org.blockzter.haha.dal.model.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;
import org.blockzter.haha.dal.model.DeviceType;
import org.blockzter.haha.dal.model.RoomDevice;

/**
 * Class _Device was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Device extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String D_ID_PK_COLUMN = "d_id";

    public static final Property<String> NAME = Property.create("name", String.class);
    public static final Property<String> STATUS = Property.create("status", String.class);
    public static final Property<Integer> TYPE = Property.create("type", Integer.class);
    public static final Property<String> UUID = Property.create("uuid", String.class);
    public static final Property<RoomDevice> DEVICE_RM_RM = Property.create("device_rm_rm", RoomDevice.class);
    public static final Property<DeviceType> DEVICE_TYPE = Property.create("device_type", DeviceType.class);

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void setStatus(String status) {
        writeProperty("status", status);
    }
    public String getStatus() {
        return (String)readProperty("status");
    }

    public void setType(int type) {
        writeProperty("type", type);
    }
    public int getType() {
        Object value = readProperty("type");
        return (value != null) ? (Integer) value : 0;
    }

    public void setUuid(String uuid) {
        writeProperty("uuid", uuid);
    }
    public String getUuid() {
        return (String)readProperty("uuid");
    }

    public void setDevice_rm_rm(RoomDevice device_rm_rm) {
        setToOneTarget("device_rm_rm", device_rm_rm, true);
    }

    public RoomDevice getDevice_rm_rm() {
        return (RoomDevice)readProperty("device_rm_rm");
    }


    public void setDevice_type(DeviceType device_type) {
        setToOneTarget("device_type", device_type, true);
    }

    public DeviceType getDevice_type() {
        return (DeviceType)readProperty("device_type");
    }


}
