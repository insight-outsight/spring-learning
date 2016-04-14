package org.springlearning.aop.proxyfactory;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class FooAroundAdvice implements MethodInterceptor {
	private Set set = new HashSet();

	public Object invoke(MethodInvocation method) throws Throwable {
		try {
			set.add(method);
			System.out.println("============around before==========");
			Object[] args = method.getArguments();
	        for(Object o : args) {  
	    		System.out.println("arg-"+o);
	        } 
			Method methodMethod = method.getMethod();
			System.out.println("methodMethod.getName()="+methodMethod.getName());
			System.out.println("methodMethod.getDeclaringClass()="+methodMethod.getDeclaringClass());
			System.out.println("methodMethod.getDeclaringClass().getName()="+methodMethod.getDeclaringClass().getName());
			System.out.println("============around before==========");
			Object result = method.proceed();
			System.out.println("============around after==========");
			return result;
		} catch (IllegalArgumentException e) {
			System.out.println("Around method : throw  an  exception ");
			throw e;
		}
	}
}