package org.springlearning.aop.dynamic_data_source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
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


@Repository
public class DynamicDataSourceDAOImpl implements DynamicDataSourceDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String INSERT_SQL = "insert into index_user_phone(uid"
			+ ",mobile_phone_number,create_time,last_modified_time,status,rand_code,checked_status,"
			+ "is_self_manage) values(?,?,?,?,?,?,?,?)";

			
	@Override
	public long insert(final IndexUserPhone indexUserPhone) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		
		jdbcTemplate.update(new PreparedStatementCreator() {  
		    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {                 
		           PreparedStatement ps = connection.prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);  
		           ps.setLong(1, indexUserPhone.getUid());  
		           ps.setString(2, indexUserPhone.getMobliePhoneNumber());
		           ps.setDate(3, new Date(2016,4,8));
		           ps.setDate(4,new Date(2016,4,8));
		           ps.setInt(5, indexUserPhone.getStatus());
		           ps.setString(6, indexUserPhone.getRandCode());
		           ps.setInt(7, indexUserPhone.getCheckStatus());
		           ps.setInt(8, indexUserPhone.getIsSelfManage());
		           return ps;  
		    }  
		}, keyHolder);  
		  
		Long generatedId = keyHolder.getKey().longValue();    
		return generatedId;
	}





	@Override
	public IndexUserPhone selectById(Long id) throws Exception {
		return null;
	}




}
