package com.z9devs.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.z9devs.entities.Director;

public interface DirectorService {
	List<Director> allDirectors();
	Director getDirector(int id);
	void createDirector(Director d);
	void deleteDirector(int id);
	void updateDirector(Director d);
	void updateDirectorAlternative(Director d);
	List<Director> filterDirectors(String query);
	boolean directorExists(int id);
}
