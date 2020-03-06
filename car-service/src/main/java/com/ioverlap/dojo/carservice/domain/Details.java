package com.ioverlap.dojo.carservice.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Details {

    private String model;

    @ManyToOne
    private Manufacturer manufacturer;

    private Integer numberOfDoors;

    private String fuelType;

    private String engine;

    private Integer mileage;

    private Integer modelYear;

    private Integer productionYear;

    private String externalColor;

    public Details() {
    }

    public Details(String model, Manufacturer manufacturer, Integer numberOfDoors, String fuelType, String engine, Integer mileage, Integer modelYear, Integer productionYear, String externalColor) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.engine = engine;
        this.mileage = mileage;
        this.modelYear = modelYear;
        this.productionYear = productionYear;
        this.externalColor = externalColor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getExternalColor() {
        return externalColor;
    }

    public void setExternalColor(String externalColor) {
        this.externalColor = externalColor;
    }

    @Override
    public String toString() {
        return "Details{" +
                ", model='" + model + '\'' +
                ", manufacturer=" + manufacturer +
                ", numberOfDoors=" + numberOfDoors +
                ", fuelType='" + fuelType + '\'' +
                ", engine='" + engine + '\'' +
                ", mileage=" + mileage +
                ", modelYear=" + modelYear +
                ", productionYear=" + productionYear +
                ", externalColor='" + externalColor + '\'' +
                '}';
    }
}
