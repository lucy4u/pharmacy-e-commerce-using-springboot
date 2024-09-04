package com.pharm.implement.entity;
import java.time.*;
import jakarta.persistence.*;

@Entity
@Table(name="medicine")
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serial;
    @Column(name = "name", nullable = false)
	private String name;
    @Column(name = "price", nullable = false)
	private int price;
    @Column(name = "stocks", nullable = false)
	private int stocks;
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;
    @Column(name = "compound", nullable = false)
    private String compound;
    @Column(name = "manudate", nullable = false)
	private LocalDate manufactdate;
    @Column(name = "expiry", nullable = false)
	private LocalDate expiryDate;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
    @Column(name = "wholesale_rate", nullable = false)
    private int wholesaleRate;

	
	
	public product() {
		
	}
public product(String name, int price, int stocks, LocalDate manufactdate, LocalDate expiryDate,boolean isDeleted, int wholesaleRate,String compound) {
		super();
		this.name = name;
		this.price = price;
		this.stocks = stocks;
		this.manufactdate = manufactdate;
		this.expiryDate = expiryDate;
		this.isDeleted = isDeleted;
		this.wholesaleRate = wholesaleRate;
		this.compound = compound;
		
	}


public boolean isDeleted() {
	return isDeleted;
}
public void setDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
}
public int getWholesaleRate() {
	return wholesaleRate;
}
public void setWholesaleRate(int wholesaleRate) {
	this.wholesaleRate = wholesaleRate;
}
public Long getSerial() {
	return serial;
}
public void setSerial(Long serial) {
	this.serial = serial;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getStocks() {
	return stocks;
}
public void setStocks(int stocks) {
	this.stocks = stocks;
}
public String getManufacturer() {
	return manufacturer;
}
public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
}
public LocalDate getManufactdate() {
	return manufactdate;
}
public String getCompound() {
	return compound;
}
public void setCompound(String compound) {
	this.compound = compound;
}
public void setManufactdate(LocalDate manufactdate) {
	this.manufactdate = manufactdate;
}
public LocalDate getExpiryDate() {
	return expiryDate;
}
public void setExpiryDate(LocalDate expiryDate) {
	this.expiryDate = expiryDate;
}}

