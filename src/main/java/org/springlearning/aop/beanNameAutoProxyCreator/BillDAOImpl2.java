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
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class BillDAOImpl2 implements BillDAO2 {

	private JdbcTemplate jdbcTemplate;
	
	private static final String INSERT_SQL_2 
	= "insert into xiang.baihu(id,date) values(?,'2016-04-13 16:32:00');";

			
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
		           System.out.println(connection.getClientInfo());
		    	   PreparedStatement ps = connection.prepareStatement(INSERT_SQL_2);  
		           ps.setLong(1, 38);
		           return ps;  
		    }  
		});  
		  
	}




}
