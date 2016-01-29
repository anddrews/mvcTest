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

    private ConnectionDb() throws DAOException {

        try {
            Class.forName(ReadProperties.get(Constants.DRIVER));
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
           return DriverManager.getConnection(
                   ReadProperties.get(Constants.DB_URL),
                   ReadProperties.get(Constants.USER),
                   ReadProperties.get(Constants.PASSWORD));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

    }

}
