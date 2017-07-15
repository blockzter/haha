package org.blockzter.mqservice.model;

/**
 * Created by blockm on 7/9/17.
 * Various event types...
 */
public enum EventType {
	UPDATE(1);


	private Integer id;

	EventType(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
