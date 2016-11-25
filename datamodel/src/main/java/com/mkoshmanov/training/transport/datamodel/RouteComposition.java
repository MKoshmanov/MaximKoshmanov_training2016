package com.mkoshmanov.training.transport.datamodel;

import com.mkoshmanov.training.transport.datamodel.utils.Table;

@Table(name = "route_composition")
public class RouteComposition extends AbstractModel {

	private Integer publicTransportStopId;
	private Integer routeId;
	private Integer sequence;   

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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "RouteComposition [publicTransportStopId=" + publicTransportStopId + ", routeId=" + routeId
				+ ", sequence=" + sequence + ", getId()=" + getId() + "]";
	}
	
}
