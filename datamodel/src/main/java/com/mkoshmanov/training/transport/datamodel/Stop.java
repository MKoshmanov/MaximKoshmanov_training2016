package com.mkoshmanov.training.transport.datamodel;

import java.util.HashSet;
import java.util.Set;

public class Stop extends AbstractModel {

	private String name;
	private Set<Transport> transports = new HashSet<Transport>();
	private Set<RouteStation> routeStations = new HashSet<RouteStation>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Transport> getTransport() {
		return transports;
	}

	public void setTransport(Set<Transport> transports) {
		this.transports = transports;
	}

	public void addTransport(Transport transport) {
		if (transports == null) {
			transports = new HashSet<Transport>();
		}
		transports.add(transport);
	}

	public Set<RouteStation> getRouteStation() {
		return routeStations;
	}

	public void setRouteStation(Set<RouteStation> routeStations) {
		this.routeStations = routeStations;
	}

	public void addRouteStation(RouteStation route) {
		if (routeStations == null) {
			routeStations = new HashSet<RouteStation>();
		}
		routeStations.add(route);
	}

	@Override
	public String toString() {
		return "Stop [name=" + name + ", id=" + id + "]";
	}
}
