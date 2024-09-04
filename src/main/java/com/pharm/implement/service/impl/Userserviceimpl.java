package com.pharm.implement.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pharm.implement.ERole;
import com.pharm.implement.dto.Userregistrationdto;
import com.pharm.implement.entity.Role;
import com.pharm.implement.entity.User;
import com.pharm.implement.repository.RoleRepository;
import com.pharm.implement.repository.Userrepository;
import com.pharm.implement.service.Userservice;


@Service
public class Userserviceimpl implements Userservice{

	private Userrepository UserRepository;
	 private RoleRepository roleRepository;
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	 
	public Userserviceimpl(Userrepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		super();
		UserRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}




	@Override
	public void save(Userregistrationdto registrationdto) {

       Role role = roleRepository.findByName(ERole.USER);
        if(role == null){
          role = checkRoleExist();
        }
        
	    User user = new User(registrationdto.getName(), registrationdto.getEmail(), registrationdto.getPhone(), passwordEncoder.encode(registrationdto.getPassword()),Arrays.asList(role));

           UserRepository.save(user);
           // Retrieve the max user ID and save it in the session attribute "userId"
          
       
           
	}


	@Override
	public User findUserByEmail(String email) {
		return UserRepository.findByEmail(email);
    }
	  private Userregistrationdto mapToUserDto(User user){
		  Userregistrationdto userDto = new Userregistrationdto();
	        userDto.setName(user.getName());
	        userDto.setPhone(user.getPhone());
	        userDto.setEmail(user.getEmail());
	        return userDto;
	    }

	    private Role checkRoleExist(){
	        Role role = new Role();
	        role.setName(ERole.USER);
	        return roleRepository.save(role);
	    }

}