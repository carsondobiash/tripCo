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
    String myUrl;
    String user;
    String pass;

    @Before
    public void initialize(){
        database = new Database();
        database.limit = 1;
        database.match = "Denver";
        testPlaces = new Place();
        testPlaces.id = "CO32";
        testPlaces.name = "Capri Heliport";
        testPlaces.latitude = 39.85279846191406;
        testPlaces.longitude = -104.97699737548828;
    }

    @Test
    public void testSearchString(){
        database.databaseSearch();
        String isTravis = System.getenv("TRAVIS");
        if(isTravis != null && isTravis.equals("true")){
            assertEquals(true, database.places.get(0).id.equals(testPlaces.id));
            assertEquals(true, database.places.get(0).name.equals(testPlaces.name));
            assertEquals(true, database.places.get(0).latitude == testPlaces.latitude);
            assertEquals(true, database.places.get(0).longitude == testPlaces.longitude);
            assertEquals(true, database.found == 25);
        }
        else {
            assertEquals(true, database.places.get(0).id.equals(testPlaces.id));
            assertEquals(true, database.places.get(0).name.equals(testPlaces.name));
            assertEquals(true, database.places.get(0).latitude == testPlaces.latitude);
            assertEquals(true, database.places.get(0).longitude == testPlaces.longitude);
            assertEquals(true, database.found == 30);
        }
    }
}
