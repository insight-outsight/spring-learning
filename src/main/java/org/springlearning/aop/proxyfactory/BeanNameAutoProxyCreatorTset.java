package org.springlearning.aop.proxyfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class BeanNameAutoProxyCreatorTset {
    public static void main(String[] args) {
       
       try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
    		   new String[]{"org/springlearning/aop/proxyfactory/applicationContext.xml","org/springlearning/aop/proxyfactory/applicationContext2.xml"});
       ) {
    	   Foo foo2 = ac.getBean("foo", Foo.class);
           foo2.printName("张三");
           foo2.printAge("79");
       }
    }
}