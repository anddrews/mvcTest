package by.gsu.epamlab.interfaces;


import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.model.Play;

import java.util.List;

public interface IRepertoire {

    List<Play> getRepertoire() throws ReadFileException;
    Play getPlay(int id) throws ReadFileException;
}
