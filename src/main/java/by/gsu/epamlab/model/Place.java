package by.gsu.epamlab.model;


public class Place {

    private int place;
    private int row;
    private boolean isFree;

    public Place(int row, int place, boolean isFree) {
        this.place = place;
        this.row = row;
        this.isFree = isFree;
    }

    public int getPlace() {
        return place;
    }

    public int getRow() {
        return row;
    }

    public boolean getIsFree() {
        return isFree;
    }

    public void setBooking()
    {
        this.isFree=!this.isFree;
    }
}
