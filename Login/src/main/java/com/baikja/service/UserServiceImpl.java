package com.baikja.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baikja.model.Role;
import com.baikja.model.User;
import com.baikja.repository.RoleRepository;
import com.baikja.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        String role = "";
        if(user.getRole().equals("1")) {
            role = "ADMIN";
        } else if(user.getRole().equals("2")) {
            role = "USER";
        } else if(user.getRole().equals("3")) {
            role = "GUEST";
        }
        Role userRole = roleRepository.findByRole(role);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		userRepository.save(user);
	}

}
