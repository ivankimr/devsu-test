package com.devsu.customers.service.domain.model;

import java.util.UUID;

public class Person {
	private UUID personId;
    private String personName;
    private short genderId;
    private short age;
    private short identificationTypeId;
    private String identificationNumber;
    private String address;
    private String phone;
    
    public UUID getPersonId() {
		return personId;
	}
    
	public void setPersonId(UUID personId) {
		this.personId = personId;
	}
	
	public String getPersonName() {
		return personName;
	}
	
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public short getGenderId() {
		return genderId;
	}
	
	public void setGenderId(short genderId) {
		this.genderId = genderId;
	}
	
	public short getAge() {
		return age;
	}
	
	public void setAge(short age) {
		this.age = age;
	}
	
	public short getIdentificationTypeId() {
		return identificationTypeId;
	}
	
	public void setIdentificationTypeId(short identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}
	
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
