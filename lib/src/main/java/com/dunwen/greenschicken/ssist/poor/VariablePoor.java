package com.dunwen.greenschicken.ssist.poor;

import android.app.Activity;
import com.dunwen.greenschicken.LibContext;
import java.lang.reflect.Field;
import java.util.HashMap;

public class VariablePoor {
  private static VariablePoor instance;
  private HashMap<String, Object> poor = new HashMap<>();

  static {
    instance = new VariablePoor();
  }

  public static void put(String name, Object value) {
    instance.poor.put(name, value);
  }

  public static Object get(String name) {
    Object o = instance.poor.get(name);
    if (o == null) {
      o = getObjectFromThis(name);
    }
    return o;
  }

  private static Object getObjectFromThis(String name) {
    Activity activity = (Activity) LibContext.getInstance().getCurrentActivity();
    try {
      Field field = activity.getClass().getDeclaredField(name);
      field.setAccessible(true);
      return field.get(activity);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }
}
