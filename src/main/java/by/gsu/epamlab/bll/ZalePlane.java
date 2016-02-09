package by.gsu.epamlab.bll;


import by.gsu.epamlab.model.Place;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Map;

public class ZalePlane {

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

    public  Map<Integer,Place[]> getPlane()
    {
        return results;
    }


}
