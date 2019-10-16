package PO73.Perepechin.wdad.learn.rmi;

import PO73.Perepechin.wdad.data.managers.PreferencesManager;
import PO73.Perepechin.wdad.utils.PreferencesManagerConstants;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static final String BINDING_NAME = "XmlDataManager";

    public static void main(String[] args) {
        PreferencesManager preferencesManager = PreferencesManager.getInstance();
        int port = Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRY_PORT_KEY));
        XmlDataManagerImpl xmlDataManager = new XmlDataManagerImpl();
        Registry registry;
        Remote stub;

        try {
            if (preferencesManager.getProperty(PreferencesManagerConstants.CREATE_REGISTRY_KEY).equals("yes")) {
                registry = LocateRegistry.createRegistry(port);
            } else {
                registry = LocateRegistry.getRegistry(port);
            }

            stub = UnicastRemoteObject.exportObject(xmlDataManager, port);
            registry.bind(BINDING_NAME, stub);
            preferencesManager.addBindedObject(BINDING_NAME, "PO73.Perepechin.wdad.learn.rmi.XmlDataManager");
            System.out.println("Server is running");
            System.out.println("Port: " + port);
        } catch (RemoteException e) {
            System.err.println("Something gone wrong!");
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            System.err.println("XmlDataManager is already binded");
        }
    }
}
