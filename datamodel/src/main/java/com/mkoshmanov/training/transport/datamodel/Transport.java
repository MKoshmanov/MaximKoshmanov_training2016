package com.mkoshmanov.training.transport.datamodel;

public class Transport extends AbstractModel {

	private String vehicle;
	private String registrationNumber;
	private String type;
	private Long driverId;
	private Long routeId;

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

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long long1) {
		this.driverId = long1;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long long1) {
		this.routeId = long1;
	}

	@Override
	public String toString() {
		return "Transport [vehicle = " + vehicle + ", registration_number=" + registrationNumber + ", type = " + type
				+ ", routeId = " + routeId + ", getId() = " + getId() + "]";
	}

}
