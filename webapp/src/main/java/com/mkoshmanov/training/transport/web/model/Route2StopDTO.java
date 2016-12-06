package com.mkoshmanov.training.transport.web.model;

public class Route2StopDTO {

	private Long id;
	private Integer transportStopId;
	private Integer routeId;
	private Integer sequence;

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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "Route2StopModel [id=" + id + ", transportStopId=" + transportStopId + ", routeId=" + routeId
				+ ", sequence=" + sequence + "]";
	}
}
