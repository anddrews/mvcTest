package by.gsu.epamlab.model;


import by.gsu.epamlab.bll.SaxParser;
import by.gsu.epamlab.model.Place;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class ZalePlane  {

    private Map<Integer,Place[]> results;

    public ZalePlane(String filePath) {

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SaxParser handler = new SaxParser();
            reader.setContentHandler(handler);

            reader.parse(filePath);

            results=handler.getResults();
        }
        catch (SAXException | IOException e) {

            e.printStackTrace();
        }
    }

    public ZalePlane(ZalePlane plane)
    {
        this.results=new TreeMap<>();
        for (Map.Entry<Integer,Place[]> tmp:plane.getPlane().entrySet())
        {
            Place[] newPlace=new Place[tmp.getValue().length];
            for (int i=0;i<tmp.getValue().length;i++)
            {
                newPlace[i]=new Place(tmp.getValue()[i]);
            }
            this.results.put(tmp.getKey(),newPlace);
        }
    }

    public  Map<Integer,Place[]> getPlane()
    {
        return results;
    }

}
