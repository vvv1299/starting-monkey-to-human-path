package RPIS72.Voronina.wdad.data.managers;

import RPIS72.Voronina.wdad.data.model.Building;
import RPIS72.Voronina.wdad.data.model.Flat;
import RPIS72.Voronina.wdad.data.model.Registration;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataManager extends Remote {
    double getBill(Building building, int flatNumber) throws RemoteException;

    Flat getFlat(Building building, int flatNumber) throws RemoteException;

    void setTariff(String tariffName, double newValue) throws RemoteException;

    void addRegistration(String street, int buildingNumber, int flatNumber, Registration registration) throws RemoteException;
}
