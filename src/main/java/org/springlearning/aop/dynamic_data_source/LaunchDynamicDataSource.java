package org.springlearning.aop.dynamic_data_source;

import java.util.Date;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class LaunchDynamicDataSource {
	
    public static void main(String[] args) {
       try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
    		   new String[]{"org/springlearning/aop/dynamic_data_source/applicationContext.xml"});
       ) {
//    	   DynamicBoneCPDataSource dynamicDataSource = ac.getBean(DynamicBoneCPDataSource.class);
    	  
    	   DynamicDataSourceDAOImpl dynamicDataSourceDAOImpl = ac.getBean(DynamicDataSourceDAOImpl.class);
    	   IndexUserPhone indexUserPhone = new IndexUserPhone();
    	   indexUserPhone.setCheckStatus(624);
    	   indexUserPhone.setCreateTime(new Date());
    	   indexUserPhone.setLastModifiedTime(new Date());
    	   indexUserPhone.setIsSelfManage(2);
    	   indexUserPhone.setMobliePhoneNumber("19900003412");
    	   indexUserPhone.setRandCode("笑话");
    	   indexUserPhone.setStatus(2);
    	   indexUserPhone.setUid(66324250l);
    	   try {
    		   ContextDataSourceKeyHolder.setDataSourceKey("eagle2.business");
    		   long insertedId = dynamicDataSourceDAOImpl.insert(indexUserPhone );
    		   System.out.println("insert OK ,id="+insertedId);
			} catch (Exception e) {
				System.out.println("测试DynamicDataSource出现异常");
				e.printStackTrace();
			}
       }
    }
    
}