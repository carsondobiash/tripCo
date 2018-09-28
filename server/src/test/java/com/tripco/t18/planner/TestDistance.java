package com.tripco.t18.planner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TestDistance {

    Distance distance;

    @Before
    public void initialize(){
        Place origin = new Place();
        Place destination = new Place();
        origin.latitude = 39.7392;
        origin.longitude = -104.9903;
        destination.latitude = 40.015;
        destination.longitude = -105.2706;

        distance = new Distance(origin, destination, "miles");

    }

    @Test
    public void testCalculateDistanceMiles(){
        int expectedDistance = 24;
        distance.calculatedistance();
        assertEquals(expectedDistance, distance.distance);
    }

    @After

    @Before
    public void initializeKm(){
        Place origin = new Place();
        Place destination = new Place();
        origin.latitude = 40.5853;
        origin.longitude = -105.0844;
        destination.latitude = -33.8688;
        destination.longitude = 151.2093;

        distance = new Distance(origin, destination, "kilometers");
    }

    @Test
    public  void testCalculateDistanceKm(){
        int expectedDistance = 13432;
        distance.calculatedistance();
        System.out.println(distance.calculatedistance());
        assertEquals(expectedDistance, distance.distance);
    }
}
