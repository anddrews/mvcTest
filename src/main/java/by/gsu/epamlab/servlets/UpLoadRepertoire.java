package by.gsu.epamlab.servlets;




import by.gsu.epamlab.bll.DaoMethods;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.DAOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet("/uploadrepertoire")
public class UpLoadRepertoire extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(Constants.PAGE, req.getHeader(Constants.GO_BACK));
        req.getRequestDispatcher(Constants.LOGIN_JSP).forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            DaoMethods.saveRepertoire(req.getInputStream());
            resp.sendRedirect(req.getContextPath()+"/");
        } catch (DAOException e) {
            req.getRequestDispatcher(Constants.ERROR_JSP).forward(req, resp);
        }

    }


}
