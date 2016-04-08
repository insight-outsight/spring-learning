package org.springlearning.aop.dynamic_data_source;

public class ContextDataSourceKeyHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSourceKey(String dataSourceKey) {
		clearDataSourceKey();
		contextHolder.set(dataSourceKey);
	}

	public static String getDataSourceKey() {
		return contextHolder.get();
	}

	public static void clearDataSourceKey() {
		contextHolder.remove();
	}

}