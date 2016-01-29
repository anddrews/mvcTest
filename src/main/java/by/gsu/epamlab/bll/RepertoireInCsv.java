package by.gsu.epamlab.bll;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.interfaces.IRepertoire;
import by.gsu.epamlab.model.Performance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RepertoireInCsv implements IRepertoire {


    public  List<Performance> getRepertoire() throws ReadFileException {

        try(BufferedReader bufferedReader=
                    new BufferedReader(new FileReader( ReadProperties.get(Constants.REPERTOIRE_FILE_NAME))))
        {
            List<Performance> repertoire=new ArrayList<>();
            String stringRead;
            int id=0;
            while ((stringRead=bufferedReader.readLine())!=null)
            {
                String name=stringRead;
                StringBuilder description=new StringBuilder();
                while (!(stringRead=bufferedReader.readLine()).equals(""))
                {
                    description.append(stringRead);
                }
                Performance performance=new Performance(id,name,description.toString());
                while ((stringRead=bufferedReader.readLine())!=null && !stringRead.equals("") )
                {
                    String[] rows=stringRead.split(Constants.SEPARATOR);
                    String date=rows[Constants.ZERO].trim();
                    String hour=rows[Constants.ONE].trim();
                    SimpleDateFormat dateFormat=new SimpleDateFormat();
                    dateFormat.setTimeZone(TimeZone.getDefault());
                    Date dateToSave=dateFormat.parse(date + " "+ hour);
                    performance.setNewDate(dateToSave);
                }
                repertoire.add(performance);
                id++;
            }

            return repertoire;

        } catch (IOException| ParseException e) {
            throw  new ReadFileException(e.getMessage());
        }

    }

    @Override
    public Performance getPerfomance(int id) throws ReadFileException {

        List<Performance> performances=getRepertoire();
        for (Performance tmp:performances)
        {
            if(tmp.getId()==id){
                return tmp;
            }
        }
        return new Performance(0,"Not list repertoire","kgdskgdskljgsdflkgjsdkflj");
    }
}
