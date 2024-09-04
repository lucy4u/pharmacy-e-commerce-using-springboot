package com.pharm.implement.dto;

public class CartViewDTO {
    
    private int cartId; // new field for cartId
    private String name;
    private String manufacturer;
    private int price;
    private int quantity;
    private double totalPrice;  // new field for total price
 

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Constructor with all fields
    public CartViewDTO(int cartId, String name, String manufacturer, int price, int quantity, double totalPrice) {
        this.cartId = cartId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
