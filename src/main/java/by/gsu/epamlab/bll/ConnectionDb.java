package by.gsu.epamlab.bll;

import by.gsu.epamlab.exception.DAOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionDb
{

    public static Connection getConnection()  throws DAOException {

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource datasource = (DataSource) envContext.lookup("jdbc/WebDB");
            Connection connection = datasource.getConnection();
            return connection;
        } catch (NamingException | SQLException e) {
            throw new DAOException(e.getMessage());
        }


    }


}
