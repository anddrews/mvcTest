package by.gsu.epamlab.interfaces;


import by.gsu.epamlab.constants.Roles;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.model.User;

public interface IUserDao {
    User getUser(String login, String passw) throws DAOException;
    User createUser(String login, String passw, Roles role) throws DAOException;
    void closeConnection();
}
