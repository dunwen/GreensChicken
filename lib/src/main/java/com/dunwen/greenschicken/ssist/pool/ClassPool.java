package com.dunwen.greenschicken.ssist.pool;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.dunwen.greenschicken.Utils;

public class ClassPool {
  private static final String[][] commonPackages = {
      { "java", "lang", "reflect" },
      { "java", "io" },
      { "java", "math" },
      { "java", "net" },
      { "java", "util", "zip" },
      { "java", "text", "resources" },
      { "java", "applet" },
      { "javax", "swing" },
      { "android", "content" },
      { "android", "widget" },
      { "com", "dunwen", "greenschicken", "sample" }
  };

  @Nullable public static Class find(String name) {
    Class result = Utils.classOrNull(name);
    if (result != null) return result;
    for (int i = 0; i < commonPackages.length; i++) {
      String[] inner = commonPackages[i];
      for (int j = 0; j < inner.length; j++) {
        String[] arr = new String[j + 1];
        System.arraycopy(inner, 0, arr, 0, j + 1);
        result = Utils.classOrNull(String.format("%s.%s", TextUtils.join(".", arr), name));
        if (result != null) {
          return result;
        }
      }
    }
    return null;
  }
}
