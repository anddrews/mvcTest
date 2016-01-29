package by.gsu.epamlab.servlets;

import by.gsu.epamlab.bll.RepertoireInCsv;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.interfaces.IRepertoire;
import by.gsu.epamlab.model.Performance;
import by.gsu.epamlab.model.ZaleModel;

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


        IRepertoire repertoire=new RepertoireInCsv();


        try {
            if(req.getQueryString()!=null) {
                int id = Integer.valueOf(req.getParameter("id"));
                Performance performance=repertoire.getPerfomance(id);
                req.getSession().setAttribute("performance",performance);
            }
            req.getRequestDispatcher(Constants.ABOUT_JSP).forward(req, resp);
        } catch (  ReadFileException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
