package com.mkoshmanov.training.transport.datamodel;

import java.io.Serializable;

public class AbstractModel implements Serializable {

	public Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}