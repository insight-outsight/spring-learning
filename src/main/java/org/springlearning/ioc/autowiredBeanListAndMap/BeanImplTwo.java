package org.springlearning.ioc.autowiredBeanListAndMap;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)  
@Component  
public class BeanImplTwo implements BeanInterface {

    @Override
    public void showName() {
        System.out.println("beanImplTwo...");
    }  
  
}  