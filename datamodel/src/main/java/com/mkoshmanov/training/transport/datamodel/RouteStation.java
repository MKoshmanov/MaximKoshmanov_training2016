package com.mkoshmanov.training.transport.datamodel;

import java.util.HashSet;
import java.util.Set;

public class RouteStation extends AbstractModel {

	private Stop stop;
	private Long transportId;
	private Integer sequence;
	private Timetable timetable;
	private Set<Stop> stops = new HashSet<Stop>();
	private Set<Timetable> timetables = new HashSet<Timetable>();

	public Set<Stop> getStops() {
		return stops;
	}

	public void setStops(Set<Stop> stops) {
		this.stops = stops;
	}

	public Set<Timetable> getTimetables() {
		return timetables;
	}

	public void setTimetables(Set<Timetable> timetables) {
		this.timetables = timetables;
	}

	public Stop getStop() {
		return stop;
	}

	public void setStop(Stop stop) {
		this.stop = stop;
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

	public Timetable getTimetable() {
		return timetable;
	}

	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
	}

	public void addStop(Stop stop) {
		if (stops == null) {
			stops = new HashSet<Stop>();
		}
		stops.add(stop);
	}

	public void addTimetable(Timetable timetable) {
		if (timetables == null) {
			timetables = new HashSet<Timetable>();
		}
		timetables.add(timetable);
	}

	@Override
	public String toString() {
		return "RouteStation [stop=" + stop + ", transportId=" + transportId + ", sequence=" + sequence + ", timetable="
				+ timetable + ", id=" + id + "]";
	}
}