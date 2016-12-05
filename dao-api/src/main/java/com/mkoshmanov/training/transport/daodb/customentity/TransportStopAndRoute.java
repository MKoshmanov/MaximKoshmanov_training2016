package com.mkoshmanov.training.transport.daodb.customentity;

import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.datamodel.Route2Stop;
import com.mkoshmanov.training.transport.datamodel.TransportStop;

public class TransportStopAndRoute {

	private Route route;
	private Route2Stop route2Stop;
	private TransportStop transportStop;

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Route2Stop getRoute2Stop() {
		return route2Stop;
	}

	public void setRoute2Stop(Route2Stop route2Stop) {
		this.route2Stop = route2Stop;
	}

	public TransportStop getTransportStop() {
		return transportStop;
	}

	public void setTransportStop(TransportStop transportStop) {
		this.transportStop = transportStop;
	}
}
