package com.z9devs.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Oggetto che implementa UserDetails e che viene restituito dal 
// service 
public class UserPrincipal implements UserDetails {
	
	private User user;
	
	// Costruttore richieda che venga passato User
	public UserPrincipal(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}
	
	
	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof UserPrincipal)) return false;
	        UserPrincipal that = (UserPrincipal) o;
	        return user.getUsername().equals(that.user.getUsername()) &&
	                user.getPassword().equals(that.user.getPassword());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(user.getUsername(), user.getPassword());
	    }
	    
	    

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
