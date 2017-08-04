package org.blockzter.mqservice.model;

/**
 * Created by mblock2 on 2/3/17.
 */
public enum NodeType {
	ZWAVE(1)
		, ZIGBEE(2)
	;

	private Integer id;

	NodeType(Integer id) {
		this.id = id;
	}

	public Integer getId() { return id; }
}
