package com.pharm.implement.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharm.implement.ERole;
import com.pharm.implement.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(ERole name);

}