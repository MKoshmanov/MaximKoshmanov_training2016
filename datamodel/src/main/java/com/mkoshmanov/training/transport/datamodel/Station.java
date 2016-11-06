package com.mkoshmanov.training.transport.datamodel;

public class Station extends AbstractModel {

	private Integer stopId;
	private Integer sequence;
	private Integer routeId;

	public Integer getStopId() {
		return stopId;
	}

	public void setStopId(Integer stopId) {
		this.stopId = stopId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	@Override
	public String toString() {
		return "Station [stoo id = " + stopId + ", sequense = " + sequence + ", route id = " + routeId + ", getId() = "
				+ getId() + "]";
	}

}


