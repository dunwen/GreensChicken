package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.ssist.pool.VariablePool;

public class RunTime {
  public static void defineValue(String name, Object value) {
    VariablePool.put(name, value);
  }
}
