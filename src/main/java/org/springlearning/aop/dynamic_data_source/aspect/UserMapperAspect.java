package com.yanke.backend.api.dal.aspect;  
  
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Before;  
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.yanke.backend.api.dal.dynamic_data_source.ContextDataSourceKeyHolder;
import com.yanke.backend.api.dal.model.IndexUserPhone;
import com.yanke.backend.api.dal.model.IndexUserPhoneExample;
import com.yanke.backend.api.dal.model.IndexUserPhoneExample.Criteria;
import com.yanke.backend.api.dal.model.IndexUserPhoneExample.Criterion;
import com.yanke.backend.api.exception.ServiceException;  
  
//@Aspect
//@Component
public class UserMapperAspect {
	
	public final static String modelClassName = "com.yanke.backend.api.dal.model.IndexUserPhone";
	public final static String modelExampleClassName = "com.yanke.backend.api.dal.model.IndexUserPhoneExample";
/*    @Pointcut("execution (public com.yanke.backend.api.dal.dao.IndexUserPhoneMapper.insert(..)")  
    public void insertPointcut(){}  
      
    @Before("serviceExecution()")  
    public void setDynamicDataSource(JoinPoint jp) {  
        for(Object o : jp.getArgs()) {  
            //处理具体的逻辑 ，根据具体的境况CustomerContextHolder.setCustomerType()选取DataSource  
        }  
    }
    */
	@Around(value="execution(public * com.yanke.backend.api.dal.dao.IndexUserPhoneMapper.insert("+modelClassName+")) && args(indexUserPhone)",argNames="indexUserPhone")
	public Object applyOnInsert(ProceedingJoinPoint joinPoint,IndexUserPhone indexUserPhone) throws Throwable {
		System.out.println(joinPoint.getKind());
        for(Object o : joinPoint.getArgs()) {  
            //处理具体的逻辑 ，根据具体的境况CustomerContextHolder.setCustomerType()选取DataSource  
        }  
        
		Integer result = -1;
		try{			
			ContextDataSourceKeyHolder.setDataSourceKey(indexUserPhone.getMobilePhoneNumber());
			result = (Integer)joinPoint.proceed();
		}	
		catch (Throwable throwable) {
			throw new ServiceException("IndexUserPhoneMapper.insert Aspect Exception,Reason:"+throwable.getMessage(),throwable);
		}
		return result;

	}
	
	
	@Before(value="execution(public java.util.List<"+modelClassName+"> com.yanke.backend.api.dal.dao.IndexUserPhoneMapper.selectByExample("+modelExampleClassName+")) && args(example)",argNames="example")
	public void applyOnSelectByExample(IndexUserPhoneExample example) throws Throwable {
		
		try{
			boolean hasMobilePhoneNumberClause = false;
			Iterator<Criteria> iterator = example.getOredCriteria().iterator();
			while(iterator.hasNext()){
				List<Criterion> getCriteriaResult =  iterator.next().getCriteria();
				for(Criterion criterion:getCriteriaResult){
					//mobile_phone_number =
					if(criterion.isSingleValue() && criterion.getCondition().contains("mobile_phone_number")){
						ContextDataSourceKeyHolder.setDataSourceKey(String.valueOf(criterion.getValue()));
						hasMobilePhoneNumberClause = true;
					}
				}
			}
			if(!hasMobilePhoneNumberClause){
				throw new IllegalArgumentException("no mobile_phone_number in query parmameter");
			}
		}	
		catch (Throwable throwable) {
			throw new ServiceException("IndexUserPhoneMapper.selectByExample Aspect Exception,Reason:"+throwable.getMessage(),throwable);
		}


	}
	

	/*
	@AfterReturning(value = "execution(public * com.xxx.module.repository.ActivityApplyIdRepository.insertBatch(java.util.List)) && args(activityApplyIdList)",argNames="activityApplyIdList")
	public void applyOnInsertBatch(List<ActivityApplyId> activityApplyIdList) throws Throwable {
		if (activityApplyIdList != null && activityApplyIdList.size() > 0 && activityApplyIdList.get(0)!=null) {
			Long activitySerialNumber = activityApplyIdList.get(0).getActivitySerialNumber();
			ActivityApplyIdUnion activityApplyIdUnion = new ActivityApplyIdUnion();
			activityApplyIdUnion.setActivitySerialNumber(activitySerialNumber);
			activityApplyIdUnion.setActivityApplyIds(activityApplyIdList);
			putToCacheIfNotNull(activityApplyIdUnion);	
		} 
	}
	
		@AfterReturning(value = "execution(public * com.dangdang.coupon.module.repository.ActivityRepository.insert(..)) && args(activity)",argNames="activity")
	public void applyOnInsert(Activity activity) throws Throwable{
		putToCacheIfNotNull(activity);
	}


	*/

	
} 