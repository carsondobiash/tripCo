package com.tripco.t18.planner;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    //Class variables that will become json objects
    public String type;
    public int version;
    public String match;
    public int limit = 0;
    public ArrayList<Place> places = new ArrayList<>();

    //Class variable for database configuration information
    private final static String myDriver = "com.mysql.jdbc.Driver";
    public static String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
    public static String user = "cs314-db";
    public static String pass = "eiK5liet1uej";
    //fill in SQL queries to count the number of records and to retrieve the data
    private static String count = "";
    private static String search = "";

    //Arguments contain the username and password for the database
    public void databaseSearch(){
        remoteDatabase();
        count = Integer.toString(limit);
        search = ("select id,name,latitude,longitude from airports where name like '%" + match + "%'");
        if(limit != 0){
            search += (" LIMIT " + count + ";");
        }
        try {
            Class.forName(myDriver);
            // connect to the database and query
            try (Connection conn = DriverManager.getConnection(myUrl, user, pass);
                 Statement stQuery = conn.createStatement();
                 ResultSet rsQuery = stQuery.executeQuery(search)
            ) {
                dbSearch(rsQuery);
            }
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void dbSearch(ResultSet query) throws SQLException {
        String id;
        String name;
        String lat;
        String lon;
        while(query.next()) {
            Place tempPlace = new Place();
            id = query.getString("id");
            name = query.getString("name");
            lat = query.getString("latitude");
            lon = query.getString("longitude");
            tempPlace.id = id;
            tempPlace.name = name;
            tempPlace.latitude = Double.parseDouble(lat);
            tempPlace.longitude = Double.parseDouble(lon);
            places.add(tempPlace);
        }
    }

    public void remoteDatabase(){
        String isTravis = System.getenv("TRAVIS");
        String isDevelopment = System.getenv("CS314_ENV");
        if(isTravis != null && isTravis.equals("true")){
            myUrl = "jdbc:mysql://127.0.0.1/cs314";
            user = "travis";
            pass = null;
        }
        if(isDevelopment != null && isDevelopment.equals("development")){
            myUrl = "jdbc:mysql://127.0.0.1:56247/cs314";
            user = "cs314-db";
            pass = "eiK5liet1uej";
        }
    }
}
