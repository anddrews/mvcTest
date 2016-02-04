package by.gsu.epamlab.servlets;




import by.gsu.epamlab.bll.DaoMethods;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.Play;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@WebServlet("/uploadrepertoire")
public class UpLoadRepertoire extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DaoMethods dao=new DaoMethods();


        InputStreamReader reader = new InputStreamReader(req.getInputStream());
        BufferedReader bufferedReader=new BufferedReader(reader);
        String stringRead;
        while (!(stringRead=bufferedReader.readLine()).equals(Constants.EMPTY_STRING));

        while (!(stringRead=bufferedReader.readLine()).startsWith("---")  )
        {
            String name=stringRead;
            StringBuilder description=new StringBuilder();
            while (!(stringRead=bufferedReader.readLine()).equals(Constants.EMPTY_STRING))
            {
                description.append(stringRead);
            }
            Play play =new Play(0,name,description.toString());
            while ((stringRead=bufferedReader.readLine())!=null && !stringRead.equals(Constants.EMPTY_STRING) )
            {
                String[] rows=stringRead.split(Constants.SEPARATOR);
                String date=rows[Constants.ZERO].trim();
                String hour=rows[Constants.ONE].trim();

                try {
                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd.mm.yyyy HH:mm");
                    dateFormat.setTimeZone(TimeZone.getDefault());
                    Date dateToSave = dateFormat.parse(date + " "+ hour);
                    play.setNewDate(dateToSave);
                } catch (ParseException e) {
                    e.printStackTrace();
                    //TODO if new date doesn't parse not save in DB
                }

            }
            dao.addNewPlayToDB(play);



        }

    }


}
