package com.pharm.implement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pharm.implement.entity.Bill;
import com.pharm.implement.entity.BillMedicine;
import com.pharm.implement.entity.BillMedicineId;

@Repository
public interface BillMedicineRepository extends JpaRepository<BillMedicine, BillMedicineId> {
	 List<BillMedicine> findById_Bill_BillId(Long billId);

	List<BillMedicine> findById_Bill(Bill bill);
}
