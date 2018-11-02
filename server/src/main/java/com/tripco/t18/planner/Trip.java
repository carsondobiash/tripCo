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
        this.map = svg();
    }

    /**
     * Returns an SVG containing the background and the legs of the trip.
     *
     * @return
     */
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

                if (line_of_SVGfile.equals("                    id=\"path181\"/>")) {
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

        String polygon = "<polyline points=\"";

        String splice_add = "\n<svg\n" +
                "        xmlns:svg=\"http://www.w3.org/2000/svg\"\n" +
                "        xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "        version=\"1.0\"\n" +
                "        width=\"1066.6073\"\n" +
                "        height=\"783.0824\"\n" +
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
            originList_latitude.add(degreesToPixel(origin.latitude, "latitude"));
            originList_longitude.add(degreesToPixel(origin.longitude, "longitude"));
            destinationList_latitude.add(degreesToPixel(destination.latitude, "latitude"));
            destinationList_longitude.add(degreesToPixel(destination.longitude, "longitude"));

        }


        for (int i = 0; i < places.length; i++) {
            polygon += originList_longitude.get(i).toString() + "," + originList_latitude.get(i).toString() + " ";
            polygon += destinationList_longitude.get(i).toString() + "," + destinationList_latitude.get(i).toString() + " ";
        }

        polygon += "\"\nfill=\"none\" stroke-width=\"4\" stroke=\"blue\" id=\"s7\"/>";

        return splice_add + polygon + "</svg>";
    }

    private double degreesToPixel(double deg, String type) {

        if (type.equals("longitude")) {

            return Math.abs((1066.6073 / 7.5) * (-109.3 - deg));
        } else if (type.equals("latitude")) {

            return Math.abs((783.0824 / 4.4) * (41.2 - deg));
        } else {
            return -1;
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

    private void twoOptSwap(Place[] places, int i1, int k){
        while(i1 < k){
            Place temp = places[i1];
            places[i1] = places[k];
            places[k] = temp;
            i1++; k--;
        }
    }
    private void twoOptCheck(Place[] visited, int[][] table){

        boolean improve = true;
        while(improve){
            improve = false;
            for(int i = 0; i <= places.length-3; i++){
                for(int k = i+2; k <= places.length-1; k++){

                    int delta = -calcLeg(visited[i], visited[i+1])-calcLeg(visited[k], visited[k+1]) + calcLeg(visited[i], visited[k]) + calcLeg(visited[i+1], visited[k+1]);
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
        Place[] optPlace = new Place[places.length];

        for (int i = 0; i < places.length; i++) {

            Place start = places[i];
            Place[] visited = new Place[places.length+1];
            Boolean[] visitedFlag = new Boolean[places.length];
            visitedFlag[i] = true;
            visited[0] = start;
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
                visited[index] = places[next];
                origin = next;
                index += 1;
            }
            visited[index] = start;

            if(opt.equals("2opt"))
                twoOptCheck(visited, distanceTable);

            int legTotal = calcTotalDist(visited);

            if (legTotal < shortestPath){

                shortestPath = legTotal;
                optPlace = Arrays.copyOf(visited, places.length);

            }

        }

        return optPlace;

    }
    private int[][] fillDistanceTable(int[][] table){
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                table[i][j] = calcLeg(places[i%places.length], places[j%places.length]);
            }
        }
        return table;
    }

    private int calcTotalDist(Place [] data){
        int legTotal = 0;
        for(int i = 0; i < data.length-1; i++){
            legTotal += calcLeg(data[i], data[i+1]);
        }
        return legTotal;
    }
}

