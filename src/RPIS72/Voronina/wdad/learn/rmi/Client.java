package RPIS72.Voronina.wdad.learn.rmi;

import RPIS72.Voronina.wdad.data.managers.DataManager;
import RPIS72.Voronina.wdad.data.managers.PreferencesManager;
import RPIS72.Voronina.wdad.data.model.Building;
import RPIS72.Voronina.wdad.data.model.Registration;
import RPIS72.Voronina.wdad.utils.BindedObject;
import RPIS72.Voronina.wdad.utils.PreferencesManagerConstants;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class Client {
    public static void main(String[] args) {
        PreferencesManager preferencesManager = PreferencesManager.getInstance();
        int port = Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstants.RMI_REGISTRY_PORT_KEY));
        String host = preferencesManager.getProperty(PreferencesManagerConstants.RMI_REGISTRY_ADDRESS_KEY);
        Registry registry;
        DataManager xmlDataManager;

        try {
            registry = LocateRegistry.getRegistry(host, port);
            String bindedObjectName = "";
            for (BindedObject object : preferencesManager.getBindedObjects()) {
                if (object.getClassName().equals("RPIS72.Voronina.wdad.learn.rmi.XmlDataManager")) {
                    bindedObjectName = object.getName();
                    break;
                }
            }
            xmlDataManager = (DataManager) registry.lookup(bindedObjectName);
            System.out.println("Client is running");

            Building building = new Building("Baker Street", 221);
            Registration registrationToAdd = new Registration(new Date(), 5, 10, 15, 20);

            System.out.println(xmlDataManager.getBill(building, 3));
            xmlDataManager.setTariff("gas", 25.5);
            System.out.println(xmlDataManager.getFlat(building, 3));
            xmlDataManager.addRegistration("Baker Street", 221, 3, registrationToAdd);
        } catch (RemoteException e) {
            System.err.println("Connection error");
        } catch (NotBoundException e) {
            System.err.println("Cannot find binded object");
        }
    }
}
