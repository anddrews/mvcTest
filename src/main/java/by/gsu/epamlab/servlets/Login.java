package by.gsu.epamlab.servlets;

import by.gsu.epamlab.constants.Constants;
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

@WebServlet("/login")
public class Login extends AbstractServlet{


    @Override
    public void init() throws ServletException {
        forwardPath=Constants.LOGIN_JSP;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login=req.getParameter(Constants.USER).trim();
        String password=req.getParameter(Constants.PASSWORD).trim();
        IUserDao dao=null;
        try{
            dao=FabricDAO.getDAO();
            User user;
            if(!login.equals(Constants.EMPTY_STRING) && !password.equals(Constants.EMPTY_STRING)
                    && ((user=dao.getUser(login,password))!=null))
            {
                req.getSession().setAttribute(Constants.USER, user);
                resp.sendRedirect(req.getParameter(Constants.PAGE));
            }
            else
            {

                req.setAttribute(Constants.ERROR,Constants.BAD_PARAMETER);
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
