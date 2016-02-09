package by.gsu.epamlab.model;


import by.gsu.epamlab.constants.Roles;

public class User {
    private final String userName;
    private final Roles role;


    public User() {
        this.userName = "";
        this.role = Roles.GUEST;
    }

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

    public int getIdRole()
    {
        return this.role.getNumber();
    }

    @Override
    public String toString() {
        return this.userName+ " "+ getRole();
    }


}
