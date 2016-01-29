package by.gsu.epamlab.fabrics;


import by.gsu.epamlab.bll.UserDao;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.interfaces.IUserDao;

public  class FabricDAO {

    public static IUserDao getDAO() throws DAOException {

        return new UserDao();

    }



}
