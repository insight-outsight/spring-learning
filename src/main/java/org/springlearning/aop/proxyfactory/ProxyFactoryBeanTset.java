package org.springlearning.aop.proxyfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class ProxyFactoryBeanTset {
    public static void main(String[] args) {
       ClassPathResource resource = new ClassPathResource("org/springlearning/aop/proxyfactory/applicationContext.xml");
       DefaultListableBeanFactory factory = new DefaultListableBeanFactory();  
       XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);  
       reader.loadBeanDefinitions(resource); 
           
       Foo foo = (Foo)factory.getBean("proxy11FactroyBean");
       foo.printName("Jimmy");
       foo.printAge("29");
       
       try (ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
    		   new String[]{"org/springlearning/aop/proxyfactory/applicationContext.xml","org/springlearning/aop/proxyfactory/applicationContext2.xml"});
       ) {
    	   Foo foo2 = ac.getBean("proxy11FactroyBean", Foo.class);
           foo2.printName("张三");
           foo2.printAge("79");
       }
    }
}