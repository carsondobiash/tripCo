package com.tripco.t18.planner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Trip class supports TFFI so it can easily be converted to/from Json by Gson.
 */
public class Trip {
    // The variables in this class should reflect TFFI.
    public Integer version;
    public String type;
    public String title;
    public Option options;
    public Place[] places;
    public ArrayList<Integer> distances = new ArrayList<>();
    public String map;

    /**
     * The top level method that does planning.
     * At this point it just adds the map and distances for the places in order.
     * It might need to reorder the places in the future.
     */
    public void plan() {
        this.places = optimized();
        this.distances = legDistances();
        this.map = typeOfMap();
    }

    /**
     * Returns an SVG containing the background and the legs of the trip.
     *
     *
     */

    private String typeOfMap(){

        if(options.map == (null)){
            return svg();
        }
        else if(options.map.equals("svg")){
            return svg();
        }
        else if(options.map.equals("kml")){
            return kml();
        }
        else {
            return svg();
        }
    }

    private String kml(){
        String KML_as_String = "";

        BufferedReader reader;

        try {

            reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/BaseKML.kml")));

        } catch (Exception e) {

            return "";

        }

        String line_of_KMLfile = "";

        try {

            while ((line_of_KMLfile = reader.readLine()) != null) {

                KML_as_String += line_of_KMLfile;

                if (line_of_KMLfile.equals("                <coordinates>")) {
                    String splice_add = spliceKML();
                    KML_as_String += splice_add;
                }

            }

        } catch (Exception e) {
            // TODO
        }

        //Return String of the KML File

        return KML_as_String;
    }

    private String spliceKML(){

        Place place;
        String coordinates = "                     ";

        for (int i = 0; i < places.length; i++) {
            place = places[i % places.length];

            coordinates += String.valueOf(place.longitude) + ", " + String.valueOf(place.latitude) + " ";

        }

        return coordinates;
    }

    private String svg() {


        String SVG_as_String = "";

        //Try to load the SVG file located in resources and throw an error if not found.
        BufferedReader reader;

        try {

            reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/World_map_with_nations.svg")));

        } catch (Exception e) {

            return "";

        }

        String line_of_SVGfile = "";

        try {

            while ((line_of_SVGfile = reader.readLine()) != null) {

                SVG_as_String += line_of_SVGfile;

                if (line_of_SVGfile.equals("       id=\"Antarctique\" />")) {
                    String splice_add = spliceSVG();
                    SVG_as_String += splice_add;
                }

            }

        } catch (Exception e) {
            // TODO
        }

        //Return String of the SVG File

        return SVG_as_String;

    }

    private String spliceSVG() {

        String dots;

        String splice_add;

        Place origin;
        Place destination;

        ArrayList<Double> originList_latitude = new ArrayList<>();
        ArrayList<Double> originList_longitude = new ArrayList<>();
        ArrayList<Double> destinationList_latitude = new ArrayList<>();
        ArrayList<Double> destinationList_longitude = new ArrayList<>();

        for (int i = 0; i < places.length; i++) {

            origin = places[i % places.length];
            destination = places[(i + 1) % places.length];

            originList_longitude.add(degreesToPixel(origin.longitude, "longitude"));
            destinationList_longitude.add(degreesToPixel(destination.longitude, "longitude"));

            originList_latitude.add(degreesToPixel(origin.latitude, "latitude"));
            destinationList_latitude.add(degreesToPixel(destination.latitude, "latitude"));

        }



        splice_add = drawLines(originList_latitude, originList_longitude, destinationList_latitude, destinationList_longitude);

        for (int i = 0; i < places.length; i++) {

            dots = "<circle cx=\"";

            dots += originList_longitude.get(i).toString()
                    + "\" cy=\"" + originList_latitude.get(i).toString()
                    + "\" r=\"2\" stroke=\"green\" stroke-width=\"1\" fill=\"pink\" />";

            splice_add += dots;

        }

        return splice_add + "</svg>";
    }

