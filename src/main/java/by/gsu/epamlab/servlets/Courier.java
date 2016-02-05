package by.gsu.epamlab.servlets;


import by.gsu.epamlab.bll.DaoMethods;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ReportCharacter;
import by.gsu.epamlab.model.ReportRow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet("/courier")
public class Courier extends HttpServlet {
   Map<ReportCharacter, Object> criteria= new TreeMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ReportRow> report= DaoMethods.getReport(criteria);
        req.setAttribute("report",report);
        req.getRequestDispatcher(Constants.COURIER_JSP).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String play=req.getParameter("play");
        String user=req.getParameter("user");
        String date=req.getParameter("date");
        SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
        Date dat=null;
        try {
            dat=format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        criteria=new TreeMap<>();
        if(!play.equals(Constants.EMPTY_STRING))
        {
            criteria.put(ReportCharacter.PLAY,play);
        }
        if(!user.equals(Constants.EMPTY_STRING))
        {
            criteria.put(ReportCharacter.USER,user);
        }
        if(dat!=null)
        {
            criteria.put(ReportCharacter.DATE, new Timestamp(dat.getTime()));
        }

        doGet(req,resp);


    }
}
