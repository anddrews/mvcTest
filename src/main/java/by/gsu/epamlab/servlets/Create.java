package by.gsu.epamlab.servlets;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.Roles;
import by.gsu.epamlab.fabrics.FabricDAO;
import by.gsu.epamlab.interfaces.IUserDao;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/create")
public class Create extends AbstractServlet{

    @Override
    public void init() throws ServletException {
        forwardPath=Constants.CREATE_JSP;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String login=req.getParameter(Constants.USER);
        String passw=req.getParameter(Constants.PASSWORD);
        String passwSec=req.getParameter(Constants.PASSWORD_SEC);
        IUserDao dao=null;
        try {

            dao=FabricDAO.getDAO();
            User user;

            if (!login.equals(Constants.EMPTY_STRING)&&!passw.equals(Constants.EMPTY_STRING)
                    && passw.equals(passwSec)
                    && ((user=dao.createUser(login, passw, Roles.USER))!=null))
            {
                req.getSession().setAttribute(Constants.USER, user);
                resp.sendRedirect(req.getParameter(Constants.PAGE));

            } else {
                req.setAttribute(Constants.ERROR,Constants.SOME_PROBLEM);
                req.setAttribute(Constants.USER, Constants.EMPTY_STRING);
                req.setAttribute(Constants.PASSWORD, Constants.EMPTY_STRING);
                req.setAttribute(Constants.PASSWORD_SEC, Constants.EMPTY_STRING);
                doGet(req, resp);
            }
        }
        catch (DAOException e)
        {
            resp.sendRedirect(Constants.ERROR_JSP);
        }
        finally {
            if(dao!=null)
            {
                dao.closeConnection();
            }
        }

    }
}
