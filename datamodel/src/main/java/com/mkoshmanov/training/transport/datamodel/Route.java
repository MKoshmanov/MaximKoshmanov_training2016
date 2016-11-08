package com.mkoshmanov.training.transport.datamodel;

public class Route extends AbstractModel {
	
	private Integer number;
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Route [number = " + number + ", getId() = " + getId() + "]";
	}

}