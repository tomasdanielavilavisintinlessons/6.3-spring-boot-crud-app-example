package com.z9devs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.z9devs.entities.Director;

public interface DirectorRepo extends JpaRepository<Director, Integer> {
	public List<Director> findByName(String name);
	
	
}

