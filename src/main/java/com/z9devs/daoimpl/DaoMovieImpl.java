package com.z9devs.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.z9devs.dao.DaoMovie;
import com.z9devs.entities.Movie;

@Repository
@Transactional
public class DaoMovieImpl implements DaoMovie {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Movie> getMoviesJPA() {
		return (List<Movie>) em.createQuery("FROM Movie m").getResultList();
	}

	@Override
	public List<Movie> getMoviesHibernateHQL() {
		
		return (List<Movie>) em.unwrap(Session.class).createQuery("FROM Movie m").getResultList();
	}

	@Override
	public List<Movie> getMoviesHibernateCriteria() {
		/*
		Criteria c = s.createCriteria(getClass());
		*/
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		Root<Movie> root = query.from(Movie.class);
		query.select(root);
		Query<Movie> q = session.createQuery(query);
		return null;
	}
}
