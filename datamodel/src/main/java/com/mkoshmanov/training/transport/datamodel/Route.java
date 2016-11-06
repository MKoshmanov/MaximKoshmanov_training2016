package com.mkoshmanov.training.transport.datamodel;

public class Route extends AbstractModel {
	
	private Integer number;
	private Integer transportId;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	@Override
	public String toString() {
		return "Route [number = " + number + ", transport id =" + transportId + ", getId() = " + getId() + "]";
	}

}