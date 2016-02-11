package by.gsu.epamlab.interfaces;


import by.gsu.epamlab.bll.ZalePlane;
import by.gsu.epamlab.constants.ReportCharacter;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.model.Play;
import by.gsu.epamlab.model.ReportRow;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IDaoMethods {

     boolean addNewPlayToDB(Play play) throws DAOException;


     boolean bookPlace(int row,int place, int price, int idPlay, long date, String user) throws DAOException;

     void fillZale(ZalePlane zale, int idPlay, long date, String user) throws DAOException;

     List<ReportRow> getReport( Map<ReportCharacter, String> character) throws DAOException;

     void saveRepertoire(InputStream input) throws DAOException;

     boolean courierBuy(int id) throws DAOException;

     boolean courierDel(int id) throws DAOException ;
}
