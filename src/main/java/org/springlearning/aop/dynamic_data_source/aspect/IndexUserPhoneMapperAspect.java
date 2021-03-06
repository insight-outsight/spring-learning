//package org.springlearning.aop.dynamic_data_source.aspect;  
//  
//
//import java.util.Iterator;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Before;
//import org.springlearning.aop.dynamic_data_source.IndexUserPhone;
//
//  
////@Aspect
////@Component
//public class IndexUserPhoneMapperAspect extends BaseMapperAspect<IndexUserPhone,IndexUserPhoneExample>{
//	
//	public final static String modelClassName = "com.yanke.backend.api.dal.model.IndexUserPhone";
//	public final static String modelExampleClassName = "com.yanke.backend.api.dal.model.IndexUserPhoneExample";
//	public final static String mapperClassName = "com.yanke.backend.api.dal.dao.IndexUserPhoneMapper"; 
//	
//	@Around(value="execution(public * "+mapperClassName+".insert("+modelClassName+")) && args(indexUserPhone)",argNames="indexUserPhone")
//	public Object aroundInsert(ProceedingJoinPoint joinPoint,IndexUserPhone indexUserPhone) throws Throwable {       
//		return setDataSourceKeyByModel(joinPoint,indexUserPhone);
//	}
//	
//	
//	@Before(value="execution(public java.util.List<"+modelClassName+"> "+mapperClassName+".selectByExample("+modelExampleClassName+")) && args(example)",argNames="example")
//	public void beforeSelectByExample(IndexUserPhoneExample example) throws Throwable {
//		setDataSourceKeyByExample(example);
//	}
//	
//	@Before(value="execution(public * com.yanke.backend.api.dal.dao.IndexUserPhoneMapper.updateByExampleSelective("+modelClassName+","+modelExampleClassName+")) && args(model,example)",argNames="model,example")
//	public void applyOnUpdateByExampleSelective(IndexUserPhone model,IndexUserPhoneExample example) throws Throwable {
//		setDataSourceKeyByExample(example);
//	}
//
//	@Override
//	protected String getShardingKey(IndexUserPhone indexUserPhone) {
//		return indexUserPhone.getMobilePhoneNumber();
//	}
//
//	@Override
//	protected String getModelMapperClassName() {
//		return mapperClassName;
//	}
//
//	@Override
//	protected Iterator<Criteria> getExampleIterator(IndexUserPhoneExample example) {
//		return example.getOredCriteria().iterator();
//	}
//
//	@Override
//	protected String getShardingKeyField() {
//		return "mobile_phone_number";
//	}	
//	
//} 