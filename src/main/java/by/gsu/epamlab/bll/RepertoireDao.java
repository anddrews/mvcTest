package by.gsu.epamlab.bll;


import by.gsu.epamlab.constants.SQLQueries;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.exception.ReadFileException;
import by.gsu.epamlab.interfaces.IRepertoire;
import by.gsu.epamlab.model.Play;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepertoireDao implements IRepertoire{


    private static List<Play> plays;


    public RepertoireDao() {
            plays=reper();
    }

    private List<Play> reper()
    {
        List<Play> plays=new ArrayList<>();
        try (Connection connection=ConnectionDb.getConnection();
             PreparedStatement psPlay=connection.prepareStatement(SQLQueries.SELECT_ALL_PLAY);
             PreparedStatement psDate=connection.prepareStatement(SQLQueries.SELECT_ALL_DATE_ON_PLAY)){
            ResultSet getPlays= psPlay.executeQuery();
            while (getPlays.next())
            {
                int idPlay=getPlays.getInt(1);
                String name=getPlays.getString(2);
                String description=getPlays.getString(3);
                Play play=new Play(idPlay,name,description);
                psDate.setInt(1,idPlay);
                ResultSet getDate=psDate.executeQuery();
                while (getDate.next())
                {
                    Date dateSql=getDate.getTimestamp(3);
                    play.setNewDate(dateSql);
                }
                plays.add(play);
            }
        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }


        return plays;


    }


    @Override
    public List<Play> getRepertoire() throws ReadFileException {
        return plays;
    }

    @Override
    public Play getPlay(int id) throws ReadFileException {
        Play result=new Play();

        for(Play tmp:this.reper())
        {
            if(tmp.getId()==id)
            {
                result= tmp;
            }
        }

        return result;
    }
}
