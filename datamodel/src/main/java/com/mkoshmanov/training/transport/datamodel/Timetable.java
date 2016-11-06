package com.mkoshmanov.training.transport.datamodel;

import java.sql.Time;

public class Timetable extends AbstractModel {
	
	private Integer stationId;
	private Integer routeId;
	private Time arriveTime;

	public Time getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Time arriveTime) {
		this.arriveTime = arriveTime;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
}
