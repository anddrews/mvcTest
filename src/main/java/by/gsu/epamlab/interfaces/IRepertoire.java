package by.gsu.epamlab.interfaces;


import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.model.Performance;

import java.util.List;

public interface IRepertoire {

    List<Performance> getRepertoire() throws ReadFileException;
    Performance getPerfomance(int id) throws ReadFileException;
}
