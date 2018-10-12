package com.tripco.t18.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t18.server.HTTP;
import spark.Request;

public class Search {

    private Database database;

    public Search(Request request){
        // first print the request
        System.out.println(HTTP.echoRequest(request));

        // extract the information from the body of the request.
        JsonParser jsonParser = new JsonParser();
        JsonElement requestBody = jsonParser.parse(request.body());

        // convert the body of the request to a Java class.
        Gson gson = new Gson();
        database = gson.fromJson(requestBody, Database.class);

        //Calling the database
        database.databaseSearch();

        // log something.
        System.out.println();
    }

    public String getSearch () {
        Gson gson = new Gson();
        return gson.toJson(database);
    }
}
