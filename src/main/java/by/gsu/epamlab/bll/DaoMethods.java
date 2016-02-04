package by.gsu.epamlab.bll;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.SQLQueries;
        import by.gsu.epamlab.exception.DAOException;
        import by.gsu.epamlab.model.Place;
        import by.gsu.epamlab.model.Play;

        import java.sql.*;
        import java.util.Date;
        import java.util.Map;

public class DaoMethods {
    public static final Object LOCK =new Object();

    public boolean addNewPlayToDB(Play play)
    {
        int id_play=0;

        try(Connection connection=ConnectionDb.getConnection();
            PreparedStatement ps=connection.prepareStatement(SQLQueries.ADD_NEW_PLAY, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psDate=connection.prepareStatement(SQLQueries.ADD_NEW_DATE_TO_PLAY)) {

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


        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean bookPlace(int row,int place, int price, int idPlay, long date, String user)
    {
        boolean result=false;
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
                synchronized (LOCK) {
                    ResultSet isBroneRes = isBrone.executeQuery();
                    if (!isBroneRes.next()) {
                        brone.setInt(Constants.ONE, idPlay);
                        brone.setTimestamp(Constants.TWO, new Timestamp(date));
                        brone.setInt(Constants.THREE, row);
                        brone.setInt(Constants.FOR, place);
                        brone.setString(Constants.FIVE, user);
                        brone.setInt(Constants.SIX, price);
                        brone.setInt(Constants.SEVEN, Constants.ONE);
                        brone.executeUpdate();
                    } else {
                        if(user.equals(isBroneRes.getString(1))) {
                            broneDel.setInt(Constants.ONE, idPlay);
                            broneDel.setTimestamp(Constants.TWO, new Timestamp(date));
                            broneDel.setString(Constants.THREE, user);
                            broneDel.setInt(Constants.FOR, row);
                            broneDel.setInt(Constants.FIVE, place);
                            broneDel.executeUpdate();
                        }
                    }
                    result = true;
                }


        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }


        return result;
    }

    public static Map<Integer,Place[]> fillZale(Map<Integer,Place[]> zale, int idPlay, long date, String user)
    {
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
                    zale.get(row)[place-Constants.ONE].setSold();
                }
                else
                {
                    zale.get(row)[place-Constants.ONE].setStatus(status);
                }
            }
        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }
        return zale;
    }


}