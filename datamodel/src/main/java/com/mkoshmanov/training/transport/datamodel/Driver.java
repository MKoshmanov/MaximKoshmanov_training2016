package com.mkoshmanov.training.transport.datamodel;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Driver extends AbstractModel {

	private String firstName;
	private String lastName;
	private Date birthday;
	public Transport transport;

	private Set<Transport> transports = new HashSet<Transport>();
	private Set<RouteStation> routes = new HashSet<RouteStation>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public Set<Transport> getTransports() {
		return transports;
	}

	public void setTransports(Set<Transport> transports) {
		this.transports = transports;
	}

	public void addTransport(Transport transport) {
		if (transports == null) {
			transports = new HashSet<Transport>();
		}
		transports.add(transport);
	}

	public Set<RouteStation> getRoute() {
		return routes;
	}

	public void setRoute(Set<RouteStation> routes) {
		this.routes = routes;
	}

	public void addRoute(RouteStation route) {
		if (routes == null) {
			routes = new HashSet<RouteStation>();
		}
		routes.add(route);
	}

	@Override
	public String toString() {
		return "Driver [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday + ", transport="
				+ transport + ", id=" + id + "]";
	}
}