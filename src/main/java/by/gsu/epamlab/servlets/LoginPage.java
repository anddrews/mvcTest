package by.gsu.epamlab.servlets;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.fabrics.FabricDAO;
import by.gsu.epamlab.interfaces.IUserDao;
import by.gsu.epamlab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginPage")
public class LoginPage extends AbstractServlet{


    @Override
    public void init() throws ServletException {
        forwardPath=Constants.LOGIN_JSP;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
