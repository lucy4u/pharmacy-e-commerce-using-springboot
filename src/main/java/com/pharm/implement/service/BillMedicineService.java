package com.pharm.implement.service;

import java.util.List;

import com.pharm.implement.entity.BillMedicine;

public interface BillMedicineService {
	
	int calculateTotalPrice(List<BillMedicine> billMedicines);
    int calculateTotalWholesalePrice(List<BillMedicine> billMedicines);
    int calculateProfit(List<BillMedicine> billMedicines);

}
