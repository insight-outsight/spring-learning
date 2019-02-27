package org.springlearning.aop.hotswappabletargetsource;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HotSwappableTargetSourceTset {
	
	public static void main(String[] args) throws Exception {
		try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { HotSwappableTargetSourceTset2.class.getPackage().getName().replace(".", "/")+"/applicationContext.xml" });) {

			Object proxy = ac.getBean("doTaskProxy");
			IDoable initTarget = (IDoable)((Advised)proxy).getTargetSource().getTarget();
			initTarget.doSomething();
			HotSwappableTargetSource hotSwapTargetSource = 
					(HotSwappableTargetSource)ac.getBean("hotSwapTargetSource");
			Object oldTarget = hotSwapTargetSource.swap(new DoWrongImpl()/*new IDoable(){

				@Override
				public void doSomething() {
					System.out.println("变中文了吧。");
					
				}      
				
			}*/);  
			IDoable oldTarget2 = (IDoable)oldTarget;
			oldTarget2.doSomething();
			IDoable newTarget = (IDoable) ((Advised)proxy).getTargetSource().getTarget();
			newTarget.doSomething();
			TestUtils.assertSame(initTarget, oldTarget);  
			TestUtils.assertNotSame(initTarget,newTarget); 
		}
	}

}