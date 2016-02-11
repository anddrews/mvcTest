package by.gsu.epamlab.servlets;

import by.gsu.epamlab.bll.DaoMethods;
import by.gsu.epamlab.bll.ZalePlane;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.fabrics.FabricDAOMethods;
import by.gsu.epamlab.fabrics.FabricRepertoire;
import by.gsu.epamlab.interfaces.IDaoMethods;
import by.gsu.epamlab.interfaces.IRepertoire;
import by.gsu.epamlab.model.Place;
import by.gsu.epamlab.model.Play;
import by.gsu.epamlab.model.User;

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
        IDaoMethods dao= FabricDAOMethods.getDaoMethods();


        try {
            if(req.getQueryString()!=null) {
                int id = Integer.valueOf(req.getParameter(Constants.ID));
                Play play =repertoire.getPlay(id);
                req.setAttribute(Constants.PLAY, play);
                long data=Long.valueOf(req.getParameter(Constants.DATE));
                req.setAttribute(Constants.DATE,data);
                User user=(User)req.getSession().getAttribute(Constants.USER);
                String name=user!=null? user.getUserName():"";
                String pathToShemZale=getServletContext().getRealPath(Constants.PATH_TO_SHEME_ZALE);
                ZalePlane zale=new ZalePlane(pathToShemZale);
                dao.fillZale(zale, play.getId(), data, name);
                req.setAttribute(Constants.ZALE,zale);

            }

            req.getRequestDispatcher(Constants.ABOUT_JSP).forward(req, resp);
        } catch (  ReadFileException | DAOException e) {
            resp.sendRedirect(Constants.ERROR_JSP);
        }
    }

}
