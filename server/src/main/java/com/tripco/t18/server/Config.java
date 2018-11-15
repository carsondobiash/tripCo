package com.tripco.t18.server;

import com.google.gson.Gson;
import com.tripco.t18.planner.Filters;

import java.util.*;

public class Config {

  private short version = 4;
  private String type = "config";
  private List<String> units = Arrays.asList("miles","nautical miles","kilometers", "user defined");
  private ArrayList<Map<String, String>> optimization;
  private List<String> attributes = Arrays.asList("name", "id", "latitude", "longitude");
  private ArrayList<Filters> filters;
  private List<String> maps = Arrays.asList("svg", "kml");

  public Config(){

      optimization = new ArrayList<>();
      Map<String, String> none = new HashMap<>();
      none.put("label", "none");
      none.put("description", "The trip is not optimized.");
      Map<String, String> shorty = new HashMap<>();
      shorty.put("label", "short");
      shorty.put("description", "Nearest neighbor.");
      Map<String, String> shorter = new HashMap<>();
      shorter.put("label", "shorter");
      shorter.put("description", "2-opt.");

      optimization.add(none);
      optimization.add(shorty);
      optimization.add(shorter);

      filters = new ArrayList<>();
      Filters supportType = new Filters();
      supportType.values = new ArrayList<>();
      supportType.name = "type";
      supportType.values.add("balloonport");
      supportType.values.add("heliport");
      supportType.values.add("seaplane base");
      supportType.values.add("small_airports");
      supportType.values.add("medium_airports");
      supportType.values.add("large_airports");
      filters.add(supportType);

      Filters supportContinent = new Filters();
      supportContinent.values = new ArrayList<>();
      supportContinent.name = "continent";
      supportContinent.values.add("AF");
      supportContinent.values.add("AN");
      supportContinent.values.add("AS");
      supportContinent.values.add("EU");
      supportContinent.values.add("NA");
      supportContinent.values.add("OC");
      supportContinent.values.add("SA");
      filters.add(supportContinent);

      Filters supportContinent = new Filters();
      supportContinent.values = new ArrayList<>();
      supportContinent.name = "continent";
      supportContinent.values.add("Africa");
      supportContinent.values.add("Antarctica");
      supportContinent.values.add("Asia");
      supportContinent.values.add("Europe");
      supportContinent.values.add("North America");
      supportContinent.values.add("Oceania");
      supportContinent.values.add("South America");
      filters.add(supportContinent);

  }

  static String getConfig() {
    Config conf = new Config();
    Gson gson = new Gson();

    return gson.toJson(conf);
  }
}
