package com.mkoshmanov.training.transport.web.model;

public class TransportDTO {

	private Long id;
	private String vehicleType;
	private Integer routeNumber;
	private String routeName;
	
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
		return "TransportDTO [id=" + id + ", vehicleType=" + vehicleType + ", routeNumber=" + routeNumber
				+ ", routeName=" + routeName + "]";
	}	
}
