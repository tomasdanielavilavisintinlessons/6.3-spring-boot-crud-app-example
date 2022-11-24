package com.z9devs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JdbcTest {
	@Autowired
	DataSource ds;
	
	@Test
	void testTransaction() {
		Connection con = null;
		Statement st1 = null;
		ResultSet rs1 = null;
		Statement st2 = null;
		Statement st3 = null;
		Statement st4 = null;
		ResultSet rs4 = null;
		int beforeCount = 0;
		try {
			con = ds.getConnection();
			// Conto numero righe fil per regista con id 2
			st1 = con.createStatement();
			rs1 = st1.executeQuery("select count(*) from movies WHERE director = 2");
			rs1.next();
			beforeCount = rs1.getInt(1);
			
			// Setto autocommit off
			con.setAutoCommit(false);
			
			// Cancello tutti i film del regista
			st2 = con.createStatement();
			st2.executeUpdate("DELETE FROM movies WHERE director = 2");
			// Cancello il regista con errore nella query
			st3 = con.createStatement();
			st2.executeUpdate("DELETE FROM nomesbagliato WHERE id = 2");
			// Faccio il commit
			con.commit();
			
		} catch (SQLException e) {
			if(con != null) {
				try {
					
					// Rollback
					con.rollback();
					
					// Setto autocommit on
					con.setAutoCommit(true);
					
					// Conto i risultati
					st4 = con.createStatement();
					rs4 = st4.executeQuery("select count(*) from movies WHERE director = 2");
					rs4.next();
					int afterCount = rs4.getInt(1);
					
					boolean check = false;
					
					if(beforeCount == afterCount)
						check = true;
					
					assertThat(check).isEqualTo(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			// https://stackoverflow.com/questions/4507440/must-jdbc-resultsets-and-statements-be-closed-separately-although-the-connection
			try{if(st1 != null) st1.close();} catch(SQLException e) {e.printStackTrace();}
			try{if(st2 != null) st2.close();} catch(SQLException e) {e.printStackTrace();}
			try{if(st3 != null) st3.close();} catch(SQLException e) {e.printStackTrace();}
			try{if(st4 != null) st4.close();} catch(SQLException e) {e.printStackTrace();}
			try{if(rs1 != null) rs1.close();} catch(SQLException e) {e.printStackTrace();}
			try{if(rs4 != null) rs4.close();} catch(SQLException e) {e.printStackTrace();}
			try{if(con != null) con.close();} catch(SQLException e) {e.printStackTrace();}
		}
	}
}
