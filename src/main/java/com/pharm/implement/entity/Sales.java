package com.pharm.implement.entity;

	import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

	@Entity
	@Table(name="Sales" ,uniqueConstraints = @UniqueConstraint(columnNames="date"))
	public class Sales {


	    @Id
	    @Temporal(TemporalType.DATE)
	    private Date date;
	    @Column(name = "daily_profit", nullable = false)
	    private double dailyProfit;

	    // Default constructor required by JPA
	    public Sales() {
	    }

	    // Constructor with parameters
	    public Sales(Date date, double dailyProfit) {
	        this.date = date;
	        this.dailyProfit = dailyProfit;
	    }

	    // Getters and Setters
	    public Date getDate() {
	        return date;
	    }

	    public void setDate(Date date) {
	        this.date = date;
	    }

	    public double getDailyProfit() {
	        return dailyProfit;
	    }

	    public void setDailyProfit(double dailyProfit) {
	        this.dailyProfit = dailyProfit;
	    }

	    // Method to add daily profit to the database
	
	}

