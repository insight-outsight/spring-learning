package org.springlearning.aop.hotswappabletargetsource;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HotSwappableTargetSourceTset2 {
	
	public static void main(String[] args) throws Exception {
		try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { HotSwappableTargetSourceTset2.class.getPackage().getName().replace(".", "/")+"/applicationContext.xml" });) {


			HotSwappableTargetSource hotSwapTargetSource = 
					(HotSwappableTargetSource)ac.getBean("hotSwapTargetSource");
			Object oldTarget = hotSwapTargetSource.getTarget();
			IDoable oldTargetImpl = (IDoable)oldTarget;
			oldTargetImpl.doSomething();
			
			Object oldTargetSame = hotSwapTargetSource.swap(new DoWrongImpl()/*new IDoable(){

				@Override
				public void doSomething() {
					System.out.println("变中文了吧。");
					
				}      
				
			}*/);  
			
			Object newTarget = hotSwapTargetSource.getTarget();
			
			IDoable oldTargetSameImpl = (IDoable)oldTargetSame;
			oldTargetSameImpl.doSomething();
			
			IDoable newTargetImpl = (IDoable) newTarget;
			newTargetImpl.doSomething();
			
			TestUtils.assertSame(oldTarget, oldTargetSame);  
			TestUtils.assertNotSame(oldTargetSame,newTarget); 
		}
	}

}