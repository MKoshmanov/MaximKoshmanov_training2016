package com.mkoshmanov.training.transport.datamodel;



import java.util.Date;

import com.mkoshmanov.training.transport.datamodel.utils.Table;

@Table(name = "driver")
public class Driver extends AbstractModel {

	private String firstName;
	private String lastName;
	private Date birthday;
	private String licenseCategory;

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
		return "Driver [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", licenceCategory=" + licenseCategory + ", id=" + id + "]";
	}
}