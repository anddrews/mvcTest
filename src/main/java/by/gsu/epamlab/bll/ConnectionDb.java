package by.gsu.epamlab.bll;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class ConnectionDb
{
    private static ConnectionDb connectionDb;
    private static String dbUrl;
    private static String user;
    private static String password;
    private static String driver;
    private static String DB_URL="dbUrl";
    private static String PASSWORD="password";
    private static String USER="user";
    private static String DRIVER="driver";


    private ConnectionDb() throws DAOException {
        ResourceBundle resourcesBundle=ResourceBundle.getBundle(Constants.PROPERTIES_PATH);
        Enumeration<String> resourcesKey=resourcesBundle.getKeys();
        while (resourcesKey.hasMoreElements())
        {
            String key=resourcesKey.nextElement();
            if (key.compareTo(DB_URL)==0){dbUrl=resourcesBundle.getString(key).trim();}
            if (key.compareTo(PASSWORD)==0){password=resourcesBundle.getString(key).trim();}
            if (key.compareTo(USER)==0){user=resourcesBundle.getString(key).trim();}
            if (key.compareTo(DRIVER)==0){driver=resourcesBundle.getString(key).trim();}
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage());
        }
    }


    public static Connection getConnection() throws DAOException {
        if(connectionDb==null)
        {
            connectionDb=new ConnectionDb();
        }
        try {
           return DriverManager.getConnection(dbUrl,user,password);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

    }

}
