package com.z9devs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.z9devs.entities.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
	@Query("FROM Movie m WHERE m.director.id = ?1 order by m.year")
	List<Movie> getMoviesByDirector(int directorId);
}
