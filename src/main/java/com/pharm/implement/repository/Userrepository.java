package com.pharm.implement.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pharm.implement.entity.User;
@Repository
public interface Userrepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
	User findById(int userId);
	 @Query("SELECT MAX(u.user_id) FROM User u")
	    Integer findMaxUserId();
}
