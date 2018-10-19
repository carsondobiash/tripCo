package com.tripco.t18.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/*
  This class contains tests for the Trip class.
 */
@RunWith(JUnit4.class)
public class TestTrip {
  Trip trip;

  // Setup to be done before every test in TestPlan
  @Before
  public void initialize() {
    trip = new Trip();
    trip.title = "Test";

    Place place1 = new Place();
    place1.id = "dnvr";
    place1.name = "Dever";
    place1.latitude = 39.7392;
    place1.longitude = -104.9903;

    Place place2 = new Place();
    place1.id = "bldr";
    place1.name = "Boulder";
    place1.latitude = 40.01499;
    place1.longitude = -105.27055;

    Place place3 = new Place();
    place1.id = "foco";
    place1.name = "Fort Collins";
    place1.latitude = 40.585258;
    place1.longitude = -105.084419;

    ArrayList<Place> places = new ArrayList<>();
    places.add(place1);
    places.add(place2);
    places.add(place3);

    trip.places = places;

    Option option = new Option();
    option.optimization = "none";
    option.units = "miles";

    trip.options = option;

  }

  @Test
  public void testTrue() {
    // assertTrue checks if a statement is true
    assertTrue(true == true);
  }

  @Test
  public void testOptimized(){

    trip.plan();

    Place place1 = new Place();
    place1.id = "dnvr";
    place1.name = "Dever";
    place1.latitude = 39.7392;
    place1.longitude = -104.9903;

    Place place2 = new Place();
    place1.id = "bldr";
    place1.name = "Boulder";
    place1.latitude = 40.01499;
    place1.longitude = -105.27055;

    Place place3 = new Place();
    place1.id = "foco";
    place1.name = "Fort Collins";
    place1.latitude = 40.585258;
    place1.longitude = -105.084419;

    ArrayList<Place> places = new ArrayList<>();
    places.add(place1);
    places.add(place2);
    places.add(place3);

    if (trip.places.size() == places.size()){

      for (int i = 0; i < places.size(); i++){
        assertEquals(trip.places.get(i).name, places.get(i).name);
      }

    } else {
      assertEquals(trip.places.size(), places.size());
    }

  }

  @Test
  public void testDistances() {
    trip.plan();
    ArrayList<Integer> expectedDistances = new ArrayList<Integer>();
    Collections.addAll(expectedDistances, 24, 41, 59);
    // Call the equals() method of the first object on the second object.
    //assertEquals(expectedDistances, trip.distances);
  }

  @Test
  public void testMap() {
    trip.plan();
    //Check to make sure svg file was found and not empty
    assertNotEquals(trip.map, "");
  }
}
