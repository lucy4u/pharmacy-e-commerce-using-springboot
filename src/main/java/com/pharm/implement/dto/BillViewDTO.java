package com.pharm.implement.dto;

import java.time.LocalDateTime;


public class BillViewDTO {

	    private String medicineName;
	    private int price;
	    private int quantity;
	    private String manufacturer;
	    private int totalPrice;
	    private LocalDateTime billDate;
	    private double grandTotal;
	    private String userName;
	    private int AddId;
	    
	    
	    
	    public int getAddId() {
			return AddId;
		}

		public void setAddId(int addId) {
			AddId = addId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public double getGrandTotal() {
	        return grandTotal;
	    }

	    public void setGrandTotal(double grandTotal) {
	        this.grandTotal = grandTotal;
	    }
		public String getMedicineName() {
			return medicineName;
		}
		public void setMedicineName(String medicineName) {
			this.medicineName = medicineName;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getManufacturer() {
			return manufacturer;
		}
		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}
		public int getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(int totalPrice) {
			this.totalPrice = totalPrice;
		}
		public LocalDateTime getBillDate() {
			return billDate;
		}
		public void setBillDate(LocalDateTime billDate) {
			this.billDate = billDate;
		}

	    // getters and setters
	

	    
}
