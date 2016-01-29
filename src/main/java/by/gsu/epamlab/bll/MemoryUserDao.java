package by.gsu.epamlab.bll;

import by.gsu.epamlab.constants.Roles;
import by.gsu.epamlab.interfaces.IUserDao;
import by.gsu.epamlab.model.User;
import by.gsu.epamlab.model.UserInMemory;

import java.util.HashMap;
import java.util.Map;


public class MemoryUserDao implements IUserDao {

    private static final Object lock=new Object();

    static  Map <String,UserInMemory> users=new HashMap<>();
    static {
        users.put("admin", new UserInMemory("admin", Roles.ADMIN, "admin"));
        users.put("user", new UserInMemory("user", Roles.USER, "user"));
        users.put("guest", new UserInMemory("guest", Roles.GUEST, "guest"));
    }


    public MemoryUserDao() {

    }

    @Override
    public User getUser(String login, String passw) {

        User result=null;

        if(isUser(login)&& users.get(login).getPassword().equals(passw))
        {
            result= new User(users.get(login).getUserName(),users.get(login).getRole());
        }
        return result;
    }

    @Override
    public boolean createUser(String login, String passw, Roles role) {
        boolean result=false;
        synchronized (lock){
            if(!isUser(login))
            {
                users.put(login,new UserInMemory(login,role,passw));
                if(isUser(login))
                {
                    result=true;
                }
            }
        }

        return result;
    }

    @Override
    public boolean isUser(String login) {

        return users.containsKey(login);
    }

    @Override
    public void closeConnection() {
        //not need close, because in memory
    }
}
