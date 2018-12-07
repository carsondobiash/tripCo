package com.tripco.t18.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t18.server.HTTP;
import spark.Request;


/**
 * This class handles to the conversions of the Distance request/resopnse,
 * converting from the Json string in the request body to a Distance object,
 * caluculateing the Distance, and
 * converting the resulting Distance object back to a Json string
 * so it may returned as the response.
 */
public class Calculate {

    private Distance distance;

    /** Handles distance calculating request, creating a new distance object from the JSON request.
     * Does the conversion from Json to a Java class before calculating the distance.
     * @param request
     */
    public Calculate (Request request) {
        // first print the request

        // extract the information from the body of the request.
        JsonParser jsonParser = new JsonParser();
        JsonElement requestBody = jsonParser.parse(request.body());

        // convert the body of the request to a Java class.
        Gson gson = new Gson();
        distance = gson.fromJson(requestBody, Distance.class);

        // plan the trip.
        int dist = distance.calculatedistance();
    }

    /** Handles the response for a Trip object.
     * Does the conversion from a Java class to a Json string.*
     */
    public String getDistance () {
        Gson gson = new Gson();
        return gson.toJson(distance);
    }
}