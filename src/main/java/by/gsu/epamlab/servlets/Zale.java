package by.gsu.epamlab.servlets;

import by.gsu.epamlab.model.ZaleModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/zale")
public class Zale extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("zale",new ZaleModel(8,15));
        //req.getRequestDispatcher("/WEB-INF/views/zale.jsp").forward(req,resp);
    }
}
