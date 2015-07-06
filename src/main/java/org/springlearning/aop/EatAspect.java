package org.springlearning.aop;


import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;


//@Aspect 
//@Component
public class EatAspect {

	private final static Logger logger = LoggerFactory.getLogger(EatAspect.class);
	
	
	
//	@Pointcut("execution(public * com.dangdang.coupon.module.repository.ActivityApplyIdRepository.selectBySerialNumber*(..))")
    public void selectBySerialNumberXPointcut(){}
	
//	@Around("selectBySerialNumberXPointcut()")
	public Object applyAop(ProceedingJoinPoint joinPoint) throws Throwable{

		try{
			Object[] args = joinPoint.getArgs();
			if(args != null && args.length>0){
				Class<?> firstArgsClassType = args[0].getClass();
				logger.debug("argggs size={},arg0={},arg0Type={}",args.length,args[0],firstArgsClassType.getName());
			}
			Object result = (Object)joinPoint.proceed();			
            return result;
		}	
		catch (Throwable throwable) {
			throw new Exception("Cache Aspect Exception,Reason:"+throwable.getMessage(),throwable);
		}
        
	}

}