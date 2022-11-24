package com.z9devs.dao;

import java.util.List;

import com.z9devs.entities.Movie;

public interface DaoMovie {

	List<Movie> getMoviesJPA();
	List<Movie> getMoviesHibernateHQL();
	List<Movie> getMoviesHibernateCriteria();
}
