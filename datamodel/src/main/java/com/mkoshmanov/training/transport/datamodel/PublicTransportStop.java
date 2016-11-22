package com.mkoshmanov.training.transport.datamodel;

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
