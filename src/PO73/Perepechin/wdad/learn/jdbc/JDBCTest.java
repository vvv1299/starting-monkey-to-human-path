package PO73.Perepechin.wdad.learn.jdbc;

import PO73.Perepechin.wdad.data.managers.JDBCDataManager;
import PO73.Perepechin.wdad.data.model.Building;
import PO73.Perepechin.wdad.data.model.Flat;
import PO73.Perepechin.wdad.data.model.Registration;

import java.util.Date;

public class JDBCTest {
    public static void main(String[] args) {
        JDBCDataManager dataManager = new JDBCDataManager();

        Building testBuilding = new Building("Революционная", 54);
        Registration testRegistration = new Registration(new Date(), 30.1, 90.10, 14.50, 54.27);

        System.out.println(dataManager.getBill(testBuilding, 256));
        Flat flat = dataManager.getFlat(testBuilding, 256);
        System.out.println(flat);
        dataManager.setTariff("Холодная вода", 0.70);
        dataManager.addRegistration("Революционная", 54, 256, testRegistration);
    }
}