    private String drawLines(ArrayList originLat, ArrayList originLong, ArrayList destinationLat, ArrayList destinationLong){

        String splice = "\n<svg\n" + "        xmlns:svg=\"http://www.w3.org/2000/svg\"\n"
                + "        xmlns=\"http://www.w3.org/2000/svg\"\n" + "        version=\"1.0\"\n"
                + "        width=\"1024\"\n" + "        height=\"512\"\n" + "        id=\"svg2339\">";
        String line;
        Place origin;
        Place destination;

        for (int i = 0; i < places.length; i++) {

            origin = places[i % places.length];
            destination = places[(i + 1) % places.length];

            if(checkForWrap(origin.longitude, destination.longitude) == false) {
                line = "<line x1=\"" + originLong.get(i).toString() + "\" y1=\""
                        + originLat.get(i).toString() + "\" x2=\"" + destinationLong.get(i).toString() + "\" y2=\""
                        + destinationLat.get(i).toString() + "\" style=\"stroke:rgb(128,0,128);stroke-width:2\" />";
                splice += line;
            }
            else{

                if(origin.longitude <= 0) {

                    line = "<line x1=\"" + String.valueOf(degreesToPixel(origin.longitude, "longitude")) + "\" y1=\""
                            + String.valueOf(degreesToPixel(origin.latitude, "latitude")) + "\" x2=\"" + String.valueOf(degreesToPixel(destination.longitude - 360, "longitude")) + "\" y2=\""
                            + String.valueOf(degreesToPixel(destination.latitude, "latitude")) + "\" style=\"stroke:rgb(128,0,128);stroke-width:2\" />";
                    splice += line;

                    line = "<line x1=\"" + String.valueOf(degreesToPixel(origin.longitude + 360, "longitude")) + "\" y1=\""
                            + String.valueOf(degreesToPixel(origin.latitude, "latitude")) + "\" x2=\"" + String.valueOf(degreesToPixel(destination.longitude, "longitude")) + "\" y2=\""
                            + String.valueOf(degreesToPixel(destination.latitude, "latitude")) + "\" style=\"stroke:rgb(128,0,128);stroke-width:2\" />";
                    splice += line;

                }
            }

        }

        return splice;

    }

    private double degreesToPixel(double deg, String type) {

        if (type.equals("longitude")) {

            return pixelLongitude(deg);

        } else if (type.equals("latitude")) {

           return pixelLatitude(deg);

        } else {
            return -1;
        }

    }

    private double pixelLongitude(double deg){

        return ((800.00 / 360.00) * (180.00 + deg));

    }

    private double pixelLatitude(double deg){

        if (0 >= deg) {
            return Math.abs(((400.00 / 180.00) * (90.00 + Math.abs(deg))));
        }
        else{
            return Math.abs(((400.00 / 180.00) * (-90.00 + deg)));
        }

    }

    private boolean checkForWrap(double originLong, double destLong){

        if (Math.abs((originLong-destLong)) > 180){
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Returns the distances between consecutive places,
     * including the return to the starting point to make a round trip.
     *
     *
     */
    private ArrayList<Integer> legDistances() {

        ArrayList<Integer> dist = new ArrayList<Integer>();

        int store;

        Place origin;
        Place destination;

        for (int i = 0; i < places.length; i++) {
            origin = places[i % places.length];
            destination = places[(i + 1) % places.length];

            store = calcLeg(origin,destination);
            dist.add(store);
        }

        return dist;
    }

    private int calcLeg(Place origin, Place destination) {

        int store;

        if (options.units.equals("user defined")) {
            Distance distance = new Distance(origin, destination, options.units, options.unitName, options.unitRadius);
            store = distance.calculatedistance();
        } else {
            Distance distance = new Distance(origin, destination, options.units);
            store = distance.calculatedistance();
        }

        return store;

    }

    private Place[] optimized() {

        //Nearest Neighbor algorithm.
        if(options.optimization == (null))
            return this.places;
        if (options.optimization.equals("short")) {
            Place[] update = nearestNeighbor("NN");

            return update;
        }
        //2-opt
        else if(options.optimization.equals("shorter")){
            Place[] update = nearestNeighbor("2opt");
            return update;
        }
        else return this.places;
    }

    private Place[] nearestNeighbor(String opt) {
        int distanceTable[][] = new int[places.length+1][places.length+1];
        fillDistanceTable(distanceTable);
        NearestNeighborThread.distanceTable = fillDistanceTable(distanceTable);
        NearestNeighborThread.places = places;
        Thread[] threads = new Thread[places.length];
        NearestNeighborThread[] data = new NearestNeighborThread[places.length];
        int minDistance = Integer.MAX_VALUE;

        for(int startCity = 0; startCity<places.length; startCity++){
            NearestNeighborThread datum = new NearestNeighborThread(startCity, opt);
            data[startCity] = datum;
            threads[startCity] = new Thread(datum);
            threads[startCity].start();
        }

        for(Thread thread : threads){
            try {
                thread.join();
            }catch(InterruptedException e){}
        }

        int[] minByIndex = new int[places.length];
        for(NearestNeighborThread datum : data){
            if(minDistance > datum.getResult()){
                minDistance = datum.getResult();
                minByIndex = datum.getPlacesByIndex();
            }
        }

        Place[] result = new Place[places.length];
        for(int i = 0; i < places.length; i++){
            result[i] = places[minByIndex[i]];
        }

        return result;


    }

    private int[][] fillDistanceTable(int[][] table){
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                table[i][j] = calcLeg(places[i%places.length], places[j%places.length]);
            }
        }
        return table;
    }


}

