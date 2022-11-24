package com.z9devs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.z9devs.dao.UserRepository;
import com.z9devs.entities.User;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// https://www.baeldung.com/spring-testing-separate-data-source
@DataJpaTest
public class JpaTest {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository repository;
	
	@Test
	void testExample() throws Exception {
		User u = new User();
		u.setUsername("provaUser");
		u.setPassword("$2a$10$rpHttqvbOq.FRjiOwpDf2OtWdCLdjTkpzIKAZbEzTa/ruKiV7IkJe");
		entityManager.persist(u);
		User user = this.repository.findByUsername("provaUser");
		assertThat(user.getUsername()).isEqualTo("provaUser");
		assertThat(user.getPassword()).isEqualTo("$2a$10$rpHttqvbOq.FRjiOwpDf2OtWdCLdjTkpzIKAZbEzTa/ruKiV7IkJe");
	}
}
