package by.gsu.epamlab.bll;


import by.gsu.epamlab.bll.wrapperSql.*;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ReportCharacter;
import by.gsu.epamlab.constants.SQLQueries;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.interfaces.IDaoMethods;
import by.gsu.epamlab.model.Place;
import by.gsu.epamlab.model.Play;
import by.gsu.epamlab.model.ReportRow;
import by.gsu.epamlab.model.ZalePlane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class DaoMethods implements IDaoMethods{
    public static final Object LOCK =DaoMethods.class;

    public  boolean addNewPlayToDB(Play play) throws DAOException {
        int id_play=0;

        try(Connection conn=ConnectionDb.getConnection();
            PreparedStatement ps=conn.prepareStatement(SQLQueries.ADD_NEW_PLAY, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psDate=conn.prepareStatement(SQLQueries.ADD_NEW_DATE_TO_PLAY)) {

            ps.setString(Constants.ONE,play.getName());
            ps.setString(Constants.TWO,play.getDescription());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next())
            {
                id_play=rs.getInt(1);
            }


            for (Date tmp:play.getDate())
            {
                psDate.setInt(Constants.ONE,id_play);
                psDate.setTimestamp(Constants.TWO, new Timestamp(tmp.getTime()));
                psDate.executeUpdate();
            }

            return true;
        } catch ( SQLException e) {
            throw new DAOException(e.getMessage());
        }



    }

    public  boolean bookPlace(int row,int place, int price, int idPlay, long date, String user) throws DAOException {
        try(
                Connection conn=ConnectionDb.getConnection();
                PreparedStatement isBrone=conn.prepareStatement(SQLQueries.SELECT_IS_BRONE);
                PreparedStatement brone=conn.prepareStatement(SQLQueries.INSERT_BRONE);
                PreparedStatement broneDel=conn.prepareStatement(SQLQueries.DELETE_BRONE))
            {
                isBrone.setInt(Constants.ONE,row);
                isBrone.setInt(Constants.TWO,place);
                isBrone.setInt(Constants.THREE,idPlay);
                isBrone.setTimestamp(Constants.FOR, new Timestamp(date));

                brone.setInt(Constants.ONE, idPlay);
                brone.setTimestamp(Constants.TWO, new Timestamp(date));
                brone.setInt(Constants.THREE, row);
                brone.setInt(Constants.FOR, place);
                brone.setString(Constants.FIVE, user);
                brone.setInt(Constants.SIX, price);
                brone.setInt(Constants.SEVEN, Constants.ONE);

                broneDel.setInt(Constants.ONE, idPlay);
                broneDel.setTimestamp(Constants.TWO, new Timestamp(date));
                broneDel.setString(Constants.THREE, user);
                broneDel.setInt(Constants.FOR, row);
                broneDel.setInt(Constants.FIVE, place);

                synchronized (LOCK) {
                    ResultSet isBroneRes = isBrone.executeQuery();
                    if (!isBroneRes.next()) {
                        /*try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        brone.executeUpdate();
                    } else {
                        if(user.equals(isBroneRes.getString(Constants.ONE))) {
                            broneDel.executeUpdate();
                        }
                    }
                }
                return true;

        } catch (SQLException  e) {
           throw new DAOException(e.getMessage());
        }
    }

    public  void fillZale(ZalePlane zale, int idPlay, long date, String user) throws DAOException {
        try(Connection conn=ConnectionDb.getConnection();
            PreparedStatement selectPlaces=conn.prepareStatement(SQLQueries.SELECT_BRONE_PLACE_ON_PLAY))
        {
            selectPlaces.setInt(Constants.ONE,idPlay);
            selectPlaces.setTimestamp(Constants.TWO,new Timestamp(date));
            ResultSet res=selectPlaces.executeQuery();
            while (res.next())
            {
                int row=res.getInt(Constants.ONE);
                int place=res.getInt(Constants.TWO);
                String userInp=res.getString(Constants.THREE);
                int status=res.getInt(Constants.FOR);
                if(Constants.EMPTY_STRING.equals(user)||!userInp.equals(user))
                {
                    zale.getPlane().get(row)[place-Constants.ONE].setSold();
                }
                else
                {
                    zale.getPlane().get(row)[place-Constants.ONE].setStatus(status);
                }
            }
        } catch (SQLException  e) {
            throw new DAOException(e.getMessage());
        }
    }

    public  List<ReportRow> getReport( Map<ReportCharacter, String> character) throws DAOException {
        AbstractQuery query= new SelectAllFromOrders();
        for(Map.Entry<ReportCharacter,String> entry:character.entrySet())
        {
            switch (entry.getKey())
            {
                case DATE:
                {
                    query=new SelectFromOrdersOnDate(query);
                    break;
                }
                case USER:
                {
                    query=new SelectFromOrdersOnUser(query);
                    break;
                }
                case PLAY:
                {
                    query=new SelectFromOrdersOnPlay(query);
                    break;
                }

            }
        }

        try(Connection conn=ConnectionDb.getConnection();
            PreparedStatement select=conn.prepareStatement(query.getQuery())) {

            int count=1;
            for (Map.Entry<ReportCharacter,String> entry:character.entrySet())
            {
                if(entry.getKey().equals(ReportCharacter.DATE))
                {
                    SimpleDateFormat format=new SimpleDateFormat(Constants.FORMAT_DATE_FOR_REPORTS);
                    try {
                        Date dat=format.parse(entry.getValue());
                        select.setTimestamp(count,new Timestamp(dat.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        //TODO if date not parse simply not add to request
                    }

                }
                else
                {
                    select.setString(count,entry.getValue());
                }
                count++;
            }

            ResultSet report=select.executeQuery();
            List<ReportRow> result=new ArrayList<>();
            while (report.next())
            {
                int id=report.getInt(Constants.ONE);
                Timestamp date=report.getTimestamp(Constants.TWO);
                String playName=report.getString(Constants.THREE);
                String user=report.getString(Constants.FOR);
                int row=report.getInt(Constants.FIVE);
                int place=report.getInt(Constants.SIX);
                int price=report.getInt(Constants.SEVEN);
                int status=report.getInt(Constants.EIGHT);
                result.add(new ReportRow(id,date,playName,user,row,place,price,status));
            }
            return result;

        } catch (SQLException  e) {
            throw new DAOException(e.getMessage());
        }

    }

    public  void saveRepertoire(InputStream input) throws DAOException {
        List<Play> result=new ArrayList<>();

        try(InputStreamReader reader = new InputStreamReader(input);
                BufferedReader bufferedReader=new BufferedReader(reader)) {
            String stringRead;
            while ((stringRead = bufferedReader.readLine())!=null) {
                String name = stringRead;
                StringBuilder description = new StringBuilder();
                while (!(stringRead = bufferedReader.readLine()).equals(Constants.EMPTY_STRING)) {
                    description.append(stringRead);
                }
                Play play = new Play(0, name, description.toString());

                    while ((stringRead = bufferedReader.readLine()) != null && !stringRead.equals(Constants.EMPTY_STRING)) {
                        String[] rows = stringRead.split(Constants.SEPARATOR);
                        String date = rows[Constants.ZERO].trim();
                        String hour = rows[Constants.ONE].trim();


                        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_FOR_REPERTOIRE);
                        dateFormat.setTimeZone(TimeZone.getDefault());
                        Date dateToSave = dateFormat.parse(date + " " + hour);
                        play.setNewDate(dateToSave);


                    }
                result.add(play);

            }
            for (Play tmp:result) {
                addNewPlayToDB(tmp);
            }

        } catch (IOException | ParseException |ArrayIndexOutOfBoundsException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public  boolean courierBuy(int id) throws DAOException {
        boolean result=false;
        try(Connection conn=ConnectionDb.getConnection();
            PreparedStatement buy=conn.prepareStatement(SQLQueries.MAKE_ORDER)) {
            buy.setInt(Constants.ONE,id);
           if( buy.executeUpdate()==1) result=true;
            return result;
        } catch (SQLException  e) {
            throw new DAOException(e.getMessage());
        }

    }

    public  boolean courierDel(int id) throws DAOException {
        boolean result=false;
        try(Connection conn=ConnectionDb.getConnection();
            PreparedStatement buy=conn.prepareStatement(SQLQueries.DELETE_BRONE_ROW)) {
            buy.setInt(Constants.ONE,id);
           if( buy.executeUpdate()==1) result=true;
            return result;
        } catch (SQLException  e) {
            throw new DAOException(e.getMessage());
        }

    }





}
