package com.demo.krypto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.demo.krypto.audit.AuditModel;

@Entity
@Table(name = "PROFILE")

public class ProfileEntity extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4814595115801146324L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROFILE_ID")
	private Long profileId;

	@Email
	@Size(max = 100)
	@Column(name = "EMAIL_ID", nullable = false)
	private String email;

	@Size(max = 100)
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Size(max = 100)
	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "ADDRESS", columnDefinition = "TEXT")
	private String address;

	@Size(max = 100)
	@Column(name = "CITY")
	private String city;

	@Size(max = 100)
	@Column(name = "STATE")
	private String state;

	@Size(max = 100)
	@Column(name = "COUNTRY")
	private String country;

	@Size(max = 100)
	@Column(name = "ZIPCODE")
	private String zipCode;

	@Size(max = 100)
	@Column(name = "PHONE", nullable = false)
	private String phoneNumber;

	@Size(max = 100)
	@Column(name = "C_NAME")
	private String cName;

	@Size(max = 100)
	@Column(name = "C_EMAIL_ID", nullable = false)
	private String cEmail;

	@Size(max = 100)
	@Column(name = "C_WEBSITE")
	private String cWebsite;

	@Size(max = 100)
	@Column(name = "C_PHONE", nullable = false)
	private String cPhoneNumber;

	@Column(name = "C_ADDRESS", columnDefinition = "TEXT")
	private String cAddress;

	@Size(max = 100)
	@Column(name = "C_ZIPCODE")
	private String cZipCode;

	@Size(max = 100)
	@Column(name = "C_CITY")
	private String cCity;

	@Size(max = 100)
	@Column(name = "C_STATE")
	private String cState;

	@Size(max = 100)
	@Column(name = "C_COUNTRY")
	private String cCountry;

	@Column(name = "PROFILE_STS", columnDefinition = "BIT", nullable = false)
	private Boolean status;



	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public String getcWebsite() {
		return cWebsite;
	}

	public void setcWebsite(String cWebsite) {
		this.cWebsite = cWebsite;
	}

	public String getcPhoneNumber() {
		return cPhoneNumber;
	}

	public void setcPhoneNumber(String cPhoneNumber) {
		this.cPhoneNumber = cPhoneNumber;
	}

	public String getcAddress() {
		return cAddress;
	}

	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}

	public String getcZipCode() {
		return cZipCode;
	}

	public void setcZipCode(String cZipCode) {
		this.cZipCode = cZipCode;
	}

	public String getcCity() {
		return cCity;
	}

	public void setcCity(String cCity) {
		this.cCity = cCity;
	}

	public String getcState() {
		return cState;
	}

	public void setcState(String cState) {
		this.cState = cState;
	}

	public String getcCountry() {
		return cCountry;
	}

	public void setcCountry(String cCountry) {
		this.cCountry = cCountry;
	}

	
}
