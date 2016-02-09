package by.gsu.epamlab.fabrics;


import by.gsu.epamlab.bll.DaoMethods;
import by.gsu.epamlab.interfaces.IDaoMethods;

public class FabricDAOMethods {

    public static IDaoMethods getDaoMethods()
    {
        return new DaoMethods();
    }
}
