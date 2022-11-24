package com.z9devs.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.z9devs.dao.DaoDirector;
import com.z9devs.dao.DirectorRepo;
import com.z9devs.entities.Director;
import com.z9devs.services.DirectorService;

// Service -> specializzazione dell'annotazione @Component, utilizzata
// 			  per i services
@Service
public class DirectorServiceImpl implements DirectorService {

	@Autowired
	// Qualifier -> si specifica il nome dato al bean
    @Qualifier("daoDirectorTrue")
	@Lazy
	DaoDirector dDao;
	
	@Autowired
	DirectorRepo dRepo;
	
	@Override
	public List<Director> allDirectors() {
		return dRepo.findAll();
	}

	@Override
	public Director getDirector(int id) {
		System.out.println("Get director dao");
		return dDao.getDirector(id);
	}

	@Override
	public void createDirector(Director d) {
		dRepo.save(d);
	}

	@Override
	public void deleteDirector(int id) {
		dRepo.deleteById(id);
	}

	@Override
	public void updateDirector(Director d) {
		dRepo.save(d);
	}

	@Override
	public void updateDirectorAlternative(Director d) {
		dDao.updateDirectorAlternative(d);
	}

	@Override
	public List<Director> filterDirectors(String query) {
		return dDao.filterDirectors(query);
	}
	
	@Override
	public boolean directorExists(int id) {
		return dRepo.existsById(id);
	}
}
