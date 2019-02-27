package org.springlearning.aop.hotswappabletargetsource;

import org.springframework.aop.framework.Advised;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springlearning.aop.proxyfactory.Foo;

public class PrototypeTargetSourceTset {
	public static void main(String[] args) throws Exception {
		try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "org/springlearning/aop/targetsource/applicationContext.xml" });) {

			Object proxy = ac.getBean("targetProxy");
			Object targetObject0 = ((Advised) proxy).getTargetSource().getTarget();
			Object targetObject1 = ((Advised) proxy).getTargetSource().getTarget();
			Object targetObject2 = ((Advised) proxy).getTargetSource().getTarget();
			TestUtils.printClassName(targetObject0);
			TestUtils.printClassName(targetObject1);
			TestUtils.printClassName(targetObject2);
			TestUtils.assertNotSame(targetObject0, targetObject1);
			TestUtils.assertNotSame(targetObject1, targetObject2);
			TestUtils.assertNotSame(targetObject0, targetObject2);

		}
	}


}