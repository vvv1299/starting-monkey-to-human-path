package PO73.Perepechin.wdad.data.storage;

import PO73.Perepechin.wdad.data.managers.PreferencesManager;
import PO73.Perepechin.wdad.utils.PreferencesManagerConstants;
import org.postgresql.jdbc2.optional.SimpleDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    public static DataSource createDataSource() {
        PreferencesManager pref = PreferencesManager.getInstance();
        return createDataSource(pref.getProperty(PreferencesManagerConstants.DATASOURCE_CLASSNAME_KEY),
                pref.getProperty(PreferencesManagerConstants.DATASOURCE_DRIVER_TYPE_KEY),
                pref.getProperty(PreferencesManagerConstants.DATASOURCE_HOST_NAME_KEY),
                Integer.parseInt(pref.getProperty(PreferencesManagerConstants.DATASOURCE_PORT_KEY)),
                pref.getProperty(PreferencesManagerConstants.DATASOURCE_DB_NAME_KEY),
                pref.getProperty(PreferencesManagerConstants.DATASOURCE_USER_KEY),
                pref.getProperty(PreferencesManagerConstants.DATASOURCE_PASS_KEY));
    }

    public static DataSource createDataSource(String className,
                                              String driverType,
                                              String host,
                                              int port,
                                              String dbName,
                                              String user,
                                              String password) {
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setServerName(host);
        dataSource.setPortNumber(port);
        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
