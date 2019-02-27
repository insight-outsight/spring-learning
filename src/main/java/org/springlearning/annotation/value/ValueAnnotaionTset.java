package org.springlearning.annotation.value;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ValueAnnotaionTset {
    
    public static void main(String[] args) {
       
       try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
    		   new String[]{"org/springlearning/annotation/value/applicationContext.xml"});
       ) {
           ValueAnnotiaonClient valueAnnotiaonClient = ac.getBean("valueAnnotiaonClient", ValueAnnotiaonClient.class);
           System.out.println(valueAnnotiaonClient.getAppVersionNum());
           System.out.println(valueAnnotiaonClient.getAppTypeNum());
           System.out.println(valueAnnotiaonClient.getAppName());
           System.out.println(valueAnnotiaonClient.getLiteralFoo());
           System.out.println(valueAnnotiaonClient.getBar1());
           System.out.println(valueAnnotiaonClient.getCount());
           System.out.println(valueAnnotiaonClient.getMax());
       }
       
    }
    
}