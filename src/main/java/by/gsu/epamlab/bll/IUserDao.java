package by.gsu.epamlab.bll;


import by.gsu.epamlab.constants.Roles;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.model.User;

public interface IUserDao {
    User getUser(String login, String passw) throws DAOException;
    boolean createUser(String login, String passw, Roles role) throws DAOException;
    boolean isUser(String login) throws DAOException;
}
