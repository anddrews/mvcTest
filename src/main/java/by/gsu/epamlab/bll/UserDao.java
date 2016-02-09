package by.gsu.epamlab.bll;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.Roles;
import by.gsu.epamlab.constants.SQLQueries;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.interfaces.IUserDao;
import by.gsu.epamlab.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements IUserDao {

    private final static Object LOCK =UserDao.class;
    private Connection connection;


    public UserDao() throws DAOException {
        this.connection = ConnectionDb.getConnection();
    }

    @Override
    public User getUser(String login, String passw) throws DAOException {
        User result=null;
        ResultSet resultSet;


        try(PreparedStatement preparedStatement=connection.prepareStatement(SQLQueries.CHECK_USER)) {

            preparedStatement.setString(Constants.ONE,login);
            preparedStatement.setString(Constants.TWO,passw);

            resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String name=resultSet.getString(Constants.USER);
                int role=resultSet.getInt(Constants.ROLE);
                result= new User(name,Roles.values()[role]);
            }
            return result;

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }



    }

    @Override
    public User createUser(String login, String passw, Roles role) throws DAOException {
        User result=null;
        int count=0;

            try (PreparedStatement createUser = connection.prepareStatement(SQLQueries.CREATE_USER);
                    PreparedStatement getUser=connection.prepareStatement(SQLQueries.GET_USER)) {

                getUser.setString(Constants.ONE,login);

                createUser.setString(Constants.ONE, login);
                createUser.setString(Constants.TWO, passw);
                createUser.setInt(Constants.THREE, role.getNumber());

                synchronized (LOCK)
                {
                    ResultSet isUser=getUser.executeQuery();

                    if (!isUser.next()) {
                        count = createUser.executeUpdate();
                        if (count > 0) {
                            result = getUser(login, passw);
                        }
                    }
                }
                return result;

            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }


    }


    @Override
    public void closeConnection() {
        if(this.connection!=null)
        {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
