package com.talbot.customerList.core.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="customers")
public class Customer {
	@Id
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String telephone;
//	private Address address;
	public Customer() {}
	public Customer(String firstname, String lastname, String email, String telephone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.telephone = telephone;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstname;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastname;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastname) {
		this.lastname = lastname;
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
//	/**
//	 * @return the address
//	 */
//	public Address getAddress() {
//		return address;
//	}
//	/**
//	 * @param address the address to set
//	 */
//	public void setAddress(Address address) {
//		this.address = address;
//	}
    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstname='%s', lastname='%s', email='%s', telephone='%s']",
                id, firstname, lastname, email, telephone);
    }
}
