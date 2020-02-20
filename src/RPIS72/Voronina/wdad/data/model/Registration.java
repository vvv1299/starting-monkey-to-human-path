package RPIS72.Voronina.wdad.data.model;

import java.io.Serializable;
import java.util.Date;

public class Registration implements Serializable {
    private Date date;
    private double coldWater;
    private double hotWater;
    private double electricity;
    private double gas;

    public Registration() {
        this(new Date(), 0, 0, 0, 0);
    }

    public Registration(Date date, double coldWater, double hotWater, double electricity, double gas) {
        this.date = date;
        this.coldWater = coldWater;
        this.hotWater = hotWater;
        this.electricity = electricity;
        this.gas = gas;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getColdWater() {
        return coldWater;
    }

    public void setColdWater(double coldWater) {
        this.coldWater = coldWater;
    }

    public double getHotWater() {
        return hotWater;
    }

    public void setHotWater(double hotWater) {
        this.hotWater = hotWater;
    }

    public double getElectricity() {
        return electricity;
    }

    public void setElectricity(double electricity) {
        this.electricity = electricity;
    }

    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

    public double bill() {
        return coldWater + hotWater + electricity + gas;
    }

    @Override
    public String toString() {
        return date +
                "\ncoldWater=" + coldWater +
                ", hotWater=" + hotWater +
                ", electricity=" + electricity +
                ", gas=" + gas + "\n";
    }
}
