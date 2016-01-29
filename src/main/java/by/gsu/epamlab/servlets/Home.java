package by.gsu.epamlab.servlets;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.fabrics.FabricRepertoire;
import by.gsu.epamlab.interfaces.IRepertoire;
import by.gsu.epamlab.model.Performance;
import by.gsu.epamlab.model.ZaleModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@WebServlet("/")
public class Home extends HttpServlet {


    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            req.getSession().setAttribute("zaleFree",new ZaleModel());

            IRepertoire repert= FabricRepertoire.getRepertoire();
            List<Performance> repertoire= repert.getRepertoire();
            req.getSession().setAttribute(Constants.REPERTOIRE,repertoire);
            req.getRequestDispatcher(Constants.HOME_JSP).forward(req, resp);
        } catch (ReadFileException e) {
            e.printStackTrace();
            //TODO
        }

    }
}
