package org.springlearning.aop.dynamic_data_source.aspect;  
  
import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;

import com.yanke.backend.api.dal.dynamic_data_source.ContextDataSourceKeyHolder;

import com.yanke.backend.api.dal.model.IndexUserPhoneExample.Criteria;
import com.yanke.backend.api.dal.model.IndexUserPhoneExample.Criterion;
import com.yanke.backend.api.exception.ServiceException;  
  

public abstract class BaseMapperAspect<MODEL,EXAMPLE> {


	public Object setDataSourceKeyByModel(ProceedingJoinPoint joinPoint,MODEL indexUserPhone) throws Throwable {
        
		System.out.println("---------------------------------");

		System.out.println("joinPoint.getTarget()="+joinPoint.getTarget().getClass());
		System.out.println("joinPoint.getSignature().getName()="+joinPoint.getSignature().getName());
		System.out.println("joinPoint.getSignature().getModifiers()="+joinPoint.getSignature().getModifiers());
		System.out.println("joinPoint.getSignature().getDeclaringTypeName()="+joinPoint.getSignature().getDeclaringTypeName());
		System.out.println("joinPoint.getSignature().getDeclaringType()="+joinPoint.getSignature().getDeclaringType());
		System.out.println("joinPoint.getSignature().getClass()="+joinPoint.getSignature().getClass());

        for(Object o : joinPoint.getArgs()) {  
    		System.out.println("arg-"+o);
        }  
        System.out.println("---------------------------------");
        
		Integer result = -1;
		try{			
			ContextDataSourceKeyHolder.setDataSourceKey(getShardingKey(indexUserPhone));
			result = (Integer)joinPoint.proceed();
		}	
		catch (Throwable throwable) {
			throw new ServiceException(getModelMapperClassName()+".insert Aspect Exception,Reason:"+throwable.getMessage(),throwable);
		}
		return result;

	}
	


	public void setDataSourceKeyByExample(EXAMPLE example) throws Throwable {
		
		try{
			boolean hasShardingKeyFieldClause = false;
			Iterator<Criteria> iterator = getExampleIterator(example);
			while(iterator.hasNext()){
				List<Criterion> getCriteriaResult =  iterator.next().getCriteria();
				for(Criterion criterion:getCriteriaResult){
					if(criterion.isSingleValue() && criterion.getCondition().contains(getShardingKeyField())){
						ContextDataSourceKeyHolder.setDataSourceKey(String.valueOf(criterion.getValue()));
						hasShardingKeyFieldClause = true;
					}
				}
			}
			if(!hasShardingKeyFieldClause){
				throw new IllegalArgumentException("no "+getShardingKeyField() +" in query parmameter");
			}
		}	
		catch (Throwable throwable) {
			throw new ServiceException(getModelMapperClassName()+".selectByExample Aspect Exception,Reason:"+throwable.getMessage(),throwable);
		}


	}

	protected abstract String getShardingKey(MODEL indexUserPhone);
	protected abstract String getShardingKeyField();
	protected abstract String getModelMapperClassName();
	protected abstract  Iterator<Criteria> getExampleIterator(EXAMPLE example);


	
} 