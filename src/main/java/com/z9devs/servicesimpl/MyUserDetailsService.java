package com.z9devs.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.z9devs.dao.UserRepository;
import com.z9devs.entities.User;
import com.z9devs.entities.UserPrincipal;

// Questo è il service che si utilizza per recuperare i dati degli utenti
@Service
public class MyUserDetailsService implements UserDetailsService {
	
	// Autowired di UserRepository, il dao che uso per recuperare i dati dal
	// db
	@Autowired
	private UserRepository repo;
	
	
	// Metodo che restituisce UserDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("User 404");
		return new UserPrincipal(user);
	}

}
