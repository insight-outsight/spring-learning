package org.springlearning.aop.proxyfactory;

import org.springframework.aop.framework.ProxyFactory;

public class ProxyFactoryTset {
	public static void main(String[] args) {
		Foo foo = new Foo();
		FooBeforeAdvice advice = new FooBeforeAdvice();
		// Spring提供的代理工厂
		ProxyFactory pf = new ProxyFactory();
		// 设置代理目标
		pf.setTarget(foo);
		// 为代理目标添加增强
		pf.addAdvice(advice);
		// 生成代理实例
		Foo proxy = (Foo) pf.getProxy();
		proxy.printName("Tony");
		proxy.printAge("27");
	}
}