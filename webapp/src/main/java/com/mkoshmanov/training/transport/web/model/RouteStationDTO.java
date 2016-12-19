package com.mkoshmanov.training.transport.web.model;

public class RouteStationDTO {
	
	private Long id;
	private Long stopId;
	private Long transportId;
	private Integer sequence;
	private Long timetableId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStopId() {
		return stopId;
	}
	public void setStopId(Long stopId) {
		this.stopId = stopId;
	}
	public Long getTransportId() {
		return transportId;
	}
	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Long getTimetableId() {
		return timetableId;
	}
	public void setTimetableId(Long timetableId) {
		this.timetableId = timetableId;
	}
}
