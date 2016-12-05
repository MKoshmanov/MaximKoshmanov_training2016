package com.mkoshmanov.training.transport.web.model;

import java.sql.Time;

public class TimetableModel {

	private Long id;
	private Integer transportStopId;
	private Integer routeId;
	private Time arrivalTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTransportStopId() {
		return transportStopId;
	}

	public void setTransportStopId(Integer transportStopId) {
		this.transportStopId = transportStopId;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Override
	public String toString() {
		return "TimetableModel [id=" + id + ", transportStopId=" + transportStopId + ", routeId=" + routeId
				+ ", arrivalTime=" + arrivalTime + "]";
	}
}
