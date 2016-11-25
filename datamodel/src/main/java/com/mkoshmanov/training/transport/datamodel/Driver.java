package com.mkoshmanov.training.transport.datamodel;

import com.mkoshmanov.training.transport.datamodel.utils.Table;

@Table(name = "driver")
public class Driver extends AbstractModel {

	private String firstName;
	private String lastName;

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

	@Override
	public String toString() {
		return "Driver [firstName=" + firstName + ", lastName=" + lastName + ", getId()=" + getId() + "]";
	}

	
}