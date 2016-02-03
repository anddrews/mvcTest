package by.gsu.epamlab.servlets;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.bll.ZalePlane;
import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.fabrics.FabricRepertoire;
import by.gsu.epamlab.interfaces.IRepertoire;
import by.gsu.epamlab.model.Play;
import by.gsu.epamlab.model.Zale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/about")
public class About extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        IRepertoire repertoire= FabricRepertoire.getRepertoire();


        try {
            if(req.getQueryString()!=null) {
                int id = Integer.valueOf(req.getParameter("id"));
                Play play =repertoire.getPlay(id);
                req.setAttribute("performance", play);
                req.setAttribute("date",req.getParameter("data"));
            }

            //req.setAttribute("zale", (new Zale()).getZale());
            req.getRequestDispatcher(Constants.ABOUT_JSP).forward(req, resp);
        } catch (  ReadFileException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
