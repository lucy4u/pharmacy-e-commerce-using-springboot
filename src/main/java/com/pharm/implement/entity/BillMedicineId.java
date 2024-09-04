package com.pharm.implement.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class BillMedicineId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "med_id", nullable = false)
    private product medicine;
    
    // Default constructor (required by JPA)
    public BillMedicineId() {}

    // Constructor with parameters
    public BillMedicineId(Bill bill, product medicine) {
        this.bill = bill;
        this.medicine = medicine;
    }
    
    public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public product getMedicine() {
		return medicine;
	}

	public void setMedicine(product medicine) {
		this.medicine = medicine;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillMedicineId that = (BillMedicineId) o;
        return Objects.equals(bill, that.bill) &&
               Objects.equals(medicine, that.medicine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bill, medicine);
    }
}