package com.jodev.authorization.model;

import org.springframework.data.annotation.Id;

public class BaseIdEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
