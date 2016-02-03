package by.gsu.epamlab.constants;


public class SQLQueries {
    public static String ADD_NEW_PLAY="INSERT INTO play (name,about) values (?,?)";
    public static String ADD_NEW_DATE_TO_PLAY="INSERT INTO repertoire (id_play,date) values (?,?)";
    public static String SELECT_ALL_PLAY="SELECT * FROM play";
    public static String SELECT_ALL_DATE_ON_PLAY="SELECT * FROM repertoire WHERE id_play=?";
    public static String SELECT_IS_BRONE="SELECT id_user FROM order WHERE row=? AND place=?";
    public static String INCERT_BRONE="INSERT INTO order (id_play,row,place,id_user,price,status)" +
            "VALUES ((SELECT id FROM repertoire WHERE id_play=? AND date=?),?,?," +
            "(SELECT id FROM users WHERE user=?),?,?)";
    public static String DELETE_BRONE="DELETE FROM order WHERE id_user=? AND row=? AND place=?";

}
