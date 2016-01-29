package by.gsu.epamlab.servlets;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.Roles;
import by.gsu.epamlab.fabrics.FabricDAO;
import by.gsu.epamlab.interfaces.IUserDao;
import by.gsu.epamlab.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/create")
public class Create extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Constants.CREATE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login=req.getParameter(Constants.USER);
        String passw=req.getParameter(Constants.PASSWORD);
        String passwSec=req.getParameter(Constants.PASSWORD_SEC);
        IUserDao dao=null;
        try {

            dao=FabricDAO.getDAO();

            if (!dao.isUser(login) &&
                    !passw.equals("") &&
                    passw.equals(passwSec) &&
                    dao.createUser(login, passw, Roles.USER)) {
                req.getSession().setAttribute(Constants.USER, dao.getUser(login, passw));
                resp.sendRedirect(Constants.HOME_PAGE);
            } else if (dao.isUser(login)) {
                req.setAttribute(Constants.ERROR, Constants.USER_ALREADY_IS);
                req.setAttribute(Constants.USER, Constants.NOTHING);
                req.setAttribute(Constants.PASSWORD, Constants.NOTHING);
                req.setAttribute(Constants.PASSWORD_SEC, Constants.NOTHING);
                doGet(req, resp);

            } else if (passw.equals("") || passw.equals(passwSec)) {
                req.setAttribute(Constants.ERROR, Constants.PASSWORD_NOT_CORRECT);
                req.setAttribute(Constants.PASSWORD, Constants.NOTHING);
                req.setAttribute(Constants.PASSWORD_SEC, Constants.NOTHING);
                doGet(req, resp);
            } else {
                req.setAttribute(Constants.ERROR,Constants.SOME_PROBLEM);
                req.setAttribute(Constants.USER, Constants.NOTHING);
                req.setAttribute(Constants.PASSWORD, Constants.NOTHING);
                req.setAttribute(Constants.PASSWORD_SEC, Constants.NOTHING);
                doGet(req, resp);
            }
        }
        catch (DAOException e)
        {
            req.getRequestDispatcher(Constants.ERROR_JSP).forward(req, resp);
        }
        finally {
            if(dao!=null)
            {
                dao.closeConnection();
            }
        }

    }
}
