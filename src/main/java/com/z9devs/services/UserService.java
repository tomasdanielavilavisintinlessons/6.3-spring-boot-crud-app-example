package com.z9devs.services;

import com.z9devs.entities.User;

public interface UserService {

	boolean userExists(String username);

	void createUser(User user);

}
