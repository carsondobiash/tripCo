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
      Filters support = new Filters();
      support.values = new ArrayList<>();
      support.name = "type";
      support.values.add("balloonport");
      support.values.add("heliport");
      support.values.add("airport");
      support.values.add("seaplane base");
      filters.add(support);

  }

  static String getConfig() {
    Config conf = new Config();
    Gson gson = new Gson();

    return gson.toJson(conf);
  }
}
