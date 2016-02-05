package by.gsu.epamlab.model;


import java.sql.Timestamp;

public class ReportRow {

    private int id;
    private Timestamp date;
    private String playName;
    private String user;
    private int row;
    private int place;
    private int price;
    private int status;

    public ReportRow(int id, Timestamp date, String playName,
                     String user, int row, int place, int price, int status) {
        this.id = id;
        this.date = date;
        this.playName = playName;
        this.user = user;
        this.row = row;
        this.place = place;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getPlayName() {
        return playName;
    }

    public String getUser() {
        return user;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public int getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ReportRow{" +
                "id=" + id +
                ", date=" + date +
                ", playName='" + playName + '\'' +
                ", user='" + user + '\'' +
                ", row=" + row +
                ", place=" + place +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
