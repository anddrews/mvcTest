package by.gsu.epamlab.servlets;


import by.gsu.epamlab.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractServlet  extends HttpServlet{

    protected String forwardPath;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(Constants.PAGE, req.getHeader(Constants.GO_BACK));
        req.getRequestDispatcher(forwardPath).forward(req, resp);

    }
}
