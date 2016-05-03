package org.springlearning.beans.factory.config;

import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  

/**
 * spring管理我们的类的时候有时候希望有些属性值是来源于一些方法调用的结果,使用
 * org.springframework.beans.factory.config.MethodInvokingFactoryBean类来生成需要注入的bean的属性
 * 
 * 
 * @author zcx
 *
 */
public class Test {  
    public static void main(String[] args) {  
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
        		Test.class.getPackage().getName().replace(".", "/")+"/applicationContext.xml");  
        MyBean myBean = (MyBean) ctx.getBean("myBean");  
        System.out.println(myBean);
        System.out.println(System.getProperty("myproperty.kk"));
        System.out.println(System.getProperty("logPath"));
    }  
}