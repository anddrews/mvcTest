package by.gsu.epamlab.model;


import by.gsu.epamlab.constants.Roles;

public class User implements Comparable<User>{
    private final String userName;
    private final Roles role;


    public User(String userName, Roles role) {
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role.toString();
    }



    @Override
    public int compareTo(User o) {
        return o.getUserName().compareTo(this.userName);
    }
}
