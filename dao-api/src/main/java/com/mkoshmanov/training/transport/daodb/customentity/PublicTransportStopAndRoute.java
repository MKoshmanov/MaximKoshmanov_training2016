package com.mkoshmanov.training.transport.daodb.customentity;

import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.datamodel.RouteComposition;
import com.mkoshmanov.training.transport.datamodel.PublicTransportStop;

public class PublicTransportStopAndRoute {

	private Route route;
	private RouteComposition routeComposition;
	private PublicTransportStop publicTransportStop;

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public RouteComposition getRouteComposition() {
		return routeComposition;
	}

	public void setRouteComposition(RouteComposition routeComposition) {
		this.routeComposition = routeComposition;
	}

	public PublicTransportStop getPublicTransportStop() {
		return publicTransportStop;
	}

	public void setPublicTransportStop(PublicTransportStop publicTransportStop) {
		this.publicTransportStop = publicTransportStop;
	}

}
