package by.gsu.epamlab.servlets;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.fabrics.FabricDAOMethods;
import by.gsu.epamlab.interfaces.IDaoMethods;
import by.gsu.epamlab.model.Place;
import by.gsu.epamlab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/booking")
public class Booking extends AbstractServlet{


    @Override
    public void init() throws ServletException {
        forwardPath=Constants.ABOUT_JSP;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDaoMethods dao= FabricDAOMethods.getDaoMethods();

        String[] placeStr=(req.getParameter(Constants.PLACE)).split(Constants.SEPARATOR_FOR_PLACE);
        int row=Integer.valueOf(placeStr[Constants.ZERO]);
        int place=Integer.valueOf(placeStr[Constants.ONE]);
        int price=Integer.valueOf(req.getParameter(Constants.PRICE));
        int id=Integer.valueOf(req.getParameter(Constants.ID_PLAY));
        long date = Long.valueOf(req.getParameter(Constants.DATE));


        User user=(User)req.getSession().getAttribute(Constants.USER);
        try {
            dao.bookPlace(row,place,price,id,date,user.getUserName());
            resp.sendRedirect(req.getHeader(Constants.GO_BACK));
        } catch (DAOException e) {
            resp.sendRedirect(Constants.ERROR_JSP
            );
        }

    }
}
