package by.gsu.epamlab.servlets;




import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.fabrics.FabricDAOMethods;
import by.gsu.epamlab.interfaces.IDaoMethods;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;


@WebServlet("/uploadrepertoire")
@MultipartConfig
public class UpLoadRepertoire extends AbstractServlet {


    @Override
    public void init() throws ServletException {
        forwardPath=Constants.LOGIN_JSP;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IDaoMethods dao= FabricDAOMethods.getDaoMethods();
        Part part=req.getPart(Constants.NAME_INPUT_UPLOAD_REPERTOIRE);
        InputStream input=part.getInputStream();

        try {
            dao.saveRepertoire(input);
            resp.sendRedirect(req.getContextPath()+"/");
        } catch (DAOException e) {
            req.getRequestDispatcher(Constants.ERROR_JSP).forward(req, resp);
        }

    }


}
