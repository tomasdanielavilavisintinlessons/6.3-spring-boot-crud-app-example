package com.z9devs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.z9devs.entities.User;

// Interfaccia che uso per recuperare i dati degli user dal db
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	boolean existsByUsername(String username);
}
