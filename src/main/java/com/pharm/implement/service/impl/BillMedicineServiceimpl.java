package com.pharm.implement.service.impl;



import java.util.List;
import java.util.function.Function;


import org.springframework.stereotype.Service;

import com.pharm.implement.entity.BillMedicine;
import com.pharm.implement.entity.product;
import com.pharm.implement.service.BillMedicineService;

@Service
public class BillMedicineServiceimpl implements BillMedicineService {

	
	    @Override
	    public int calculateTotalPrice(List<BillMedicine> billMedicines) {
	        int totalPrice = 0;

	        for (BillMedicine billMedicine : billMedicines) {
	            // Get the quantity from the BillMedicine
	            int quantity = billMedicine.getQuantity();

	            // Get the Product using the med_id from BillMedicine
	            product product = billMedicine.getId().getMedicine();

	            // Calculate the total price for the current BillMedicine
	            int productTotalPrice = product.getPrice() * quantity;



	            // Add the total price for the current BillMedicine to the overall total
	            totalPrice += productTotalPrice;
	        }

	        return totalPrice;
	    }

	    @Override
	    public int calculateTotalWholesalePrice(List<BillMedicine> billMedicines) {
	        int totalWholesalePrice = 0;

	        for (BillMedicine billMedicine : billMedicines) {
	            // Get the quantity from the BillMedicine
	            int quantity = billMedicine.getQuantity();

	            // Get the Product using the med_id from BillMedicine
	            product product = billMedicine.getId().getMedicine();

	            // Calculate the total wholesale price for the current BillMedicine
	            int productTotalWholesalePrice = product.getWholesaleRate() * quantity;

	            // Add the total wholesale price for the current BillMedicine to the overall total
	            totalWholesalePrice += productTotalWholesalePrice;
	        }

	        return totalWholesalePrice;
	    }

	    @Override
	    public int calculateProfit(List<BillMedicine> billMedicines) {
	        // Calculate the total price and total wholesale price
	        int totalPrice = calculateTotalPrice(billMedicines);
	        int totalWholesalePrice = calculateTotalWholesalePrice(billMedicines);

	        // Calculate the profit (total price - total wholesale price)
	        return totalPrice - totalWholesalePrice;
	    }

	    private int calculateTotal(List<BillMedicine> billMedicines, Function<product, Integer> priceFunction) {
	        return billMedicines.stream()
	                .mapToInt(billMedicine -> {
	                    int price = priceFunction.apply(billMedicine.getId().getMedicine());
	                    return price * billMedicine.getQuantity();
	                })
	                .sum();
	    }
  }

