package PO73.Perepechin.wdad.data.managers;

import PO73.Perepechin.wdad.data.model.Building;
import PO73.Perepechin.wdad.data.model.Flat;
import PO73.Perepechin.wdad.data.model.Registration;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataManager extends Remote {
    double getBill(Building building, int flatNumber) throws RemoteException;

    Flat getFlat(Building building, int flatNumber) throws RemoteException;

    void setTariff(String tariffName, double newValue) throws RemoteException;

    void addRegistration(String street, int buildingNumber, int flatNumber, Registration registration) throws RemoteException;
}
