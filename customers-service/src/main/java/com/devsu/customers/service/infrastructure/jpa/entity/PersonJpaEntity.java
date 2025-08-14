package com.devsu.customers.service.infrastructure.jpa.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "person", schema = "devsu")
public class PersonJpaEntity {
 @Id @GeneratedValue
 @Column(name = "person_id", columnDefinition = "UUID")
 private UUID personId;

 @Column(name = "person_name", nullable = false, length = 100)
 private String personName;

 @Column(name = "gender_id", nullable = false)
 private Short genderId;

 @Column(nullable = false)
 private Short age;

 @Column(name = "identification_type_id", nullable = false)
 private Short identificationTypeId;

 @Column(name = "identification_number", nullable = false, length = 30)
 private String identificationNumber;

 @Column(nullable = false, length = 300)
 private String address;

 @Column(nullable = false, length = 20)
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

 public Short getGenderId() {
	return genderId;
 }

 public void setGenderId(Short genderId) {
	this.genderId = genderId;
 }

 public Short getAge() {
	return age;
 }

 public void setAge(Short age) {
	this.age = age;
 }

 public Short getIdentificationTypeId() {
	return identificationTypeId;
 }

 public void setIdentificationTypeId(Short identificationTypeId) {
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
