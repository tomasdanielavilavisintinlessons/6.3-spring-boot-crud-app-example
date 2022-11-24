package com.z9devs.dao;

import java.util.List;

import com.z9devs.entities.Director;

public interface DaoDirector {
	List<Director> allDirectors();
	Director getDirector(int id);
	void createDirector(Director d);
	void deleteDirector(int id);
	void updateDirector(Director d);
	void updateDirectorAlternative(Director d);
	List<Director> filterDirectors(String query);
	List<Director> filterDirectorsJbc();
	List<Director> provaJdbcTemplate();
}
