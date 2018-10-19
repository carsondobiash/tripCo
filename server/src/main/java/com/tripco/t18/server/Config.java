package com.tripco.t18.server;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Config {

  private short version = 3;
  private String type = "config";
  private List<String> units = Arrays.asList("miles","nautical miles","kilometers", "user defined");
  private List<String> optimization = Arrays.asList("none", "short", "shorter");

  static String getConfig() {
    Config conf = new Config();
    Gson gson = new Gson();

    return gson.toJson(conf);
  }
}
