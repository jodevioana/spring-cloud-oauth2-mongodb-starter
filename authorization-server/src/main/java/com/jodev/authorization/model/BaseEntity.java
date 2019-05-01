package com.jodev.authorization.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Long version = 1l;

	@CreatedDate
	protected LocalDateTime createdOn;

	@LastModifiedDate
	protected LocalDateTime updatedOn;

}
