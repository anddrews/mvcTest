package by.gsu.epamlab.bll;


import by.gsu.epamlab.model.Place;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Map;

public class ZalePlane {

    public static Map<Integer,Place[]> getPlane()
    {
        Map<Integer,Place[]> results=null;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SaxParser handler = new SaxParser();
            reader.setContentHandler(handler);

            reader.parse("D:/kursy/java/mvcTest/src/main/webapp/zale.xml");

             results=handler.getResults();
        }
        catch (SAXException | IOException e) {

            e.printStackTrace();
        }
        return results;
    }


}
