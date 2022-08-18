package com.ang.rental.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	@Column(name = "fname")
	private String firstName;
	@Column(name = "lname")
	private String lastName;
	private String email;
	private String password;
	private int contact;
	private String address; 
	private boolean active;
	private boolean verify;
	@OneToMany(targetEntity = Roles.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private Set<Roles> roles = new HashSet<>();
	@OneToMany(mappedBy = "userModel")
	@JsonIgnore
	private List<ListingModel> listingModel = new ArrayList<>();
	
	public UserModel(String firstName, String lastName, String email, String password, int contact, String address,
			boolean active, boolean verify, Set<Roles> roles, List<ListingModel> listingModel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.address = address;
		this.active = active;
		this.verify = verify;
		this.roles = roles;
		this.listingModel = listingModel;
	}
	
	public UserModel() {
		super();
	}

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public List<ListingModel> getListingModel() {
		return listingModel;
	}

	public void setListingModel(List<ListingModel> listingModel) {
		this.listingModel = listingModel;
	}	

	
}
