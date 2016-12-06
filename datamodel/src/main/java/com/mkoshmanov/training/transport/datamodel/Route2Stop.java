package com.mkoshmanov.training.transport.datamodel;

public class Route2Stop extends AbstractModel {

	private Integer transportStopId;
	private Integer routeId;
	private Integer sequence;

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
		return "Route2Stop [transportStopId=" + transportStopId + ", routeId=" + routeId + ", sequence=" + sequence
				+ "]";
	}

}
