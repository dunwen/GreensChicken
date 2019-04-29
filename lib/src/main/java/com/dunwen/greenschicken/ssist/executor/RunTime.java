package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.ssist.poor.VariablePoor;

public class RunTime {
  public static void defineValue(String name, Object value) {
    VariablePoor.put(name, value);
  }
}
