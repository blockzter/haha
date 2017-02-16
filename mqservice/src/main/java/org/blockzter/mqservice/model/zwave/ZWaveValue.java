
package org.blockzter.mqservice.model.zwave;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ZWaveValue {
//	Feb  8 15:23:59 raspberrypi Node-RED[8078]: payload: '{"nodeid":6,"cmdclass":37,"instance":1,"cmdidx":0,"oldState":false,"currState":true,"label":"Switch","units":"",
// 		"value":{"value_id":"6-37-1-0","node_id":6,"class_id":37,"type":"bool","genre":"user","instance":1,"index":0,"label":"Switch","units":"","help":"",
// 			"read_only":false,"write_only":false,"is_polled":false,"min":0,"max":0,"value":true},"uuid":"b827eb501c51-0x16a1eda-6"}',
//	Feb  8 15:23:59 raspberrypi Node-RED[8078]: _msgid: '2432245d.dbcddc' }

    @SerializedName("class_id")
    private Long mClassId;
    @SerializedName("genre")
    private String mGenre;
    @SerializedName("help")
    private String mHelp;
    @SerializedName("index")
    private Long mIndex;
    @SerializedName("instance")
    private Long mInstance;
    @SerializedName("is_polled")
    private Boolean mIsPolled;
    @SerializedName("label")
    private String mLabel;
    @SerializedName("max")
    private Long mMax;
    @SerializedName("min")
    private Long mMin;
    @SerializedName("node_id")
    private Long mNodeId;
    @SerializedName("read_only")
    private Boolean mReadOnly;
    @SerializedName("type")
    private String mType;
    @SerializedName("units")
    private String mUnits;
    @SerializedName("value")
    private String mValue;
    @SerializedName("value_id")
    private String mValueId;
    @SerializedName("write_only")
    private Boolean mWriteOnly;

    @Override
    public String toString() {
        return "ZWaveValue{" +
                "mClassId=" + mClassId +
                ", mGenre='" + mGenre + '\'' +
                ", mHelp='" + mHelp + '\'' +
                ", mIndex=" + mIndex +
                ", mInstance=" + mInstance +
                ", mIsPolled=" + mIsPolled +
                ", mLabel='" + mLabel + '\'' +
                ", mMax=" + mMax +
                ", mMin=" + mMin +
                ", mNodeId=" + mNodeId +
                ", mReadOnly=" + mReadOnly +
                ", mType='" + mType + '\'' +
                ", mUnits='" + mUnits + '\'' +
                ", mValue='" + mValue + '\'' +
                ", mValueId='" + mValueId + '\'' +
                ", mWriteOnly=" + mWriteOnly +
                '}';
    }

    public Long getClassId() {
        return mClassId;
    }

    public void setClassId(Long class_id) {
        mClassId = class_id;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getHelp() {
        return mHelp;
    }

    public void setHelp(String help) {
        mHelp = help;
    }

    public Long getIndex() {
        return mIndex;
    }

    public void setIndex(Long index) {
        mIndex = index;
    }

    public Long getInstance() {
        return mInstance;
    }

    public void setInstance(Long instance) {
        mInstance = instance;
    }

    public Boolean getIsPolled() {
        return mIsPolled;
    }

    public void setIsPolled(Boolean is_polled) {
        mIsPolled = is_polled;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public Long getMax() {
        return mMax;
    }

    public void setMax(Long max) {
        mMax = max;
    }

    public Long getMin() {
        return mMin;
    }

    public void setMin(Long min) {
        mMin = min;
    }

    public Long getNodeId() {
        return mNodeId;
    }

    public void setNodeId(Long node_id) {
        mNodeId = node_id;
    }

    public Boolean getReadOnly() {
        return mReadOnly;
    }

    public void setReadOnly(Boolean read_only) {
        mReadOnly = read_only;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUnits() {
        return mUnits;
    }

    public void setUnits(String units) {
        mUnits = units;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getValueId() {
        return mValueId;
    }

    public void setValueId(String value_id) {
        mValueId = value_id;
    }

    public Boolean getWriteOnly() {
        return mWriteOnly;
    }

    public void setWriteOnly(Boolean write_only) {
        mWriteOnly = write_only;
    }

}
