//package org.springlearning.aop.dynamic_data_source.aspect;  
//  
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.ibatis.annotations.Param;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;  
//import org.aspectj.lang.annotation.Before;  
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import com.google.common.collect.Lists;
//
//import org.springlearning.aop.dynamic_data_source.ContextDataSourceKeyHolder;
//import org.springlearning.aop.dynamic_data_source.aspect.exception.ServiceException;
//
//import com.yanke.backend.api.dal.model.IndexUserPhone;
//import com.yanke.backend.api.dal.model.IndexUserPhoneExample;
//import com.yanke.backend.api.dal.model.IndexUserPhoneExample.Criteria;
//import com.yanke.backend.api.dal.model.IndexUserPhoneExample.Criterion;
//
//  
////@Aspect
////@Component
//public class DynamicDataSourceAspect {
//	
//	public final static String modelClassName = "com.yanke.backend.api.dal.model.IndexUserPhone";
//	public final static String modelExampleClassName = "com.yanke.backend.api.dal.model.IndexUserPhoneExample";
///*    
//   @Pointcut("execution (public com.yanke.backend.api.dal.dao.IndexUserPhoneMapper.insert(..)")  
//    public void insertPointcut(){}  
//      
//    @Before("serviceExecution()")  
//    public void setDynamicDataSource(JoinPoint jp) {  
//        for(Object o : jp.getArgs()) {  
//            //处理具体的逻辑 ，根据具体的境况CustomerContextHolder.setCustomerType()选取DataSource  
//        }  
//    }
//*/
//	
//	@Around(value="execution(public * com.yanke.backend.api.dal.dao.IndexUserPhoneMapper.insert("+modelClassName+")) && args(indexUserPhone)",argNames="indexUserPhone")
//	public Object applyOnInsert(ProceedingJoinPoint joinPoint,IndexUserPhone indexUserPhone) throws Throwable {
//		System.out.println("---------------------------------");
//		System.out.println("joinPoint.getTarget()="+joinPoint.getTarget());
//		System.out.println("joinPoint.getSignature().getName()="+joinPoint.getSignature().getName());
//        for(Object o : joinPoint.getArgs()) {  
//    		System.out.println("arg-"+o);
//        }  
//        System.out.println("---------------------------------");
//      /**  
//      //另一种实现方式：使用策略模式
//      //1.根据UserGameInfo.uid 进行数据源路由选择  
//      //DataSourceRouter.setRouterStrategy(  
//      //          RouterStrategy.SRATEGY_TYPE_CLUSTER ,  
//      //          null,  
//      //         userGameInfo.getUid());  
//      */  
//		Integer result = -1;
//		try{			
//			ContextDataSourceKeyHolder.setDataSourceKey(indexUserPhone.getMobilePhoneNumber());
//			result = (Integer)joinPoint.proceed();
//		}	
//		catch (Throwable throwable) {
//			throw new ServiceException("IndexUserPhoneMapper.insert Aspect Exception,Reason:"+throwable.getMessage(),throwable);
//		}
//		return result;
//
//	}
//	
//	
//	@Before(value="execution(public java.util.List<"+modelClassName+"> com.yanke.backend.api.dal.dao.IndexUserPhoneMapper.selectByExample("+modelExampleClassName+")) && args(example)",argNames="example")
//	public void applyOnSelectByExample(IndexUserPhoneExample example) throws Throwable {
//		
//		try{
//			boolean hasMobilePhoneNumberClause = false;
//			Iterator<Criteria> iterator = example.getOredCriteria().iterator();
//			while(iterator.hasNext()){
//				List<Criterion> getCriteriaResult =  iterator.next().getCriteria();
//				for(Criterion criterion:getCriteriaResult){
//					//mobile_phone_number =
//					if(criterion.isSingleValue() && criterion.getCondition().contains("mobile_phone_number")){
//						ContextDataSourceKeyHolder.setDataSourceKey(String.valueOf(criterion.getValue()));
//						hasMobilePhoneNumberClause = true;
//					}
//				}
//			}
//			if(!hasMobilePhoneNumberClause){
//				throw new IllegalArgumentException("no mobile_phone_number in query parmameter");
//			}
//		}	
//		catch (Throwable throwable) {
//			throw new ServiceException("IndexUserPhoneMapper.selectByExample Aspect Exception,Reason:"+throwable.getMessage(),throwable);
//		}
//
//
//	}
//	
//	@Before(value="execution(public * com.yanke.backend.api.dal.dao.IndexUserPhoneMapper.updateByExampleSelective("+modelClassName+","+modelExampleClassName+")) && args(model,example)",argNames="model,example")
//	public void applyOnUpdateByExampleSelective(IndexUserPhone model,IndexUserPhoneExample example) throws Throwable {
//		
//		try{
//			boolean hasMobilePhoneNumberClause = false;
//			Iterator<Criteria> iterator = example.getOredCriteria().iterator();
//			while(iterator.hasNext()){
//				List<Criterion> getCriteriaResult =  iterator.next().getCriteria();
//				for(Criterion criterion:getCriteriaResult){
//					//mobile_phone_number =
//					if(criterion.isSingleValue() && criterion.getCondition().contains("mobile_phone_number")){
//						ContextDataSourceKeyHolder.setDataSourceKey(String.valueOf(criterion.getValue()));
//						hasMobilePhoneNumberClause = true;
//					}
//				}
//			}
//			if(!hasMobilePhoneNumberClause){
//				throw new IllegalArgumentException("no mobile_phone_number in update parmameter");
//			}
//		}	
//		catch (Throwable throwable) {
//			throw new ServiceException("IndexUserPhoneMapper.updateByExampleSelective Aspect Exception,Reason:"+throwable.getMessage(),throwable);
//		}
//		
//		
//	}
//	
//
//	
//} 