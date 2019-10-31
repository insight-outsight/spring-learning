package org.springlearning.beans.factory.springfactorybean;

import org.springframework.beans.factory.FactoryBean;

public class StudentFactoryBean implements FactoryBean<Student> {

    private String name;

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Student getObject() throws Exception {
        return new Student(name);
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}