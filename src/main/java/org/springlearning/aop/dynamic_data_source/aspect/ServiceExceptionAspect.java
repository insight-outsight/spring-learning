package com.yanke.backend.api.dal.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yanke.backend.api.exception.ServiceException;


@Aspect 
@Component
public class ServiceExceptionAspect {

//	private final static Logger LOG = LoggerFactory.getLogger(ServiceExceptionAspect.class);



	@Around("execution(public * com.yanke.backend.api.service.*ServiceImpl.*(..))")
	public Object handle(ProceedingJoinPoint joinPoint) throws Throwable{
		
		try{
			Object result = (Object)joinPoint.proceed();			
            return result;
		}	
		catch (Throwable throwable) {
			throw new ServiceException("Service Exception,Reason:"+throwable.getMessage(),throwable);
		}
        
	}


}
