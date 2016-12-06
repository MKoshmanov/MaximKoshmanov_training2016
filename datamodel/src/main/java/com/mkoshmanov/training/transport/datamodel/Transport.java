package com.mkoshmanov.training.transport.datamodel;

public class Transport extends AbstractModel {

	private String vehicleType;
	private Long driverId;
	private Long routeId;

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	@Override
	public String toString() {
		return "Transport [vehicleType=" + vehicleType + ", driverId=" + driverId + ", routeId=" + routeId
				+ ", getId()=" + getId() + "]";
	}

}
