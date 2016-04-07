package org.springlearning.aop.proxyfactory;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class FooBeforeAdvice implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object object) throws Throwable {
		// 打印出类的名称
		System.out.print("【Class Name is " + object.getClass().getSimpleName() + " ，");
		// 打印出参数的值
		System.out.println("arg is " + (String) args[0] + "】 ");
	}
}