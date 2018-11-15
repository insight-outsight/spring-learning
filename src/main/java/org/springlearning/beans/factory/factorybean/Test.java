package org.springlearning.beans.factory.factorybean;

import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  

/**
 * 
 * @author zcx
 *
 */
public class Test {  
    public static void main(String[] args) {  
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
        		Test.class.getPackage().getName().replace(".", "/")+"/applicationContext.xml");  
        Car car1 = (Car) ctx.getBean("car1");  
        System.out.println(car1);
        Car car2 = (Car) ctx.getBean("car2");  
        System.out.println(car2);
    }  
}