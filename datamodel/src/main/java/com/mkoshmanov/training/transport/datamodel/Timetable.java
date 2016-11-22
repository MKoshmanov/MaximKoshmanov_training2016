package com.mkoshmanov.training.transport.datamodel;

import java.sql.Time;

public class Timetable extends AbstractModel {

	private Integer publicTransportStopId;
	private Integer routeId;
	private String vehicle;
	private Time arriveTime;

	public Integer getPublicTransportStopId() {
		return publicTransportStopId;
	}

	public void setPublicTransportStopId(Integer publicTransportStopId) {
		this.publicTransportStopId = publicTransportStopId;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public Time getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Time arriveTime) {
		this.arriveTime = arriveTime;
	}

	@Override
	public String toString() {
		return "Timetable [publicTransportStopId=" + publicTransportStopId + ", routeId=" + routeId + ", vehicle="
				+ vehicle + ", arriveTime=" + arriveTime + ", getId()=" + getId() + "]";
	}

}
