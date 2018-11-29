package com.tripco.t18.planner;

public class NearestNeighborThread implements Runnable{

    private static Place[] places;
    private static int[][] distanceTable;
    private int result;
    private int startCity;
    private int[] placesByIndex;
    private String optimization;

    public NearestNeighborThread(int startCity, String optimization) {
        this.optimization = optimization;
        this.startCity = startCity;
    }

    public int getResult(){
        return result;
    }

    public int[] getPlacesByIndex(){
        return placesByIndex;
    }


    public void run(){}

}