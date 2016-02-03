package by.gsu.epamlab.constants;


public class SQLQueries {
    public static String ADD_NEW_PLAY="INSERT INTO play (name,about) values (?,?)";
    public static String ADD_NEW_DATE_TO_PLAY="INSERT INTO repertoire (id_play,date) values (?,?)";
    public static String SELECT_ALL_PLAY="SELECT * FROM play";
    public static String SELECT_ALL_DATE_ON_PLAY="SELECT * FROM repertoire WHERE id_play=?";
    public static String SELECT_ID_PLAY_ON_DATE="SELECT id FROM repertoire WHERE id_play=? AND date=?";

    public static String SELECT_IS_BRONE="SELECT id_user FROM orders WHERE row=? AND place=? AND" +
            " id_play=( " + SELECT_ID_PLAY_ON_DATE+" )";
    public static String INCERT_BRONE="INSERT INTO orders (id_play,row,place,id_user,price,status)" +
            "VALUES (( "+SELECT_ID_PLAY_ON_DATE+" ),?,?," +
            "(SELECT id FROM users WHERE user=?),?,?)";
    public static String DELETE_BRONE="DELETE  FROM orders WHERE " +
            "id_play=( "+SELECT_ID_PLAY_ON_DATE+") AND"+
            " id_user=( SELECT id FROM users WHERE user=?  ) AND row=? AND place=?";
    public static String SELECT_BRONE_PLACE_ON_PLAY="SELECT row,place,user,status " +
            "FROM orders INNER JOIN users ON (orders.id_user=users.id) " +
            "WHERE id_play=( "+ SELECT_ID_PLAY_ON_DATE+" )";
    public static String SELECT_BRONE_PLACE_ON_PLAY_WITHOUT_USER="SELECT row,place,user,status " +
            "FROM orders WHERE id_play=( "+ SELECT_ID_PLAY_ON_DATE+" )";


}
