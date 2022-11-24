package com.z9devs.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.z9devs.dao.UserRepository;
import com.z9devs.entities.User;
import com.z9devs.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder bcEncoder;
	
	@Override
	public boolean userExists(String username) {
		System.out.println("Controllo se user con username " + username + " esiste");
		return repo.existsByUsername(username);
	}
	
	
	@Override
	public void createUser(User user) {
		System.out.println("Creo username");
		user.setPassword(bcEncoder.encode(user.getPassword()));
		repo.save(user);
	}
}
