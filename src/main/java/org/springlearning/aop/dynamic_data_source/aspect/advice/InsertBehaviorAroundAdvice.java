package com.yanke.backend.api.dal.aspect.advice;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import com.yanke.backend.api.dal.dynamic_data_source.ContextDataSourceKeyHolder;
import com.yanke.backend.api.dal.dynamic_data_source.ShardingInfo;
import com.yanke.backend.api.dal.dynamic_data_source.ShardingInfoHolder;

//@Component
public class InsertBehaviorAroundAdvice implements MethodInterceptor {

	public Object invoke(MethodInvocation method) throws Throwable {
		try {
			System.out.println("============around before==========");
			if(method!=null){
				Method methodMethod = method.getMethod();
				if(methodMethod!=null){
					String methodName = methodMethod.getName();
					System.out.println("methodMethod.getName()="+methodName);
					if("insert".equals(methodName)){
						Class<?> mapperClass = methodMethod.getDeclaringClass();
						System.out.println("methodMethod.getDeclaringClass()="+methodMethod.getDeclaringClass());
						ShardingInfo shardingInfo =
								ShardingInfoHolder.getShardingInfoByMapperClassName(mapperClass.getName());
						if(shardingInfo!=null){
							Object[] args = method.getArguments();
					        for(Object arg : args) {
					        	if(shardingInfo.getModelClassName().equals(arg.getClass().getName())){
					        		System.out.println("arg-"+arg);
					        		Field shardingField = shardingInfo.getModelClass().getDeclaredField(shardingInfo.getShardingFieldName());
					        		if(!shardingField.isAccessible()){
					        			shardingField.setAccessible(true);
					        		}
					        		String shardingFieldValue = String.valueOf(shardingField.get(arg));
					        		ContextDataSourceKeyHolder.setDataSourceKey(shardingFieldValue);
					        	}
					    		
					        } 
						}
					}
				}
			}
			Object result = method.proceed();
			System.out.println("============around after==========");
			return result;
		} catch (Throwable throwable) {
			System.out.println("Around method : throw  an  exception ");
			throwable.printStackTrace();
			throw throwable;
		}
	}
	
}