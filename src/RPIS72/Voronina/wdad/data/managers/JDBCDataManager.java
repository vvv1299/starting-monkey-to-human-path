package RPIS72.Voronina.wdad.data.managers;

import RPIS72.Voronina.wdad.data.model.Building;
import RPIS72.Voronina.wdad.data.model.Flat;
import RPIS72.Voronina.wdad.data.model.Registration;
import RPIS72.Voronina.wdad.data.storage.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public double getBill(Building building, int flatNumber) {
        try {
            String sql = "SELECT sum(registrations_tariffs.amount * tariffs.cost) as bill FROM registrations_tariffs JOIN (\n" +
                            "    registrations JOIN (\n" +
                            "        flats JOIN (\n" +
                            "            buildings JOIN streets ON buildings.street_id = streets.id\n" +
                            "        ) ON flats.building_id = buildings.id\n" +
                            "    ) ON registrations.flat_id = flats.id\n" +
                            ") ON registrations_tariffs.registration_id = registrations.id\n" +
                            "JOIN tariffs ON registrations_tariffs.tariff_name = tariffs.name\n" +
                            "WHERE streets.name = ? AND buildings.number = ? AND flats.number = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, building.getStreet());
            statement.setInt(2, building.getNumber());
            statement.setInt(3, flatNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return resultSet.getDouble("bill");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Flat getFlat(Building building, int flatNumber) {
        try {
            String sql =
                    "SELECT registrations.id, registrations.date FROM registrations JOIN (\n" +
                    "    flats JOIN (\n" +
                    "        buildings JOIN streets ON buildings.street_id = streets.id\n" +
                    "    ) ON flats.building_id = buildings.id\n" +
                    ") ON registrations.flat_id = flats.id\n" +
                    "WHERE streets.name = ? AND buildings.number = ? AND flats.number = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, building.getStreet());
            statement.setInt(2, building.getNumber());
            statement.setInt(3, flatNumber);

            List<Registration> registrations = new ArrayList<>();
            ResultSet registrationsSet = statement.executeQuery();
            Registration registration;
            while (registrationsSet.next()) {
                registration = new Registration();
                int registrationId = registrationsSet.getInt("id");
                registration.setDate(registrationsSet.getDate("date"));
                sql =
                        "SELECT registrations_tariffs.tariff_name, registrations_tariffs.amount\n" +
                        "FROM registrations_tariffs JOIN registrations\n" +
                        "    ON registrations_tariffs.registration_id = registrations.id\n" +
                        "WHERE registrations.id = ?;";

                statement = connection.prepareStatement(sql);
                statement.setInt(1, registrationId);

                ResultSet amountSet = statement.executeQuery();
                while (amountSet.next()) {
                    switch (amountSet.getString("tariff_name")) {
                        case "Холодная вода":
                            registration.setColdWater(amountSet.getDouble("amount"));
                            break;

                        case "Горячая вода":
                            registration.setHotWater(amountSet.getDouble("amount"));
                            break;

                        case "Газ":
                            registration.setGas(amountSet.getDouble("amount"));
                            break;

                        case "Электричество":
                            registration.setElectricity(amountSet.getDouble("amount"));
                            break;
                    }
                }
                registrations.add(registration);
            }
            return new Flat(flatNumber, 0, 0, registrations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setTariff(String tariffName, double newValue) {
        try {
           String sql = "UPDATE tariffs SET cost = ? WHERE name = ?;";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setDouble(1, newValue);
           statement.setString(2, tariffName);
           statement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void addRegistration(String street, int buildingNumber, int flatNumber, Registration registration) {
        try {
            String sql =
                    "SELECT flats.id FROM flats JOIN (\n" +
                    "    buildings JOIN streets ON buildings.street_id = streets.id\n" +
                    ") ON flats.building_id = buildings.id\n" +
                    "WHERE flats.number = ? AND buildings.number = ? AND streets.name = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, flatNumber);
            statement.setInt(2, buildingNumber);
            statement.setString(3, street);
            ResultSet resultSet = statement.executeQuery();

            int flatId = 0;
            if (resultSet.next()) flatId = resultSet.getInt("id");

            sql = "INSERT INTO registrations VALUES (default, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new Date(registration.getDate().getTime()));
            statement.setInt(2, flatId);
            statement.execute();

            sql = "SELECT registrations.id FROM registrations WHERE flat_id = ? AND date = ?";
            statement = connection.prepareStatement(sql);
            statement.setDate(2, new Date(registration.getDate().getTime()));
            statement.setInt(1, flatId);
            resultSet = statement.executeQuery();

            int registrationId = 0;
            if (resultSet.next()) registrationId = resultSet.getInt("id");

            for (int i = 0; i < 4; i++) {
                sql = "INSERT INTO registrations_tariffs VALUES (default, ?, ?, ?);";
                double amount = 0;
                String tariffName = "";
                switch (i) {
                    case 0:
                        amount = registration.getColdWater();
                        tariffName = "Холодная вода";
                        break;

                    case 1:
                        amount = registration.getHotWater();
                        tariffName = "Горячая вода";
                        break;

                    case 2:
                        amount = registration.getGas();
                        tariffName = "Газ";
                        break;

                    case 3:
                        amount = registration.getElectricity();
                        tariffName = "Электричество";
                        break;
                }
                statement = connection.prepareStatement(sql);
                statement.setDouble(1, amount);
                statement.setInt(2, registrationId);
                statement.setString(3, tariffName);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
