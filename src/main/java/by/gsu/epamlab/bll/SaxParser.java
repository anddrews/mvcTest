package by.gsu.epamlab.bll;


import by.gsu.epamlab.model.Place;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class SaxParser extends DefaultHandler
{
    private enum Tags
    {ZALE,ROW,PLACES,CATEGORY}

    private boolean isCategory;
    private boolean isPlace;

    private Map<Integer,Place[]> results;
    private String thisElement;
    private int row;
    private int places;

    public SaxParser()
    {
        super();
        this.isCategory =false;
        this.isPlace =false;
        results=new TreeMap<>();
    }

    public   Map<Integer,Place[]> getResults()
    {
        return results;
    }

    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {

        thisElement=localName.toUpperCase();
        if (Tags.valueOf(thisElement).equals(Tags.ROW))
        {
            isPlace=true;
            isCategory=true;
            row=Integer.valueOf(attributes.getValue(0));
        }


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        Tags tags= Tags.valueOf(thisElement);
        switch (tags)
        {
            case PLACES:
            {
                if(isPlace) {
                    String placeStr = (new String(ch, start, length)).trim();
                    places = Integer.valueOf(placeStr);
                    results.put(row, new Place[places]);
                    isPlace=false;
                }
                break;
            }
            case CATEGORY:
            {
                if(isCategory)
                {
                    int price = Integer.valueOf(new String(ch, start, length));
                    for (int i = 0; i < results.get(row).length; i++) {
                        results.get(row)[i] = new Place(price);
                    }
                    isCategory=false;
                }
                break;
            }
        }
    }



    @Override
    public void endDocument() throws SAXException
    {
        super.endDocument();
    }


}
