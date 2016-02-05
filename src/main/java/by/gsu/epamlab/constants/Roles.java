package by.gsu.epamlab.constants;


public enum Roles {

    ADMIN(0),COURIERE(1),USER(2),GUEST(3);

    private int role;

    Roles(int i) {
        this.role=i;
    }

    public int getNumber()
    {
        return this.role;
    }
}
