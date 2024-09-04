package com.pharm.implement.service;



import com.pharm.implement.dto.Userregistrationdto;
import com.pharm.implement.entity.User;

public interface Userservice{

	void save(Userregistrationdto registrationdto );
	
	User findUserByEmail(String email);
	
	  
}
