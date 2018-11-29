package com.tripco.t18.planner;

public class NearestNeighborThread implements Runnable{

    private Place[] places;
    private int[][] distanceTable;
    private int result;
    private int[] placesByIndex;

    public NearestNeighborThread(int[][] distanceTable, Place[] places) {
        this.places = places;
        this.distanceTable = distanceTable;
    }

    public int getResult(){
        return result;
    }

    public int[] getPlacesByIndex(){
        return placesByIndex;
    }


    public void run(){}

}