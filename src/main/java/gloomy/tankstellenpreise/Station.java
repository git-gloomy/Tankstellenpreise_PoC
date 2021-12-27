package gloomy.tankstellenpreise;

public class Station {
    public String id;
    public String name;
    public String brand;
    public String street;
    public String place;
    public float lat;
    public float lng;
    public float dist;
    public float diesel;
    public float e5;
    public float e10;
    public boolean isOpen;
    public String houseNumber;
    public String postCode;

    @Override
    public String toString() {
        return "Station{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", street='" + street + '\'' +
                ", place='" + place + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", dist=" + dist +
                ", diesel=" + diesel +
                ", e5=" + e5 +
                ", e10=" + e10 +
                ", isOpen=" + isOpen +
                ", houseNumber='" + houseNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
