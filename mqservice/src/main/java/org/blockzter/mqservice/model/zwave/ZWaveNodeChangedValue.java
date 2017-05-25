
package org.blockzter.mqservice.model.zwave;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ZWaveNodeChangedValue {

    @JsonProperty(value = "cmdclass")
    private Integer mCmdclass;
    @JsonProperty(value = "cmdidx")
    private Integer mCmdidx;
    @JsonProperty(value = "currState")
    private String mCurrState;
    @JsonProperty(value = "instance")
    private Integer mInstance;
    @JsonProperty(value = "label")
    private String mLabel;
    @JsonProperty(value = "nodeid")
    private Integer mNodeid;
    @JsonProperty(value = "oldState")
    private String mOldState;
    @JsonProperty(value = "units")
    private String mUnits;
    @JsonProperty(value = "uuid")
    private String mUuid;
    @JsonProperty(value = "value")
    private ZWaveValue mValue;

    @Override
    public String toString() {
        return "NodeChangedValue{" +
                "mCmdclass=" + mCmdclass +
                ", mCmdidx=" + mCmdidx +
                ", mCurrState=" + mCurrState +
                ", mInstance=" + mInstance +
                ", mLabel='" + mLabel + '\'' +
                ", mNodeid=" + mNodeid +
                ", mOldState=" + mOldState +
                ", mUnits='" + mUnits + '\'' +
                ", mUuid='" + mUuid + '\'' +
                ", mValue=" + mValue +
                '}';
    }

    public Integer getCmdclass() {
        return mCmdclass;
    }

    public void setCmdclass(Integer cmdclass) {
        mCmdclass = cmdclass;
    }

    public Integer getCmdidx() {
        return mCmdidx;
    }

    public void setCmdidx(Integer cmdidx) {
        mCmdidx = cmdidx;
    }

    public String getCurrState() {
        return mCurrState;
    }

    public void setCurrState(String currState) {
        mCurrState = currState;
    }

    public Integer getInstance() {
        return mInstance;
    }

    public void setInstance(Integer instance) {
        mInstance = instance;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public Integer getNodeid() {
        return mNodeid;
    }

    public void setNodeid(Integer nodeid) {
        mNodeid = nodeid;
    }

    public String getOldState() {
        return mOldState;
    }

    public void setOldState(String oldState) {
        mOldState = oldState;
    }

    public String getUnits() {
        return mUnits;
    }

    public void setUnits(String units) {
        mUnits = units;
    }

    public String getUuid() {
        return mUuid;
    }

    public void setUuid(String uuid) {
        mUuid = uuid;
    }

    public ZWaveValue getValue() {
        return mValue;
    }

    public void setValue(ZWaveValue value) {
        mValue = value;
    }

}
