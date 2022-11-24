package com.z9devs;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootCrudApplicationTests {

	@Autowired
    private DataSource dataSource;
	
	@Test
	void contextLoads() {
	}
	
	@Test
    public void givenTomcatConnectionPoolInstance_whenCheckedPoolClassName_thenCorrect() {
        assertThat(dataSource.getClass().getName())
          .isEqualTo("org.apache.tomcat.jdbc.pool.DataSource");
        
    }
	

}
