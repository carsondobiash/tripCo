package com.tripco.t18.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TestMap {

    Place places[];
    Map map;

    @Before
    public void initialize(){



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

        places  = new Place[3];
        places[0] = place1;
        places[1] = place2;
        places[2] = place3;
        map = new Map(places);
    }

    @Test
    public void testkml(){
        String mapString;
        mapString = map.kml();
        assertNotEquals(mapString, "");
    }

    @Test
    public void testsvg(){
        String mapString;
        mapString = map.svg();
        assertNotEquals(mapString, "");
    }

    @Test
    public void textpixelLong(){
        double num = 800.0;
        double pixel = map.pixelLongitude(180.0);
        assertEquals(pixel,num, 0.01);
    }

    @Test
    public void textpixelLat(){
        double num = 0.0;
        double pixel = map.pixelLatitude(90.0);
        assertEquals(pixel,num, 0.01);
    }

    @Test
    public void testWrap(){
        boolean wrap = map.checkForWrap(-180, 180);
        assertTrue(wrap);
    }
}
