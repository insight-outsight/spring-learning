package org.springlearning.aop.beanNameAutoProxyCreator;

import java.util.Date;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class LaunchApplyTranscationUsingBeanNameAutoProxyCreator {
	
    public static void main(String[] args) {
       try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
    		   new String[]{"org/springlearning/aop/beanNameAutoProxyCreator/applicationContext.xml"});
       ) {
    	  

    	   try {
    		   //被beanNameAutoProxyCreator代理后实际类型已经不再是BillServiceImpl], 
    		   //but was actually of type [com.sun.proxy.$Proxy4]
//        	   BillService billService = ac.getBean("billService",BillServiceImpl.class);
        	   BillService billService = (BillService)ac.getBean("billService");
        	   billService.saveBill();
    		   System.out.println("OK");
			} catch (Exception e) {
				System.out.println("测试LaunchApplyTranscationUsingBeanNameAutoProxyCreator出现异常");
				e.printStackTrace();
			}
       }
    }
    
}