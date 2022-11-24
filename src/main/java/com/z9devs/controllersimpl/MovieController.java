package com.z9devs.controllersimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.z9devs.dao.DaoMovie;
import com.z9devs.entities.Movie;
import com.z9devs.services.DirectorService;
import com.z9devs.services.MovieService;

//@RestController
// https://www.geeksforgeeks.org/spring-rest-controller/#:~:text=The%20main%20difference%20between%20the,help%20of%20the%20%40RestController%20annotation.
// https://www.baeldung.com/spring-controller-vs-restcontroller
@Controller
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private DaoMovie dMovie;
	@Autowired
	private MovieService mService;
	
	@Autowired
	private DirectorService dService;
	
	@GetMapping("listJPA")
	public String movieListJPA(Model model) {
		model.addAttribute("method", "JPA query");
		model.addAttribute("mov", dMovie.getMoviesJPA());
		model.addAttribute("directors", dService.allDirectors());
		return "movies";
	}
	
	@GetMapping("listHibernateHQL")
	public String movieListHQL(Model model) {
		model.addAttribute("method", "Hibernate HQL query");
		model.addAttribute("mov", dMovie.getMoviesHibernateHQL());
		return "movies";
	}
	
	@GetMapping("listHibernateCriteria")
	public String movieList(Model model) {
		model.addAttribute("method", "Hibernate Criteria query");
		model.addAttribute("mov", dMovie.getMoviesHibernateCriteria());
		return "movies";
	}
	
	@GetMapping(path = "/documents")
	public ResponseEntity<List<Movie>> getAllDocuments() 
	{
		return new ResponseEntity<>(dMovie.getMoviesJPA(), HttpStatus.OK);
	}
	
	@PostMapping(path="/addMovie")
	public ResponseEntity<Boolean> addMovie(@RequestBody Movie m) {
		mService.addMovie(m);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}
	
	@GetMapping(path="/movieForDirector")
	public ResponseEntity<List<Movie>> addMovie(@RequestParam(name="directorId") int directorId) {
		return new ResponseEntity<>(mService.getMovieByDirectorId(directorId), HttpStatus.CREATED);
	}
}
