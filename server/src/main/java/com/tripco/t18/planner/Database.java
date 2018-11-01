package com.tripco.t18.planner;

import java.sql.*;
import java.util.*;

public class Database {
    //Class variables that will become json objects
    public Integer version;
    public String type;
    public ArrayList<Map<String, String>> filters;
    public String match;
    public Integer limit;
    public Integer found;
    public ArrayList<Place> places = new ArrayList<>();

    //Class variable for database configuration information
    private final static String myDriver = "com.mysql.jdbc.Driver";
    private static String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
    private static String user = "cs314-db";
    private static String pass = "eiK5liet1uej";
    //fill in SQL queries to count the number of records and to retrieve the data
    private static String count = "";
    private static String search = "";
    private static String getCount = "";

    //Arguments contain the username and password for the database
    public void databaseSearch(){
        remoteDatabase();
        count = Integer.toString(limit);
        search = ("select id,name,latitude,longitude from airports where name like '%" + match + "%' or municipality like '%" + match + "%' or id like '%" + match +"%' order by name");
        getCount = ("select count(*) from world_airports where name like '%" + match + "%' or municipality like '%" + match + "%' or id like '%" + match +"%' order by name");
        if(limit == null){
            limit = 0;
        }
        if(limit != 0){
            search += (" LIMIT " + count + ";");
        }
        try {
            Class.forName(myDriver);
            // connect to the database and query
            try (Connection conn = DriverManager.getConnection(myUrl, user, pass);
                 Statement stQuery = conn.createStatement();
                 Statement stCount = conn.createStatement();
                 ResultSet rsQuery = stQuery.executeQuery(search);
                 ResultSet rsCount = stCount.executeQuery(getCount);
            ) {
                dbSearch(rsQuery);
                dbCount(rsCount);
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

    public void dbCount(ResultSet query) throws SQLException{
        String num;
        while(query.next()){
            num = query.getString("count(*)");
            found = Integer.parseInt(num);
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
