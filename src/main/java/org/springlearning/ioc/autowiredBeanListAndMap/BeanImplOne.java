package org.springlearning.ioc.autowiredBeanListAndMap;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)  
@Component  
public class BeanImplOne implements BeanInterface {

    @Override
    public void showName() {
        System.out.println("beanImplOne...");
    }  
  
}  