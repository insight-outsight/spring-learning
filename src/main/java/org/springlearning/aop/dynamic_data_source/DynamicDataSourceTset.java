package org.springlearning.aop.dynamic_data_source;

import java.util.Date;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class DynamicDataSourceTset {
    public static void main(String[] args) {

       
       try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
    		   new String[]{"org/springlearning/aop/dynamic_data_source/applicationContext.xml"});
       ) {
    	   DynamicDataSourceDAOImpl dynamicDataSourceDAOImpl = ac.getBean(DynamicDataSourceDAOImpl.class);
    	   IndexUserPhone indexUserPhone = new IndexUserPhone();
    	   indexUserPhone.setCheckStatus(2);
    	   indexUserPhone.setCreateTime(new Date());
    	   indexUserPhone.setLastModifiedTime(new Date());
    	   indexUserPhone.setIsSelfManage(1);
    	   indexUserPhone.setMobliePhoneNumber("180000000");
    	   indexUserPhone.setRandCode("ewtwyw");
    	   indexUserPhone.setStatus(1);
    	   indexUserPhone.setUid(74324252l);
    	   try {
    		   ContextDataSourceKeyHolder.setDataSourceKey("eagle2.ods");
			dynamicDataSourceDAOImpl.insert(indexUserPhone );
		} catch (Exception e) {
			System.out.println("yyyyyyy");
			e.printStackTrace();
		}
       }
    }
}