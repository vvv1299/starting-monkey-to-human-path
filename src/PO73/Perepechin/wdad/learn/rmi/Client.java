package PO73.Perepechin.wdad.learn.rmi;

import PO73.Perepechin.wdad.data.managers.PreferencesManager;
import PO73.Perepechin.wdad.data.model.Building;
import PO73.Perepechin.wdad.data.model.Registration;
import PO73.Perepechin.wdad.utils.BindedObject;
import PO73.Perepechin.wdad.utils.PreferencesManagerConstants;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class Client {
    public static void main(String[] args) {
        PreferencesManager preferencesManager = PreferencesManager.getInstance();
        int port = Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRY_PORT_KEY));
        String host = preferencesManager.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS_KEY);
        Registry registry;
        XmlDataManager xmlDataManager;

        try {
            registry = LocateRegistry.getRegistry(host, port);
            String bindedObjectName = "";
            for (BindedObject object : preferencesManager.getBindedObjects()) {
                if (object.getClassName().equals("PO73.Perepechin.wdad.learn.rmi.XmlDataManager")) {
                    bindedObjectName = object.getName();
                    break;
                }
            }
            xmlDataManager = (XmlDataManager) registry.lookup(bindedObjectName);
            System.out.println("Client is running");

            Building building = new Building("Baker Street", 221);
            Registration registrationToAdd = new Registration(new Date(), 5, 10, 15, 20);

            System.out.println(xmlDataManager.getBill(building, 3));
            xmlDataManager.setTariff("gas", 25.5);
            System.out.println(xmlDataManager.getFlat(building, 3));
            xmlDataManager.addRegistration("Baker Street", 221, 3, registrationToAdd);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.err.println("Cannot find binded object");
        }
    }
}
