package com.mkoshmanov.training.transport.datamodel;

public class TransportStop extends AbstractModel {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TransportStop [name=" + name + ", id=" + id + "]";
	}
}
