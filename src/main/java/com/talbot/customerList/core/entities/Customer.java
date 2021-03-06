package com.talbot.customerList.core.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="customer")
public class Customer {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private Address address;
	public Customer() {this.id = new ObjectId().toString();}

	// public Customer(String firstName, String lastName, String email, String
	// telephone) {
	// this.id = new ObjectId().toString();
	// this.firstName = firstName;
	// this.lastName = lastName;
	// this.email = email;
	// this.telephone = telephone;
	// }
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email address
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email address to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the telephone number
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone number to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
