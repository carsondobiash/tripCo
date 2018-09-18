package com.tripco.t18.planner;

import java.util.Arrays;
import java.util.List;

/**
 * Describes the options to apply when planning a trip in TFFI format.
 * At this point we are only using the values provided.
 */
public class Option {

  public String distance;
  public String optimization;
  public List<String> units = Arrays.asList("miles","nautical miles","kilometers", "user defined" );
  public String unitName;
  public int unitRadius;

}
