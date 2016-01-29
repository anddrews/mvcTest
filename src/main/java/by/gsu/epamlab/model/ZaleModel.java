package by.gsu.epamlab.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ZaleModel {

    private List<Place> zale;

    public ZaleModel() {

        zale=new ArrayList<>();
    }


    public List<Place> getIsFree()
    {
        return zale;
    }

    public void bookingPlace(int row, int place)
    {
        zale.add(new Place(row,place,false));
    }
}
