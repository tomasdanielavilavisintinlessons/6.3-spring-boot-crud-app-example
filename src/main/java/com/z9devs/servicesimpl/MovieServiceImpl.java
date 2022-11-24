package com.z9devs.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.z9devs.dao.MovieRepo;
import com.z9devs.entities.Movie;
import com.z9devs.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	MovieRepo mRepo;

	@Override
	public void addMovie(Movie m) {
		mRepo.save(m);
	}

	@Override
	public List<Movie> getMovieByDirectorId(int directorId) {
		return mRepo.getMoviesByDirector(directorId);
	}

}
