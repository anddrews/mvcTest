package by.gsu.epamlab.servlets;

import by.gsu.epamlab.bll.DaoMethods;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.bll.ZalePlane;
import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.fabrics.FabricRepertoire;
import by.gsu.epamlab.interfaces.IRepertoire;
import by.gsu.epamlab.model.Place;
import by.gsu.epamlab.model.Play;
import by.gsu.epamlab.model.User;
import by.gsu.epamlab.model.Zale;
import com.sun.nio.zipfs.ZipFileAttributes;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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
                long data=Long.valueOf(req.getParameter("data"));
                req.setAttribute("date",data);
                User user=(User)req.getSession().getAttribute("user");
                System.out.println(user);
                String name=user!=null? user.getUserName():"";
                Map<Integer,Place[]> zale=new Zale().getZale();
                req.setAttribute("zale", DaoMethods.fillZale(zale, play.getId(), data, name));

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
