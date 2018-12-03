package com.tripco.t18.planner;

import java.util.Arrays;

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

    public void run(){
        int[] visited = new int[places.length+1];
        Boolean[] visitedFlag = new Boolean[places.length];
        visitedFlag[startCity] = true;
        visited[0] = startCity;
        int index = 1;
        int origin = startCity;

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
        visited[index] = startCity;

        if(optimization.equals("2opt"))
            twoOptCheck(visited, distanceTable);

        result = calcTotalDist(visited);

        placesByIndex = Arrays.copyOf(visited, places.length);


    }

    private int calcTotalDist(int [] data){
        int legTotal = 0;
        for(int i = 0; i < data.length-1; i++){
            legTotal += distanceTable[data[i]][data[i+1]];
        }
        return legTotal;
    }

    private void twoOptCheck(int[] visited, int[][] table) {
        boolean improve = true;
        while (improve) {
            improve = false;
            for (int i = 0; i <= places.length - 3; i++) {
                for (int k = i + 2; k <= places.length - 1; k++) {

                    int delta = -table[visited[i]][visited[i + 1]] - table[visited[k]][visited[k + 1]] + table[visited[i]][visited[k]] + table[visited[i + 1]][visited[k + 1]];
                    if (delta < 0) {
                        twoOptSwap(visited, i + 1, k);
                        improve = true;
                    }
                }
            }
        }
    }

    private void twoOptSwap(int[] places, int i1, int k) {
        while (i1 < k) {
            int temp = places[i1];
            places[i1] = places[k];
            places[k] = temp;
            i1++;
            k--;
        }
    }
}