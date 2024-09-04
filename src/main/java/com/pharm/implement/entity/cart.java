package com.pharm.implement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class cart {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchase_id;
	@Column(name = "cart_id", nullable = false)
	private int cartid;
	@Column(name = "quantity", nullable = false)
	private int quantity;
	@Column(name = "user_id", nullable = false)
	private int userid;
	@Column(name = "serial", nullable = false)
	private Long serial;
	
    public cart() {
        // Default constructor
    }
	
	public cart(int cartid, int quantity, int userid, Long serial) {
		super();
		this.cartid = cartid;
		this.quantity = quantity;
		this.userid = userid;
		this.serial = serial;
	}
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	public int getCart_id() {
		return cartid;
	}
	public void setCart_id(int cartid) {
		this.cartid = cartid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getUser_id() {
		return userid;
	}
	public void setUser_id(int userid) {
		this.userid = userid;
	}
	public Long getSerial() {
		return serial;
	}
	public void setSerial(Long serial) {
		this.serial = serial;
	}
	
	
}
