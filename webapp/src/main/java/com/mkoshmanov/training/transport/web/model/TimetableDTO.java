package com.mkoshmanov.training.transport.web.model;

import java.sql.Time;

public class TimetableDTO {

	private Long id;
	private Time arrivalTime;
	
	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}	
	public Time getArrivalTime() {
		return arrivalTime;
	}	
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}	
	@Override
	public String toString() {
		return "TimetableDTO [id=" + id + ", arrivalTime=" + arrivalTime + "]";
	}	
}
