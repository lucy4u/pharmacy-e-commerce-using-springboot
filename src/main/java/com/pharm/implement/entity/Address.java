package com.pharm.implement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "address_id")
	    private Long addressId;

	    @Column(name = "street")
	    private String street;

	    @Column(name = "city")
	    private String city;

	    @Column(name = "state")
	    private String state;

	    @Column(name = "zip_code")
	    private String zipCode;

	    public Address() {
	        // Initialize default values if needed
	    }
	    
	    
		public Address(String street, String city, String state, String zipCode) {
			super();
			this.street = street;
			this.city = city;
			this.state = state;
			this.zipCode = zipCode;
		}

		public Long getAddressId() {
			return addressId;
		}

		public void setAddressId(Long addressId) {
			this.addressId = addressId;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
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

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}
	    
	    
}
