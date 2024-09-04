package com.pharm.implement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "bill_date")
    private LocalDateTime billDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
    
   

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Bill() {
        // initialize any default values if needed
    }

	public Bill(User user, LocalDateTime billDate,Address address) {
		super();
		this.user = user;
		this.billDate = billDate;
		this.address = address;
	}


	public Long getBillId() {
		return billId;
	}




	public void setBillId(Long billId) {
		this.billId = billId;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public LocalDateTime getBillDate() {
		return billDate;
	}




	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}
}
