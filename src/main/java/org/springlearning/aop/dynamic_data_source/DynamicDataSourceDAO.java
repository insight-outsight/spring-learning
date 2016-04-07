package org.springlearning.aop.dynamic_data_source;


import java.util.List;
import java.util.Map;


public interface DynamicDataSourceDAO {

	public long insert(final IndexUserPhone indexUserPhone) throws Exception;
	public IndexUserPhone selectById(final Long id) throws Exception;

	
}
