package by.gsu.epamlab.bll;


        import by.gsu.epamlab.constants.SQLQueries;
        import by.gsu.epamlab.exception.DAOException;
        import by.gsu.epamlab.model.Play;

        import java.sql.*;
        import java.util.Date;

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

    public static boolean bookPlace(int row,int place, int price, int idPlay, long date, String idUser)
    {
        try(
                Connection conn=ConnectionDb.getConnection();
                PreparedStatement isBrone=conn.prepareStatement(SQLQueries.SELECT_IS_BRONE);
                PreparedStatement brone=conn.prepareStatement(SQLQueries.INCERT_BRONE);
                PreparedStatement broneDel=conn.prepareStatement(SQLQueries.DELETE_BRONE))
            {
                isBrone.setInt(1,row);
                isBrone.setInt(2,place);
                ResultSet isBroneRes=isBrone.executeQuery();
                if(!isBroneRes.next())
                {
                    brone.setInt(1,idPlay);
                    brone.setTimestamp(2,new Timestamp(date));
                    brone.setInt(3,row);
                    brone.setInt(4,place);
                    //TODO brone.setInt(5,"idUser");
                    brone.setInt(6,price);
                    brone.setInt(1,1);
                    brone.executeUpdate();
                }
                else
                {
                    //TODO broneDel.setInt(1,idUser);
                    broneDel.setInt(2,row);
                    broneDel.setInt(3,place);
                    brone.executeUpdate();
                }


        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }


        return false;
    }


}
