package com.pharm.implement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharm.implement.entity.Address;


public interface AddressRepository  extends JpaRepository<Address, Long>  {
	List<Address> findByAddressId(Long addressId);

}
