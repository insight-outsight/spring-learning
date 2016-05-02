package org.springlearning.aop.dynamic_data_source;



public class ShardingInfo {

	private String mapperClassName;
	private String modelClassName;
	private String modelExampleClassName;
	private String shardingFieldName;
	private Class<?> modelClass;
	private Class<?> modelExampleClass;
	
	public ShardingInfo() {
		super();
	}
	
	public String getMapperClassName() {
		return mapperClassName;
	}
	public void setMapperClassName(String mapperClassName) {
		this.mapperClassName = mapperClassName;
	}
	public String getModelClassName() {
		return modelClassName;
	}
	public void setModelClassName(String modelClassName) {
		this.modelClassName = modelClassName;
	}
	public String getModelExampleClassName() {
		return modelExampleClassName;
	}
	public void setModelExampleClassName(String modelExampleClassName) {
		this.modelExampleClassName = modelExampleClassName;
	}
	public String getShardingFieldName() {
		return shardingFieldName;
	}
	public void setShardingFieldName(String shardingFieldName) {
		this.shardingFieldName = shardingFieldName;
	}

	public Class<?> getModelClass() {
		return modelClass;
	}

	public void setModelClass(Class<?> modelClass) {
		this.modelClass = modelClass;
	}

	public Class<?> getModelExampleClass() {
		return modelExampleClass;
	}

	public void setModelExampleClass(Class<?> modelExampleClass) {
		this.modelExampleClass = modelExampleClass;
	}

	@Override
	public String toString() {
		return "ShardingInfo [mapperClassName=" + mapperClassName + ", modelClassName=" + modelClassName
				+ ", modelExampleClassName=" + modelExampleClassName + ", shardingFieldName=" + shardingFieldName
				+ ", modelClass=" + modelClass + ", modelExampleClass=" + modelExampleClass + "]";
	}


	
	
}
