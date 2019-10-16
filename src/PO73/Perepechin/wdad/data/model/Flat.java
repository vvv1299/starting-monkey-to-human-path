package PO73.Perepechin.wdad.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Flat implements Serializable {
    private int number;
    private int personsQuantity;
    private double area;
    private List<Registration> registrations;

    public Flat() {
        this(0, 0, 0, new ArrayList<>());
    }

    public Flat(int number, int personsQuantity, double area, List<Registration> registrations) {
        this.number = number;
        this.personsQuantity = personsQuantity;
        this.area = area;
        this.registrations = registrations;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPersonsQuantity() {
        return personsQuantity;
    }

    public void setPersonsQuantity(int personsQuantity) {
        this.personsQuantity = personsQuantity;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public void addRegistration(Registration registration) {
        registrations.add(registration);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Flat " + number + "\nPersons: " + personsQuantity + "\nArea: " + area + "\nRegistrations:\n");
        registrations.forEach(builder::append);
        return builder.toString();
    }
}
