package com.mkoshmanov.training.transport.datamodel;

public class Transport extends AbstractModel {

	private String vehicleType;
	private Integer routeNumber;
	private String routeName;

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(Integer routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	@Override
	public String toString() {
		return "Transport [vehicleType=" + vehicleType + ", routeNumber=" + routeNumber + ", routeName=" + routeName
				+ "]";
	}
}
