package com.pharm.implement.service.impl;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharm.implement.dto.BillViewDTO;
import com.pharm.implement.entity.Address;
import com.pharm.implement.entity.Bill;
import com.pharm.implement.entity.BillMedicine;
import com.pharm.implement.entity.User;
import com.pharm.implement.repository.AddressRepository;
import com.pharm.implement.repository.BillMedicineRepository;
import com.pharm.implement.repository.BillRepository;
import com.pharm.implement.service.BillService;


@Service
public class BillServiceimpl implements BillService {
	
	   @Autowired
	    private BillRepository billRepository;
	   @Autowired
	    private BillMedicineRepository billMedicineRepository;
	   @Autowired
	    private AddressRepository addressRepository;


	@Override
	public List<Bill> viewBillHistory(User user) {
	    if (user != null && user.getUser_id() == 2) {
	        // If the userId is 2, return all bills
	        return billRepository.findAll();
	    } else {
	        // Otherwise, return bills associated with the specified user
	        return billRepository.findByUser(user);
	    }
	}
     @Override
	 public List<BillViewDTO> getBillDetails(Long billId, User userId) {
    	 Optional<Bill> optionalBill = billRepository.findByBillIdAndUser(billId, userId);
         if (optionalBill.isPresent()) {
             Bill bill = optionalBill.get();

          // Update the method call to use the correct repository method
             List<BillMedicine> billMedicines = billMedicineRepository.findById_Bill_BillId(billId);


             // Create DTOs for the response
             List<BillViewDTO> billMedicineDTOs = new ArrayList<>();
             double grandTotal = 0;
             String userName = userId.getName();
             for (BillMedicine billMedicine : billMedicines) {
                 BillViewDTO dto = new BillViewDTO();
                 dto.setMedicineName(billMedicine.getId().getMedicine().getName());
                 dto.setPrice(billMedicine.getId().getMedicine().getPrice());
                 dto.setQuantity(billMedicine.getQuantity());
                 dto.setManufacturer(billMedicine.getId().getMedicine().getManufacturer());
                 dto.setTotalPrice(billMedicine.getQuantity() * billMedicine.getId().getMedicine().getPrice());
                 dto.setBillDate(bill.getBillDate());
                 billMedicineDTOs.add(dto);
                 grandTotal += dto.getTotalPrice();
             }
             

             for (BillViewDTO dto : billMedicineDTOs) {
                 dto.setGrandTotal(grandTotal);
                 dto.setUserName(userName);
                     
                 
             }
             return billMedicineDTOs;
         } else {
             // Handle case where the specified bill doesn't exist for the user
             return Collections.emptyList();
	 }
     

}
	@Override
	public Address viewAddress(Long billId) {
		Bill bill= billRepository.findByBillId(billId);
				
		return bill.getAddress();
	}
}
  

	
