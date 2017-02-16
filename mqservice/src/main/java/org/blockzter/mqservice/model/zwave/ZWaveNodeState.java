package org.blockzter.mqservice.model.zwave;

/**
 * Created by mblock2 on 2/8/17.
 */
public enum ZWaveNodeState {
	DISABLED("Disabled")
	, FALSE("false")
	, TRUE("true");

	private String value;
	ZWaveNodeState(String value) {
		this.value = value;
	}
}
