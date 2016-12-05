package com.mkoshmanov.training.transport.web.model;

public class TransportModel {

	private Long id;
	private String vehicleType;
	private Long driverId;
	private Long routeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		return "TransportModel [id=" + id + ", vehicleType=" + vehicleType + ", driverId=" + driverId + ", routeId="
				+ routeId + "]";
	}
}
