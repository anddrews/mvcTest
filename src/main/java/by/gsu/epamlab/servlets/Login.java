package by.gsu.epamlab.servlets;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.bll.FabricDAO;
import by.gsu.epamlab.bll.IUserDao;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Constants.LOGIN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login=req.getParameter(Constants.USER).trim();
        String password=req.getParameter(Constants.PASSWORD).trim();
        try{
            IUserDao dao=FabricDAO.getDAO();
            if(!login.equals("") && !password.equals("") && dao.isUser(login))
            {
                User user= dao.getUser(login, password);
                req.getSession().setAttribute(Constants.USER,user);
                resp.sendRedirect(Constants.HOME_PAGE);
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


    }
}
