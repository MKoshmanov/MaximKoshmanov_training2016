package com.mkoshmanov.training.transport.datamodel;

import com.mkoshmanov.training.transport.datamodel.utils.Table;

@Table(name = "route")
public class Route extends AbstractModel {

	private Integer number;
	private String name;
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Route [number=" + number + ", name=" + name + ", getId()=" + getId() + "]";
	}
}