package com.mkoshmanov.training.transport.daodb.customentity;

import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.datamodel.Station;
import com.mkoshmanov.training.transport.datamodel.Stop;

public class StopAndRoute {
	
	private Route route;
	private Station station;
	private Stop stop;
	
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public Stop getStop() {
		return stop;
	}
	public void setStop(Stop stop) {
		this.stop = stop;
	}
}
