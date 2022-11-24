package com.z9devs.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.z9devs.entities.Director;
import com.z9devs.services.DirectorService;

public abstract class DirectorControllerAbstract {
	@Autowired
	DirectorService dService;
	
	@GetMapping("")
	public String movieListJPA() {
		return "directors";
	}
	
	@GetMapping("/getDirectors")
	public ResponseEntity<List<Director>> getDirectors() {
		return new ResponseEntity<>(dService.allDirectors(), HttpStatus.OK);
	}
	
	@PostMapping(path="/createDirector", consumes="application/json")
	public ResponseEntity<Boolean> createDirector(@RequestBody Director director) {
		dService.createDirector(director);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}
	
	@PostMapping(path="/updateDirector", consumes="application/json")
	public ResponseEntity<Boolean> updateDirector(@RequestBody Director director) {
		dService.updateDirector(director);
		return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path="/deleteDirector")
	public ResponseEntity<Boolean> deleteDirector(@RequestParam(name="directorId") int directorId) {
		dService.deleteDirector(directorId);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@GetMapping(path="/filterDirectors")
	// Annotazione @ResponseBody
	public @ResponseBody List<Director> filterDirectors(@RequestParam(name="q") String query) {
		return dService.filterDirectors(query);
	}
	
	@GetMapping(path="/{id}")
	public ModelAndView singleDirector(@PathVariable int id) {
		ModelAndView mav = new ModelAndView();
		
		if(dService.directorExists(id)) {
			System.out.println("Dentro a condizione");
			
			mav.setViewName("directorDetails");
			mav.addObject("directorData", dService.getDirector(id));
			return mav;
		} else {
			mav.setViewName("errorsPages/404");
			Map<String,String> res = new LinkedHashMap<String,String>();
			res.put("entityType", "Director");
			res.put("entityId", id+"");
			mav.addObject("data", res);
			return mav;
		}
	}
}
