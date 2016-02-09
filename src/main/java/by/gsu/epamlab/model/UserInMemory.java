package by.gsu.epamlab.model;


import by.gsu.epamlab.constants.Roles;


public class UserInMemory {
    private final String userName;
    private final Roles role;
    private final String password;


    public UserInMemory(String userName, Roles role, String password) {
        this.userName = userName;
        this.role = role;
        this.password=password;
    }

    public String getUserName() {
        return userName;
    }

    public Roles getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }


}
