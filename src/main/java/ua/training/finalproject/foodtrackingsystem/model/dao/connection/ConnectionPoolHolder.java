package ua.training.finalproject.foodtrackingsystem.model.dao.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Locale;
import java.util.ResourceBundle;

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


}