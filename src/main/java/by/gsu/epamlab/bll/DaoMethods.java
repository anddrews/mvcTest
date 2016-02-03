package by.gsu.epamlab.bll;


        import by.gsu.epamlab.constants.SQLQueries;
        import by.gsu.epamlab.exception.DAOException;
        import by.gsu.epamlab.model.Place;
        import by.gsu.epamlab.model.Play;

        import java.sql.*;
        import java.util.Date;
        import java.util.Map;

public class DaoMethods {

    public boolean addNewPlayToDB(Play play)
    {
        int id_play=0;

        try(Connection connection=ConnectionDb.getConnection();
            PreparedStatement ps=connection.prepareStatement(SQLQueries.ADD_NEW_PLAY, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psDate=connection.prepareStatement(SQLQueries.ADD_NEW_DATE_TO_PLAY)) {

            ps.setString(1,play.getName());
            ps.setString(2,play.getDescription());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next())
            {
                id_play=rs.getInt(1);
            }


            for (Date tmp:play.getDate())
            {
                psDate.setInt(1,id_play);
                psDate.setTimestamp(2, new Timestamp(tmp.getTime()));
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
                PreparedStatement brone=conn.prepareStatement(SQLQueries.INCERT_BRONE);
                PreparedStatement broneDel=conn.prepareStatement(SQLQueries.DELETE_BRONE))
            {
                isBrone.setInt(1,row);
                isBrone.setInt(2,place);
                isBrone.setInt(3,idPlay);
                isBrone.setTimestamp(4, new Timestamp(date));
                ResultSet isBroneRes=isBrone.executeQuery();
                if(!isBroneRes.next())
                {
                    brone.setInt(1,idPlay);
                    brone.setTimestamp(2,new Timestamp(date));
                    brone.setInt(3,row);
                    brone.setInt(4,place);
                    brone.setString(5,user);
                    brone.setInt(6,price);
                    brone.setInt(7,1);
                    brone.executeUpdate();
                }
                else
                {
                    broneDel.setInt(1, idPlay);
                    broneDel.setTimestamp(2,new Timestamp(date));
                    broneDel.setString(3,user);
                    broneDel.setInt(4,row);
                    broneDel.setInt(5,place);
                    broneDel.executeUpdate();
                }
                result=true;


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
            selectPlaces.setInt(1,idPlay);
            selectPlaces.setTimestamp(2,new Timestamp(date));
            ResultSet res=selectPlaces.executeQuery();
            while (res.next())
            {
                int row=res.getInt(1);
                int place=res.getInt(2);
                String userInp=res.getString(3);
                int status=res.getInt(4);
                if("".equals(user)||!userInp.equals(user))
                {
                    zale.get(row)[place-1].setSold();
                }
                else
                {
                    zale.get(row)[place-1].setStatus(status);
                }
            }
        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }
        return zale;
    }


}
