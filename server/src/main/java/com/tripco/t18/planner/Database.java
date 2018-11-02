package com.tripco.t18.planner;

import java.sql.*;
import java.util.*;

public class Database {
    //Class variables that will become json objects
    public Integer version;
    public String type;
    public String match;
    public ArrayList<Filters> filters = new ArrayList<>();
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
        Integer lim;
        if(limit == null){
            lim = 0;
        }
        else{
            lim = limit;
        }
        count = Integer.toString(lim);
        search = ("select world_airports.id,world_airports.name,world_airports.latitude,world_airports.longitude,world_airports.type from world_airports inner join region on world_airports.iso_region = region.id " +
                "inner join country on world_airports.iso_country = country.id inner join continents on world_airports.continent = continents.id where country.name like '%" + match + "%' " + filterDatabase() +
                " or region.name like '%" + match + "%' " + filterDatabase() + " or world_airports.name like '%" + match + "%' " + filterDatabase() + " or world_airports.municipality like '%" + match + "%' " +
                filterDatabase() + " order by continents.name, country.name, region.name, world_airports.municipality, world_airports.name ASC");
        getCount = ("select count(*) from world_airports inner join region on world_airports.iso_region = region.id " +
                "inner join country on world_airports.iso_country = country.id inner join continents on world_airports.continent = continents.id where country.name like '%" + match + "%' " + filterDatabase() +
                " or region.name like '%" + match + "%' " + filterDatabase() + " or world_airports.name like '%" + match + "%' " + filterDatabase() + " or world_airports.municipality like '%" + match + "%' " +
                filterDatabase() + " order by continents.name, country.name, region.name, world_airports.municipality, world_airports.name ASC");
        String isTravis = System.getenv("TRAVIS");
        if(isTravis != null && isTravis.equals("true")){
            search = ("select id,name,latitude,longitude from airports where name like '%" + match + "%' or municipality like '%" + match + "%' or id like '%" + match +"%' order by name");
            getCount = ("select count(*) from airports where name like '%" + match + "%' or municipality like '%" + match + "%' or id like '%" + match +"%' order by name");
        }
        if(lim != 0){
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
            name.replaceAll("\"", "");
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

    private String filterDatabase(){
        String filter = "";
        if(!filters.isEmpty()){
            for(int i = 0; i < filters.size(); i++){
                for(int j = 0; j < filters.get(i).values.size(); j++){
                    filter += "and " + filters.get(i).name + " like '%" + filters.get(i).values.get(j) + "%' ";
                }
            }
        }
        return filter;
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
