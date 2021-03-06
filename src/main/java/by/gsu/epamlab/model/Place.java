package by.gsu.epamlab.model;


public class Place {

    private int price;
    private int status;

    public Place(int price) {
        this.price = price;
        this.status = 0;
    }
    public Place(Place place)
    {
        this.price=place.getPrice();
        this.status=place.getStatus();
    }

    public int getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }

    public void setBooking() {
        if(this.status!=-1)this.status = Math.abs(this.status-1);
    }
    public void setSold()
    {
        this.status=-1;
    }
    public void setFree()
    {
        if(this.status!=-1) this.status=0;
    }
    public void setStatus(int status)
    {
        this.status=status;
    }

    @Override
    public String toString() {
        return "price=" + price +
                ", status=" + status;

    }
}

