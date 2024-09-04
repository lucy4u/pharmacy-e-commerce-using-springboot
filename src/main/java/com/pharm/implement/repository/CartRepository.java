package com.pharm.implement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pharm.implement.entity.cart;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<cart, Integer> {


		
		@Query("SELECT MAX(p.cartid) FROM cart p WHERE p.userid = :userId")
		    Integer findBiggestCartIdByUserId(int userId);
		  List<cart> findByuserid(int userId);
		  void deleteByUseridAndCartid(Long userId, Long cartId);
		  @Transactional
		    void deleteByUserid(int userId);
		  cart findByCartid(Long cartId);
	}


