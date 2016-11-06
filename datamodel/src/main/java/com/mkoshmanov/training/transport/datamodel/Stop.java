package com.mkoshmanov.training.transport.datamodel;

public class Stop extends AbstractModel {
	
	private String stopName;

	public String getStopName() {
		return stopName;
	}

	public void setStopName(String stopName) {
		this.stopName = stopName;
	}

	@Override
	public String toString() {
		return "stopName [stop name=" + stopName + ", getId()=" + getId() + "]";
	}
}
