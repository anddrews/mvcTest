package by.gsu.epamlab.bll;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.Roles;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements IUserDao{

    private final static Object lock=new Object();

    private static final String GET_USER="SELECT * FROM users WHERE user=?";
    private static final String CREATE_USER="INSERT INTO users(user,password,role) VALUES (?,?,?) ";
    private Connection connection;


    public UserDao() throws DAOException {
        this.connection = ConnectionDb.getConnection();
    }

    @Override
    public User getUser(String login, String passw) throws DAOException {
        User result=null;
        ResultSet resultSet;


        try(PreparedStatement preparedStatement=connection.prepareStatement(GET_USER)) {

            preparedStatement.setString(1,login);

            resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String name=resultSet.getString(Constants.USER);
                String password=resultSet.getString(Constants.PASSWORD);
                int role=resultSet.getInt(Constants.ROLE);

                if(passw.equals(password))
                {
                    result= new User(name,Roles.values()[role]);
                }
            }
            return result;

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }



    }

    @Override
    public boolean createUser(String login, String passw, Roles role) throws DAOException {
        boolean result=false;
        int count=0;
        synchronized (lock) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {

                if (!isUser(login)) {

                    preparedStatement.setString(Constants.ONE, login);
                    preparedStatement.setString(Constants.TWO, passw);
                    preparedStatement.setInt(Constants.THREE, role.getNumber());
                    count = preparedStatement.executeUpdate();
                    if (count > 0) {
                        result = true;
                    }
                }
                return result;

            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }

    }

    @Override
    public boolean isUser(String login) throws DAOException {
        ResultSet resultSet;
        boolean result=false;

        try(PreparedStatement preparedStatement=connection.prepareStatement(GET_USER)) {
            preparedStatement.setString(Constants.ONE,login);

            resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                result=true;
            }
            return result;

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

    }
}
