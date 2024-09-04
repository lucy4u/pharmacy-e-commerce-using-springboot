package com.pharm.implement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pharm.implement.entity.Address;
import com.pharm.implement.entity.Bill;
import com.pharm.implement.entity.User;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

	List<Bill> findByUser(User userId);
	 Optional<Bill> findByBillIdAndUser(Long billId, User user);
	 List<Bill> findAll();
	 @Query("SELECT b.user.user_id FROM Bill b WHERE b.billId = :billId")
	  Long findUserIdByBillId(@Param("billId") Long billId);
	 @Query("SELECT MAX(b.billId) FROM Bill b WHERE b.user.user_id = :userId")
	    Long findLargestBillIdByUserId(@Param("userId") Integer userId);
	 Bill findByBillId(Long billId);
	 
	}

