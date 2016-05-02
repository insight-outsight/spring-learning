package org.springlearning.beans.factory.config;
public class MyBeanNameProvider {  
    public static String getName() {  
        return "n-" + System.currentTimeMillis();  
    }  
}