package gloomy.tankstellenpreise.request;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public abstract class Request {
    private final String url;

    public Request(String url) {
        this.url = url;
    }

    protected String readResult(Map<String, String> parameters) throws Exception {
        URL urlObject = new URL(url + "?" + getParamsString(parameters));
        HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();

        con.setDoOutput(true);
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setRequestMethod("GET");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            return readAll(in);
        } catch (IOException ex) {
            System.out.println("Request failed. Error: " + ex.getMessage());
            return null;
        }
    }

    protected String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }

    protected static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public abstract boolean send();
}
