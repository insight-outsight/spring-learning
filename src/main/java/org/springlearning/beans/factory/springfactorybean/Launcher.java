package org.springlearning.beans.factory.springfactorybean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
    
    public static void main(String[] args) {
       
       try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
    		   new String[]{"org/springlearning/beans/factory/springfactorybean/applicationContext.xml"});
       ) {
           Student s1 = ctx.getBean("stuOne", Student.class);
           System.out.println(s1.getName());
           Student s2 = ctx.getBean("stuOne", Student.class);
           System.out.println(s2.getName());

           System.out.println(s1 == s2);

           Student s3 = ctx.getBean("stuTwo", Student.class);
           System.out.println(s3.getName());
           System.out.println(s1 == s3);
       }
       
    }
    
}