package org.springlearning.aop.proxyfactory;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class FooBeforeAdvice implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object object) throws Throwable {
		System.out.println("-------------MethodBeforeAdvice Info start---------- " );
		// 打印出类的名称
		System.out.print("【target object Class=" + object.getClass().getSimpleName() + "】 ，");
		// 打印出方法名称
		System.out.print("【methodName=" + method+ "】 ，");
		// 打印出参数的值
		System.out.println("arg0=" + (String) args[0] + "】 ");
		System.out.println("-------------MethodBeforeAdvice Info end---------- " );
	}
}