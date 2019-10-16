package PO73.Perepechin.wdad.learn.rmi;

import PO73.Perepechin.wdad.data.model.Building;
import PO73.Perepechin.wdad.data.model.Flat;
import PO73.Perepechin.wdad.data.model.Registration;
import PO73.Perepechin.wdad.learn.xml.XmlTask;

import java.io.File;
import java.util.Calendar;

public class XmlDataManagerImpl implements XmlDataManager {
    public static final File XML_FILE = new File("src/PO73/Perepechin/wdad/learn/xml/testHousekeeper.xml");

    private XmlTask xml;

    public XmlDataManagerImpl() {
        xml = new XmlTask();
        xml.setXmlFile(XML_FILE);
    }

    @Override
    public double getBill(Building building, int flatNumber) {
        return xml.getBill(building.getStreet(), building.getNumber(), flatNumber);
    }

    @Override
    public Flat getFlat(Building building, int flatNumber) {
        return xml.getFlat(building.getStreet(), building.getNumber(), flatNumber);
    }

    @Override
    public void setTariff(String tariffName, double newValue) {
        xml.setTariff(tariffName, newValue);
    }

    @Override
    public void addRegistration(String street, int buildingNumber, int flatNumber, Registration registration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(registration.getDate());
        xml.addRegistration(street,
                buildingNumber,
                flatNumber,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                registration.getColdWater(),
                registration.getHotWater(),
                registration.getElectricity(),
                registration.getGas());
    }
}