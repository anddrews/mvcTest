package by.gsu.epamlab.servlets;

import by.gsu.epamlab.bll.DaoMethods;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.Place;
import by.gsu.epamlab.model.Play;
import by.gsu.epamlab.model.User;
import by.gsu.epamlab.model.Zale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@WebServlet("/booking")
public class Booking extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(Constants.ABOUT_JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] placeStr=(req.getParameter(Constants.PLACE)).split(Constants.SEPARATOR_FOR_PLACE);
        int row=Integer.valueOf(placeStr[0]);
        int place=Integer.valueOf(placeStr[1]);
        int price=Integer.valueOf(req.getParameter(Constants.PRICE));
        int id=Integer.valueOf(req.getParameter(Constants.ID_PLAY));
        long date = Long.valueOf(req.getParameter(Constants.DATE));


        Map<Integer,Place[]> zale=(Map<Integer,Place[]>)req.getSession().getAttribute(Constants.ZALE);
        User user=(User)req.getSession().getAttribute(Constants.USER);
        if(DaoMethods.bookPlace(row,place,price,id,date,user.getUserName()))req.setAttribute(Constants.ZALE,zale);
        resp.sendRedirect(req.getHeader(Constants.GO_BACK));
    }
}
