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
        String mapString;
        Map map = new Map(places);
        if(options.map == (null)){
             mapString = map.svg();
        }
        else if(options.map.equals("svg")){
            mapString = map.svg();
        }
        else if(options.map.equals("kml")){
            mapString = map.kml();
        }
        else {
            mapString = map.svg();
        }
        return mapString;
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

