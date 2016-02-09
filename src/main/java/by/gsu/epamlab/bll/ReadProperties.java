package by.gsu.epamlab.bll;


import by.gsu.epamlab.constants.Constants;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReadProperties {

    private static ReadProperties readProperties;
    private static Map<String,String> result;

    private ReadProperties() {
        result=new HashMap<>();
        ResourceBundle resourcesBundle=ResourceBundle.getBundle(Constants.PROPERTIES_PATH);
        Enumeration<String> resourcesKey=resourcesBundle.getKeys();
        while (resourcesKey.hasMoreElements())
        {
            String key=resourcesKey.nextElement();
            if (key.compareTo(Constants.DB_URL)==0){result.put(Constants.DB_URL, resourcesBundle.getString(key).trim());}
            if (key.compareTo(Constants.PASSWORD)==0){result.put(Constants.PASSWORD, resourcesBundle.getString(key).trim());}
            if (key.compareTo(Constants.USER)==0){result.put(Constants.USER, resourcesBundle.getString(key).trim());}
            if (key.compareTo(Constants.DRIVER)==0){result.put(Constants.DRIVER, resourcesBundle.getString(key).trim());}
            if (key.compareTo(Constants.REPERTOIRE_FILE_NAME)==0){result.put(Constants.REPERTOIRE_FILE_NAME, resourcesBundle.getString(key).trim());}
        }

    }

    public static String get(String property)
    {
        if(readProperties==null)
        {
          readProperties=new  ReadProperties();
        }

        return result.get(property);
    }
}
