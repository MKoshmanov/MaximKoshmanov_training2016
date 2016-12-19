package com.mkoshmanov.training.transport.datamodel;

import java.sql.Time;

public class Timetable extends AbstractModel {

	private Time arrivalTime;

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Override
	public String toString() {
		return "Timetable [arrivalTime=" + arrivalTime + ", id=" + id + "]";
	}
}
