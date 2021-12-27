package gloomy.tankstellenpreise.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceRequest extends Request {
    private final String apikey;

    private List<String> ids = new ArrayList<>();

    public PriceRequest(String apikey) {
        super("https://creativecommons.tankerkoenig.de/json/prices.php");
        this.apikey = apikey;
    }

    public void addId(String id) {
        this.ids.add(id);
    }

    public boolean send() {
        Map<String, String> parameters = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        String sep = "";
        for(String id : ids) {
            sb.append(sep);
            sb.append(id);
            sep = ",";
        }

        parameters.put("ids", sb.toString());
        parameters.put("apikey", apikey);

        try {
            String result = readResult(parameters);
            System.out.println(result);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to request station list. Error: " + e.getMessage());
            return false;
        }
    }
}
