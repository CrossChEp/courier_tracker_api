package com.example.courierTracker.courierTracker.model.courier;

public class AddCourierTypeModel {
    private String typeName;
    private double courierCapacity;

    public AddCourierTypeModel() {
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getCourierCapacity() {
        return courierCapacity;
    }

    public void setCourierCapacity(double courierCapacity) {
        this.courierCapacity = courierCapacity;
    }
}
