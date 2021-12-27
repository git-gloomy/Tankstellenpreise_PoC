package gloomy.tankstellenpreise.request;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import gloomy.tankstellenpreise.Station;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationRequest extends Request {
    private final String apikey;

    private float lat = 49.322463f;
    private float lng = 7.339268f;
    private float rad = 10.0f;
    private String type = "all";
    private String sort = "dist";

    public StationRequest(String apikey) {
        super("https://creativecommons.tankerkoenig.de/json/list.php");
        this.apikey = apikey;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setRad(float rad) {
        this.rad = rad;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public boolean send() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("lat", Float.toString(lat));
        parameters.put("lng", Float.toString(lng));
        parameters.put("rad", Float.toString(rad));
        parameters.put("type", type);
        parameters.put("sort", sort);
        parameters.put("apikey", apikey);

        try {
            String result = readResult(parameters);
            if(!(new Gson().fromJson(result, JsonObject.class)).get("ok").getAsBoolean()) return false;

            String stationJson = new Gson().fromJson(result, JsonObject.class).get("stations").toString();
            System.out.println(stationJson);
            Type targetClassType = new TypeToken<ArrayList<Station>>() { }.getType();
            List<Station> stations = new Gson().fromJson(stationJson, targetClassType);
            for(Station station : stations) {
                System.out.println(station);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Failed to request station list. Error: " + e.getMessage());
            return false;
        }
    }
}
