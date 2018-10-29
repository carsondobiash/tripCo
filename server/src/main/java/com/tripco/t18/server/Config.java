package com.tripco.t18.server;

import com.google.gson.Gson;

import java.util.*;

public class Config {

  private short version = 3;
  private String type = "config";
  private List<String> units = Arrays.asList("miles","nautical miles","kilometers", "user defined");
  private List optimizations;

  public Config(){
      Map<String, String> none = new HashMap<>();
      none.put("label", "none");
      none.put("description", "The trip is not optimized.");
      Map<String, String> shorty = new HashMap<>();
      shorty.put("label", "short");
      shorty.put("description", "Nearest neighbor.");
      Map<String, String> shorter = new HashMap<>();
      shorter.put("label", "shorter");
      shorter.put("description", "2-opt.");

      optimizations.add(none);
      optimizations.add(shorty);
      optimizations.add(shorter);

  }

  static String getConfig() {
    Config conf = new Config();
    Gson gson = new Gson();

    return gson.toJson(conf);
  }
}
