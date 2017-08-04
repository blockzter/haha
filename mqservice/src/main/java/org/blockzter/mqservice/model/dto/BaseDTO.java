package org.blockzter.mqservice.model.dto;

/**
 * Created by mblock2 on 2/5/17.
 */
public class BaseDTO {
	private Integer id;

	public BaseDTO(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BaseDTO{" +
				"id=" + id +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
