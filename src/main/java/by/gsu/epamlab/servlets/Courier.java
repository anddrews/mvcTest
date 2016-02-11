package by.gsu.epamlab.servlets;


import by.gsu.epamlab.bll.DaoMethods;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ReportCharacter;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.fabrics.FabricDAOMethods;
import by.gsu.epamlab.interfaces.IDaoMethods;
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
   Map<ReportCharacter, String> criteria= new TreeMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDaoMethods dao= FabricDAOMethods.getDaoMethods();

        String play=req.getParameter(Constants.PLAY);
        String user=req.getParameter(Constants.USER);
        String date=req.getParameter(Constants.DATE);


        criteria=new TreeMap<>();
        if(play!=null && !play.equals(Constants.EMPTY_STRING))
        {
            criteria.put(ReportCharacter.PLAY,play);
            req.setAttribute(Constants.PLAY,play);
        }
        if(user!=null && !user.equals(Constants.EMPTY_STRING))
        {
            criteria.put(ReportCharacter.USER,user);
            req.setAttribute(Constants.USER_NAME,user);

        }
        if(date!=null && !date.equals(Constants.EMPTY_STRING))
        {
            criteria.put(ReportCharacter.DATE, date);
            req.setAttribute(Constants.DATE,date);

        }

        try {
            List<ReportRow> report = dao.getReport(criteria);
            req.setAttribute(Constants.REPORT,report);
            req.getRequestDispatcher(Constants.COURIER_JSP).forward(req, resp);
        } catch (DAOException e) {
            resp.sendRedirect(Constants.ERROR_JSP);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDaoMethods dao= FabricDAOMethods.getDaoMethods();

        String[] id=req.getParameterValues(Constants.ID);
        try {
            if (id != null) {
                String action = req.getParameter(Constants.ACTION);

                for (String tmp : id) {

                    if (action.equals(Constants.BUY))
                        dao.courierBuy(Integer.valueOf(tmp));
                    else
                        dao.courierDel(Integer.valueOf(tmp));
                }


            }
        }catch (DAOException e)
        {
            resp.sendRedirect(Constants.ERROR_JSP);
        }



        doGet(req,resp);


    }
}
