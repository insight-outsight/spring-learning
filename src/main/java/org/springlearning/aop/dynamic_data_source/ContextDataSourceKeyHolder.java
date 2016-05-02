package org.springlearning.aop.dynamic_data_source;

import java.util.List;

import org.springlearning.utils.CRC32Utils;

public class ContextDataSourceKeyHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	private static List<String> dataSourcesList = null;

	public static void setDataSourceKey(String key) {	
		setDataSourceKey(CRC32Utils.getCRC32Value(key));
	}

	public static void setDataSourceKey(long key) {
		if(dataSourcesList == null){
			throw new IllegalArgumentException("dataSourcesList is null");
		}
		long targetDataSourceNum = key%dataSourcesList.size();

		System.out.println("set targetDataSourceNum="+targetDataSourceNum);
		
		if(targetDataSourceNum<0){
			throw new IllegalArgumentException("dataSourceNum "+ targetDataSourceNum+" < 0,key:"+key+",dataSourcesList.size():"+dataSourcesList.size());
		}
		if(targetDataSourceNum>dataSourcesList.size()-1){
			throw new IllegalArgumentException("dataSourceNum "+ targetDataSourceNum+">"+(dataSourcesList.size()-1)+",key:"+key+",dataSourcesList.size():"+dataSourcesList.size());
		}
		contextHolder.set(dataSourcesList.get((int)targetDataSourceNum));
	}
	
	public static String getDataSourceKey() {
		return contextHolder.get();
	}

	public static void clearDataSourceKey() {
		contextHolder.remove();
	}

	public static void setDataSourceKeyList(List<String> dataSourcesListArg) {
		dataSourcesList = dataSourcesListArg;
	}

}
