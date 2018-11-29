package com.tripco.t18.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t18.server.HTTP;
import spark.Request;

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
     * @return
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

        String polyline;
        String dots;

        String splice_add = "\n<svg\n" +
                "        xmlns:svg=\"http://www.w3.org/2000/svg\"\n" +
                "        xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "        version=\"1.0\"\n" +
                "        width=\"1024\"\n" +
                "        height=\"512\"\n" +
                "        id=\"svg2339\">";

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


        for (int i = 0; i < places.length; i++) {

            dots = "<circle cx=\"";
            String line;

            origin = places[i % places.length];
            destination = places[(i + 1) % places.length];

            if(checkForWrap(origin.longitude, destination.longitude) == false) {
                polyline = "<line x1=\"" + originList_longitude.get(i).toString() + "\" y1=\""
                        + originList_latitude.get(i).toString() + "\" x2=\""
                        + destinationList_longitude.get(i).toString() + "\" y2=\""
                        + destinationList_latitude.get(i).toString() + "\" style=\"stroke:rgb(128,0,128);stroke-width:2\" />";
                splice_add += polyline;
            }
            else{

                if(origin.longitude <= 0) {

                    line = "<line x1=\"" + String.valueOf(degreesToPixel(origin.longitude, "longitude")) + "\" y1=\""
                            + String.valueOf(degreesToPixel(origin.latitude, "latitude")) + "\" x2=\""
                            + String.valueOf(degreesToPixel(destination.longitude - 360, "longitude")) + "\" y2=\""
                            + String.valueOf(degreesToPixel(destination.latitude, "latitude")) + "\" style=\"stroke:rgb(128,0,128);stroke-width:2\" />";
                    splice_add += line;

                    line = "<line x1=\"" + String.valueOf(degreesToPixel(origin.longitude + 360, "longitude")) + "\" y1=\""
                            + String.valueOf(degreesToPixel(origin.latitude, "latitude")) + "\" x2=\""
                            + String.valueOf(degreesToPixel(destination.longitude, "longitude")) + "\" y2=\""
                            + String.valueOf(degreesToPixel(destination.latitude, "latitude")) + "\" style=\"stroke:rgb(128,0,128);stroke-width:2\" />";
                    splice_add += line;

                }
            }

            dots += originList_longitude.get(i).toString() + "\" cy=\"" + originList_latitude.get(i).toString() + "\" r=\"2\" stroke=\"green\" stroke-width=\"1\" fill=\"pink\" />";

            splice_add += dots;

        }

        return splice_add + "</svg>";
    }

    private double degreesToPixel(double deg, String type) {

        if (type.equals("longitude")) {

            return ((800.00 / 360.00) * (180.00 + deg));

        } else if (type.equals("latitude")) {

            if (0 >= deg) {
                return Math.abs(((400.00 / 180.00) * (90.00 + Math.abs(deg))));
            }
            else{
                return Math.abs(((400.00 / 180.00) * (-90.00 + deg)));
            }

        } else {
            return -1;
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
     * @return
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

    private void twoOptSwap(int[] places, int i1, int k){
        while(i1 < k){
            int temp = places[i1];
            places[i1] = places[k];
            places[k] = temp;
            i1++; k--;
        }
    }
    private void twoOptCheck(int[] visited, int[][] table){
        boolean improve = true;
        while(improve){
            improve = false;
            for(int i = 0; i <= places.length-3; i++){
                for(int k = i+2; k <= places.length-1; k++){

                    int delta = -table[visited[i]][visited[i+1]]-table[visited[k]][visited[k+1]] + table[visited[i]][visited[k]] + table[visited[i+1]][visited[k+1]];
                    if(delta < 0){
                        twoOptSwap(visited, i+1, k);
                        improve = true;
                    }
                }
            }
        }
    }
    private Place[] nearestNeighbor(String opt) {
        int distanceTable[][] = new int[places.length+1][places.length+1];
        fillDistanceTable(distanceTable);

        int shortestPath = Integer.MAX_VALUE;
        int[] optIndex = new int[places.length];

        for (int i = 0; i < places.length; i++) {

            int[] visited = new int[places.length+1];
            Boolean[] visitedFlag = new Boolean[places.length];
            visitedFlag[i] = true;
            visited[0] = i;
            int index = 1;
            int origin = i;

            while (index != places.length) {


                int shortestLeg = Integer.MAX_VALUE;
                int next = -1;


                for (int dest = 0; dest < places.length; dest++) {

                    if (visitedFlag[dest] == null || !(visitedFlag[dest])) {
                        if (distanceTable[origin][dest] < shortestLeg){
                            next = dest;
                            shortestLeg = distanceTable[origin][dest];

                        }
                    }
                }

                visitedFlag[next] = true;
                visited[index] = next;
                origin = next;
                index += 1;
            }
            visited[index] = i;

            if(opt.equals("2opt"))
                twoOptCheck(visited, distanceTable);

            int legTotal = calcTotalDist(visited);

            if (legTotal < shortestPath){

                shortestPath = legTotal;
                optIndex = Arrays.copyOf(visited, places.length);

            }

        }
        Place[] optimized = new Place[places.length];
        for(int i = 0; i<optimized.length; i++){
            optimized[i] = places[optIndex[i]];
        }
        return optimized;

    }
    private int[][] fillDistanceTable(int[][] table){
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                table[i][j] = calcLeg(places[i%places.length], places[j%places.length]);
            }
        }
        return table;
    }

    private int calcTotalDist(int [] data){
        int legTotal = 0;
        for(int i = 0; i < data.length-1; i++){
            legTotal += calcLeg(places[data[i]], places[data[i+1]]);
        }
        return legTotal;
    }
}

