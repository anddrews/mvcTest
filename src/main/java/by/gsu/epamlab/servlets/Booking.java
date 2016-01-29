package by.gsu.epamlab.servlets;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.ZaleModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/booking")
public class Booking extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(Constants.ABOUT_JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int place=Integer.valueOf(req.getParameter("place"));
        int row=Integer.valueOf(req.getParameter("row"));
        String booking=req.getParameter("booking");
        ZaleModel zale=(ZaleModel)req.getSession().getAttribute("zaleFree");
        zale.bookingPlace(row, place);
        req.getSession().setAttribute("zaleFree", zale);
        resp.sendRedirect(req.getHeader("referer"));
    }
}
