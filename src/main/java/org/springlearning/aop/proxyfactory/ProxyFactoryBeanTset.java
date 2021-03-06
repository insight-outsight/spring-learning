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
       
    }
}