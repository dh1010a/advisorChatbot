package com.baikja.service;

import com.baikja.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
