package gloomy.tankstellenpreise;

import gloomy.tankstellenpreise.request.StationRequest;

import org.apache.commons.cli.*;

import java.util.List;

public class Tankstellenpreise {

    private final static String apikey = "";

    public static void main(String[] args) {
        Options options = new Options();

        Option consumption = new Option("c", "consumption", true, "fuel consumption with which to compute in litres per 100 kilometers");
        consumption.setRequired(false);
        options.addOption(consumption);

        Option amount = new Option("a", "amount", true, "amount of fuel you want to refuel in litres");
        amount.setRequired(false);
        options.addOption(amount);

        Option type = new Option("t", "type", true, "e5, e10 or diesel");
        type.setRequired(false);
        options.addOption(type);

        Option longitude = new Option("lng", "longitude", true, "longitude of the location around which to search for fuel stations");
        longitude.setRequired(false);
        options.addOption(longitude);

        Option latitude = new Option("lat", "latitude", true, "latitude of the location around which to search for fuel stations");
        latitude.setRequired(false);
        options.addOption(latitude);

        Option radius = new Option("r", "radius", true, "radius to search for fuel stations in kilometers");
        radius.setRequired(false);
        options.addOption(radius);

        Option includeClosed = new Option("ic", "includeclosed", false, "include closed stations");
        radius.setRequired(false);
        options.addOption(includeClosed);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;//not a good practice, it serves it purpose

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("tankstellenpreise", options);

            System.exit(1);
        }

        StationRequest sr = new StationRequest(apikey);
        float lat = 49.322463f;
        float lng = 7.339268f;
        float rad = 10.0f;
        Station.type = cmd.getOptionValue("type", "e5");
        try {
            Station.amount = Float.parseFloat(cmd.getOptionValue("amount", "20.0"));
        } catch (NumberFormatException ex) {
            System.out.println("IllegalArgument: amount must be of type \"float\".");
        }
        try {
            Station.consumption = Float.parseFloat(cmd.getOptionValue("consumption", "6.0"));
        } catch (NumberFormatException ex) {
            System.out.println("IllegalArgument: radius must be of type \"float\".");
        }
        try {
            lat = Float.parseFloat(cmd.getOptionValue("latitude", "49.322463"));
        } catch (NumberFormatException ex) {
            System.out.println("IllegalArgument: latitude must be of type \"float\".");
        }
        sr.setLatitude(lat);
        try {
            lng = Float.parseFloat(cmd.getOptionValue("longitude", "7.339268"));
        } catch (NumberFormatException ex) {
            System.out.println("IllegalArgument: longitude must be of type \"float\".");
        }
        sr.setLongitude(lng);
        try {
            rad = Float.parseFloat(cmd.getOptionValue("radius", "10.0"));
        } catch (NumberFormatException ex) {
            System.out.println("IllegalArgument: radius must be of type \"float\".");
        }
        sr.setRadius(rad);

        System.out.println("Computing prices for the following arguments: " + '\t'
            + "amount: " + Station.amount + '\t'
            + "consumption: " + Station.consumption + '\t'
            + "type: " + Station.type + '\t'
            + "lat: " + lat + '\t'
            + "lng: " + lng + '\t'
            + "radius: " + rad);

        sr.send();
        List<Station> stations = sr.getStations();

        stations.sort((t1, t2) -> Float.compare(t1.computePrice(), t2.computePrice()));

        for(Station station : stations) {
            if(!station.isOpen && !cmd.hasOption(includeClosed)) continue;
            System.out.println(station);
        }

        System.exit(0);
    }
}
