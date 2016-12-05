package com.mkoshmanov.training.transport.web.model;

public class TransportStop {

	private Long id;
	private String name;
	private String district;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return "TransportStop [id=" + id + ", name=" + name + ", district=" + district + "]";
	}

}
