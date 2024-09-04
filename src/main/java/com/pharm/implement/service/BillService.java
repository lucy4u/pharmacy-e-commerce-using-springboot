package com.pharm.implement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pharm.implement.dto.BillViewDTO;
import com.pharm.implement.entity.Address;
import com.pharm.implement.entity.Bill;
import com.pharm.implement.entity.User;

@Service
public interface BillService {
	 public List<Bill> viewBillHistory(User userId);
	 public List<BillViewDTO> getBillDetails(Long billId, User userId);
	 public Address viewAddress(Long billId);
}
