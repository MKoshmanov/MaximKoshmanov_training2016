package com.mkoshmanov.training.transport.datamodel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Driver extends AbstractModel {

	private String firstName;
	private String lastName;
	private Date birthday;
	private String licenseCategory;
	private Set<Transport> transports = new HashSet<Transport>();
		
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

	public String getLicenseCategory() {
		return licenseCategory;
	}

	public void setLicenseCategory(String licenceCategory) {
		this.licenseCategory = licenceCategory;
	}
	
	public Set<Transport> getTransport() {
		return transports;
	}

	public void setTransport(Set<Transport> transports) {
		this.transports = transports;
	}
	
	public void addTransport(Transport transport){
		if (transports == null) {
            transports = new HashSet<Transport>();
        }
        transports.add(transport);
	}

	@Override
	public String toString() {
		return "Driver [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", licenceCategory=" + licenseCategory + ", id=" + id + "]";
	}
}