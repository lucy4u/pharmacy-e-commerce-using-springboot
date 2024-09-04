package com.pharm.implement.entity;



import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill_items")
public class BillMedicine {

	@EmbeddedId
    private BillMedicineId id;
 
    @Column(name = "quantity")
    private int quantity;
    
    public BillMedicine() {
        // Default constructor required by JPA
    }
    

   
    public BillMedicine(Bill bill, product medicine, int quantity) {
        this.id = new BillMedicineId(bill, medicine);
        this.quantity = quantity;
    }

    public BillMedicineId getId() {
        return id;
    }

    public void setId(BillMedicineId id) {
        this.id = id;
    }

	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}


	