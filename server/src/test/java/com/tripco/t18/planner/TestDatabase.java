package com.tripco.t18.planner;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TestDatabase {
    Database database;
    Place testPlaces;
    ArrayList<Place> test = new ArrayList<>();

    @Before
    public void initialize(){
        database = new Database();
        database.limit = 1;
        database.match = "International";
        testPlaces = new Place();
        testPlaces.id = "6CO3";
        testPlaces.name = "Wine Glass International Airport";
        testPlaces.latitude = 37.63639831542969;
        testPlaces.longitude = -103.65799713134766;
        test.add(testPlaces);
    }

    @Test
    public void testSearchString(){
        database.databaseSearch();
        assertEquals(true, database.places.get(0).id.equals(testPlaces.id));
        assertEquals(true, database.places.get(0).name.equals(testPlaces.name));
        assertEquals(true, database.places.get(0).latitude == testPlaces.latitude);
        assertEquals(true, database.places.get(0).longitude == testPlaces.longitude);
    }
}
