package org.springlearning.beans.factory.factorybean;

public class Car {

    private String model;

    public Car() {
        super();
    }

    public Car(String model) {
        super();
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car [model=" + model + "]";
    }
    
    
}
