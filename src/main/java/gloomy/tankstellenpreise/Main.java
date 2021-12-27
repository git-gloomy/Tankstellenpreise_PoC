package gloomy.tankstellenpreise;

import gloomy.tankstellenpreise.request.StationRequest;

public class Main {

    private final static String apikey = "";

    public static void main(String[] args) {
        StationRequest sr = new StationRequest(apikey);
        sr.send();
    }
}
