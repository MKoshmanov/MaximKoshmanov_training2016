package com.mkoshmanov.training.transport.datamodel;

import java.sql.Time;

import com.mkoshmanov.training.transport.datamodel.utils.Table;

@Table(name = "timetable")
public class Timetable extends AbstractModel {

	private Integer transportStopId;
	private Integer routeId;
	private Time arrivalTime;

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
		return "Timetable [transportStopId=" + transportStopId + ", routeId=" + routeId + ", arrivalTime=" + arrivalTime
				+ ", getId()=" + getId() + "]";
	}

}
