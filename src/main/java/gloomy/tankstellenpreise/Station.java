package gloomy.tankstellenpreise;

public class Station {
    public static float amount;
    public static float consumption;
    public static String type;

    public float computePrice() {
        if(computedPrice != -1.0f)
            return computedPrice;
        else if(type.equals("e5"))
            return (e5 * amount) + (e5 * dist * (consumption / 100.0f));
        else if(type.equals("e10"))
            return (e10 * amount) + (e10 * dist * (consumption / 100.0f));
        else if(type.equals("diesel"))
            return (diesel * amount) + (diesel * dist * (consumption / 100.0f));
        else
            return -1.0f;
    }

    public float rawPrice() {
        if(computedPrice != -1.0f)
            return rawPrice;
        else if(type.equals("e5"))
            return e5 * amount;
        else if(type.equals("e10"))
            return e10 * amount;
        else if(type.equals("diesel"))
            return diesel * amount;
        else
            return -1.0f;
    }

    private float computedPrice = -1.0f;
    private float rawPrice = -1.0f;
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
                ", rawPrice='" + rawPrice() + '\'' +
                ", combinedPrice='" + computePrice() + '\'' +
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
