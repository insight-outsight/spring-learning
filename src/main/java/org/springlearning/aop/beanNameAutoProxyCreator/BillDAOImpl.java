package org.springlearning.aop.beanNameAutoProxyCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;



public class BillDAOImpl implements BillDAO {

	private JdbcTemplate jdbcTemplate;
	
	private static final String INSERT_SQL_1 
		= "insert into fang.qinglong(id,name) values(?,'扩大战果了');";


			
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	@Override
	public void insert() throws Exception {

		
		jdbcTemplate.update(new PreparedStatementCreator() {  
		    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {                 
		    	   System.out.println(connection.getClass().getName());//com.jolbox.bonecp.ConnectionHandle
		           System.out.println(connection.getClientInfo());
		    	   PreparedStatement ps = connection.prepareStatement(INSERT_SQL_1);  
		           ps.setLong(1, 54);
		           return ps;  
		    }  
		});  
		  
	}




}
