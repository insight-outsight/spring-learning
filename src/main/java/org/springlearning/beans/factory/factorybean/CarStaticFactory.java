package org.springlearning.beans.factory.factorybean;

public class CarStaticFactory {

    public static Car make(String name){ 
        return new Car(name);
    }
    
}
