package ua.training.finalproject.foodtrackingsystem.model.dao.connection;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Stores pool of connections to database.
 * This class is used in DaoFactory.
 *
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 * @see ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory
 */
public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("db",
            new Locale("en", "GB"));

    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    org.apache.commons.dbcp.BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
                    ds.setUrl(resourceBundle.getString("db.url"));
                    ds.setUsername(resourceBundle.getString("db.user"));
                    ds.setPassword(resourceBundle.getString("db.password"));
                    ds.setMinIdle(50);
                    ds.setMaxIdle(100);
                    ds.setMaxOpenPreparedStatements(1000);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

    //<editor-fold desc="C3P0 Connection Pool">
   /* public static ComboPooledDataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(resourceBundle.getString("db.url"));
        cpds.setUser(resourceBundle.getString("db.user"));
        cpds.setPassword(resourceBundle.getString("db.password"));

        // Optional Settings
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(100);

        return cpds;
    }*/
    //</editor-fold>
}