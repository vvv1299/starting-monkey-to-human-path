package PO73.Perepechin.wdad.data.managers;

import PO73.Perepechin.wdad.data.model.Building;
import PO73.Perepechin.wdad.data.model.Flat;
import PO73.Perepechin.wdad.data.model.Registration;
import PO73.Perepechin.wdad.data.storage.DataSourceFactory;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDataManager implements DataManager {
    private Connection connection;

    public JDBCDataManager() {
        try {
            this.connection = DataSourceFactory.createDataSource().getConnection();
        } catch (SQLException e) {
            System.err.println("Connection error");
        }
    }

    @Override
    public double getBill(Building building, int flatNumber) throws RemoteException {

//        SELECT registrations.id
//        FROM registrations JOIN (
//                SELECT flats.id
//                FROM flats JOIN (
//                        SELECT buildings.id
//                        FROM buildings JOIN (
//                                SELECT streets.id
//                                FROM streets
//                                WHERE name = 'Революционная'
//                        ) as street ON buildings.street_id = street.id
//                        WHERE number = 54
//                ) as building ON flats.building_id = building.id WHERE number = 256
//        ) as flat ON registrations.flat_id = flat.id;

        try {
            connection.prepareStatement("SELECT * FROM buildings JOIN (SELECT streets.id FROM streets WHERE name = ?) as streetId ON buildings.street_id = streetId");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Flat getFlat(Building building, int flatNumber) throws RemoteException {
        return null;
    }

    @Override
    public void setTariff(String tariffName, double newValue) throws RemoteException {

    }

    @Override
    public void addRegistration(String street, int buildingNumber, int flatNumber, Registration registration) throws RemoteException {

    }
}
