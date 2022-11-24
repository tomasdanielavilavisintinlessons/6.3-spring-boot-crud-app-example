package com.z9devs.services;

import java.util.List;

import com.z9devs.entities.Movie;

public interface MovieService {
	public void addMovie(Movie m);
	public List<Movie> getMovieByDirectorId(int directorId);
}
