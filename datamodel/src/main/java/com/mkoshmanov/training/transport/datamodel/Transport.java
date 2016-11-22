package com.mkoshmanov.training.transport.datamodel;

public class Transport extends AbstractModel {

	private String type;
	private String registrationNumber;
	private Long driverId;
	private Long routeId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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
		return "Transport [type=" + type + ", registrationNumber=" + registrationNumber + ", driverId=" + driverId
				+ ", routeId=" + routeId + ", getId()=" + getId() + "]";
	}
	
}
