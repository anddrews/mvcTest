package by.gsu.epamlab.constants;


public class Constants {

    //Properties file path

    public final static String PROPERTIES_PATH ="connectDB";

    //path to jsp page

    public final static String HOME_JSP = "/WEB-INF/views/index.jsp";
    public final static String LOGIN_JSP = "/WEB-INF/views/login.jsp";
    public final static String CREATE_JSP = "/WEB-INF/views/createUser.jsp";
    public final static String ERROR_JSP = "/WEB-INF/views/errorPage.jsp";

    //url for jump

    public final static String LOGIN_PAGE="/mvcTest/login";
    public final static String CREATE_USER_PAGE="/mvcTest/login/create";
    public final static String HOME_PAGE="/mvcTest/";

    // String constants

    public final static String USER="user";
    public final static String PASSWORD="password";
    public final static String ROLE="role";
    public final static String PASSWORD_SEC="passwordSec";
    public final static String ERROR="error";
    public final static String BAD_PARAMETER="Bad parameter";
    public final static String NOTHING="";
    public final static int ONE=1;
    public final static int TWO=2;
    public final static int THREE=3;

    //error message
    public final static String USER_ALREADY_IS="User with that name already is";
    public final static String PASSWORD_NOT_CORRECT="Not correct password, pls. reinput it";
    public final static String SOME_PROBLEM= "Some problem pls. try again";




}
