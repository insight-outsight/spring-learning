package org.springlearning.aop.dynamic_data_source;  
  
import org.aspectj.lang.JoinPoint;  
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Before;  
import org.aspectj.lang.annotation.Pointcut;  
  
@Aspect  
public class DynamicDataSourceAspect {  
    @Pointcut("execution (public org.springlearning.aop.dynamic_data_source.DAOImpl..*.*(..))")  
    public void serviceExecution(){}  
      
    @Before("serviceExecution()")  
    public void setDynamicDataSource(JoinPoint jp) {  
        for(Object o : jp.getArgs()) {  
            //处理具体的逻辑 ，根据具体的境况CustomerContextHolder.setCustomerType()选取DataSource  
        }  
    }  
} 