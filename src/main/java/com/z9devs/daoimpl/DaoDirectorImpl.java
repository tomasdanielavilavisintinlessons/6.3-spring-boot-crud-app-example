package com.z9devs.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.z9devs.dao.DaoDirector;
import com.z9devs.entities.Director;

// @Primary -> prima scelta
//https://vladmihalcea.com/best-way-call-stored-procedure-jpa-hibernate/
@Repository("daoDirectorTrue")
@Transactional
@Lazy
// @Scope("prototype")
public class DaoDirectorImpl implements DaoDirector {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataSource datasource;
	
	@Autowired private ApplicationContext applicationContext;

	@PostConstruct
	void postConstruct() {
		System.out.println("Ho creato un bean di tipo DaoDirectorImpl");
		/*
		try {
			TimeUnit.SECONDS.sleep(5);
			((DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory()).destroyBean(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	@PreDestroy
	void preDestroy() {
		System.out.println("Sto distruggendo un bean di tipo DaoDirectorImpl");
	}
	// @Autowired
    // private EntityManagerFactory entityManagerFactory;
	
	@Override
	public List<Director> allDirectors() {
		return (List<Director>) em.createQuery("FROM Director d").getResultList();
	}

	@Override
	public Director getDirector(int id) {
		System.out.println("Get director");
		return em.find(Director.class, id);
	}

	@Override
	public void createDirector(Director d) {
		em.persist(d);;
	}

	@Override
	public void deleteDirector(int id) {
		Director d = em.find(Director.class, id);
		if(d != null)
			em.remove(d);
	}

	@Override
	public void updateDirector(Director d) {
		em.merge(d);
	}

	@Override
	public void updateDirectorAlternative(Director d) {
		/*
		 * 
		 SessionFactory sf = entityManagerFactory.unwrap(SessionFactory.class);
		
		Director di = sf.openSession().find(Director.class, d.getId());	
		di.setName(d.getName());
		sf.getCurrentSession().saveOrUpdate(di);
		
		
		SessionFactory sf = entityManagerFactory.unwrap(SessionFactory.class);
		
		Director di = sf.getCurrentSession().find(Director.class, d.getId());	
		di.setName(d.getName());
		sf.getCurrentSession().saveOrUpdate(di);
		 */
		
		Director di = em.find(Director.class, d.getId());		
		di.setName(d.getName());
		em.merge(di);	
	}

	@Override
	public List<Director> filterDirectors(String query) {
		Session session = em.unwrap(Session.class);
		Criteria c = session.createCriteria(Director.class, "director");
		c.add(Restrictions.like("director.name", String.format("%%%s%%", query)));
		return c.list();
	}
	
	@Override
	public List<Director> filterDirectorsJbc() {
		Connection con = null ;
		try {
			con = datasource.getConnection();
			
			Statement s = con.createStatement();
			
			String sql = "select * from directors";
			
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				System.out.println(res.getString("name"));
			}
			
			res.close();
			con.close();
			
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
	
	
	
	@Override
	public List<Director> provaJdbcTemplate() {
        String sql = "SELECT * FROM directors";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {
        	System.out.println("con spring jdbctemplate");
        	System.out.println(row.get("name"));
        }

		return null;
	}
	
	
}
