package by.gsu.epamlab.fabrics;


import by.gsu.epamlab.bll.RepertoireInCsv;
import by.gsu.epamlab.interfaces.IRepertoire;

public class FabricRepertoire {

    public static IRepertoire getRepertoire()
    {
        return new RepertoireInCsv();
    }
}
