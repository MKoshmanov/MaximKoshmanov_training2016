package com.mkoshmanov.training.transport.web.model;

import java.sql.Date;

public class DriverDTO {
	private Long id;
    private String firstName;
	private String lastName;
	private Date birthday;
	private String licenseCategory;

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

	public String getLicenceCategory() {
		return licenseCategory;
	}

	public void setLicenceCategory(String licenceCategory) {
		this.licenseCategory = licenceCategory;
	}

	@Override
	public String toString() {
		return "DriverModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday="
				+ birthday + ", licenseCategory=" + licenseCategory + "]";
	}
}
