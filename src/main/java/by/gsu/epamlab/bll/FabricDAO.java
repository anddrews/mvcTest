package by.gsu.epamlab.bll;


import by.gsu.epamlab.exception.DAOException;

public  class FabricDAO {

    public static IUserDao getDAO() throws DAOException {

        return new UserDao();

    }



}
