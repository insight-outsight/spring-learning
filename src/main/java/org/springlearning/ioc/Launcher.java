package org.springlearning.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
    
    public static void main(String[] args) {
       
       try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
    		   new String[]{"org/springlearning/ioc/applicationContext.xml"});
       ) {
           HelloClient helloClient = ac.getBean("helloClient", HelloClient.class);
           helloClient.showVersion();
           helloClient.textHello();
       }
       
    }
    
}