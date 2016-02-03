package by.gsu.epamlab.fabrics;


import by.gsu.epamlab.bll.RepertoireDao;
import by.gsu.epamlab.bll.RepertoireInCsv;
import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.interfaces.IRepertoire;
import by.gsu.epamlab.model.Play;

import java.util.ArrayList;
import java.util.List;

public class FabricRepertoire {

    public static IRepertoire getRepertoire()
    {
        return new RepertoireDao();
    }
}
