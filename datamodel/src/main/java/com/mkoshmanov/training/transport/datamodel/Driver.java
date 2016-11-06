package com.mkoshmanov.training.transport.datamodel;

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
		return "Driver [first name = " + firstName + ", last name = " + lastName + ", getId()=" + getId() + "]";
	}

}