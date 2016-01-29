package by.gsu.epamlab.constants;


public enum Roles {

    ADMIN(0),USER(1),GUEST(2);

    private int role;

    Roles(int i) {
        this.role=i;
    }

    public int getNumber()
    {
        return this.role;
    }
}
