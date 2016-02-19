package by.gsu.epamlab.servlets;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.fabrics.FabricRepertoire;
import by.gsu.epamlab.interfaces.IRepertoire;
import by.gsu.epamlab.model.Play;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class Home extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            IRepertoire repert= FabricRepertoire.getRepertoire();
            List<Play> repertoire= repert.getRepertoire();
            req.setAttribute(Constants.REPERTOIRE, repertoire);
            req.getRequestDispatcher(Constants.HOME_JSP).forward(req, resp);
        } catch (ReadFileException | DAOException e) {
            resp.sendRedirect(Constants.ERROR_JSP);
        }

    }
}
