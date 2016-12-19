package com.mkoshmanov.training.transport.web.model;

import java.sql.Date;

public class DriverDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private Date birthday;
	private Long transportId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getTransportId() {
		return transportId;
	}

	public void setTransportId(Long id) {
		this.transportId = id;
	}

	@Override
	public String toString() {
		return "DriverDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", transportId=" + transportId + "]";
	}
}
