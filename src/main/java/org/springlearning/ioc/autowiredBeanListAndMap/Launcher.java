package org.springlearning.ioc.autowiredBeanListAndMap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
    
    public static void main(String[] args) {
       
       try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
    		   new String[]{"org/springlearning/ioc/autowiredBeanListAndMap/applicationContext.xml"});
       ) {
           BeanClient beanClient = ac.getBean("beanClient", BeanClient.class);
           beanClient.showName();
       }
       
    }
    
}