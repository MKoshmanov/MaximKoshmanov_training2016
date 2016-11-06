package com.mkoshmanov.training.transport.datamodel;

public class Transport extends AbstractModel  {
	
	private String vehicle;
	private String registrationNumber;
	private String type;

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Transport [vehicle = " + vehicle + ", registration number=" + registrationNumber + ", type = " + type
				+ ", getId() = " + getId() + "]";
	}

}

