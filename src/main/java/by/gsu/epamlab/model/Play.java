package by.gsu.epamlab.model;


import by.gsu.epamlab.constants.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Play {

    final int id;
    final String name;
    final String description;
    final List<Date> date;

    public Play() {
        this.id=0;
        this.name = "";
        this.description="";
        this.date = null;
    }

    public Play(int id, String name, String description) {
        this.id=id;
        this.name = name;
        this.description=description;
        this.date = new ArrayList<>();

    }

    public void setNewDate(Date date)
    {
        this.date.add(date);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Date> getDate()
    {
        return this.date;
    }



    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        String result=name + " ";
        SimpleDateFormat dateFormat=new SimpleDateFormat(Constants.PERFORMANS_DATE_FORMAT);
        for (Date tmp: date)
        {
            result+= dateFormat.format(tmp)+ " ";
        }
        return result;
    }
}
