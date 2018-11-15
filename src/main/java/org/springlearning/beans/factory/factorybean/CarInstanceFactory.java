package org.springlearning.beans.factory.factorybean;

public class CarInstanceFactory {

    public Car make(String name){ 
        return new Car(name);
    }
    
}
