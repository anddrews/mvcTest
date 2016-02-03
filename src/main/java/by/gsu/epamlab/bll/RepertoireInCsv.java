package by.gsu.epamlab.bll;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.interfaces.IRepertoire;
import by.gsu.epamlab.model.Play;

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


    public  List<Play> getRepertoire() throws ReadFileException {

        try(BufferedReader bufferedReader=
                    new BufferedReader(new FileReader( ReadProperties.get(Constants.REPERTOIRE_FILE_NAME))))
        {
            List<Play> repertoire=new ArrayList<>();
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
                Play play =new Play(id,name,description.toString());
                while ((stringRead=bufferedReader.readLine())!=null && !stringRead.equals("") )
                {
                    String[] rows=stringRead.split(Constants.SEPARATOR);
                    String date=rows[Constants.ZERO].trim();
                    String hour=rows[Constants.ONE].trim();
                    SimpleDateFormat dateFormat=new SimpleDateFormat();
                    dateFormat.setTimeZone(TimeZone.getDefault());
                    Date dateToSave=dateFormat.parse(date + " "+ hour);
                    play.setNewDate(dateToSave);
                }
                repertoire.add(play);
                id++;
            }

            return repertoire;

        } catch (IOException| ParseException e) {
            throw  new ReadFileException(e.getMessage());
        }

    }

    @Override
    public Play getPlay(int id) throws ReadFileException {

        List<Play> plays =getRepertoire();
        for (Play tmp: plays)
        {
            if(tmp.getId()==id){
                return tmp;
            }
        }
        return new Play(0,"Not list repertoire","kgdskgdskljgsdflkgjsdkflj");
    }
}
