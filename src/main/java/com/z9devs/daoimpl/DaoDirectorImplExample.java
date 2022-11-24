package com.z9devs.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.z9devs.dao.DaoDirector;
import com.z9devs.entities.Director;

@Repository("daoDirectorFalse")
@Transactional
public class DaoDirectorImplExample implements DaoDirector {

	@Override
	public List<Director> allDirectors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Director getDirector(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createDirector(Director d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteDirector(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDirector(Director d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDirectorAlternative(Director d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Director> filterDirectors(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Director> filterDirectorsJbc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Director> provaJdbcTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

}
