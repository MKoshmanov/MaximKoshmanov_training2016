package com.mkoshmanov.training.transport.datamodel;

import com.mkoshmanov.training.transport.datamodel.utils.Table;

@Table(name = "public_transport_stop")
public class PublicTransportStop extends AbstractModel {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PublicTransportStop [name=" + name + ", getId()=" + getId() + "]";
	}

}
