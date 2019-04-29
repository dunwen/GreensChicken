package com.dunwen.greenschicken;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;

public class LibContext {
  private WeakReference<Activity> currentActivity;
  private static LibContext instance = new LibContext();

  public static LibContext getInstance() {
    return instance;
  }

  public Context getCurrentActivity() {
    return currentActivity == null ? null : currentActivity.get();
  }

  void setCurrentActivity(Activity activity) {
    currentActivity = new WeakReference<>(activity);
  }
}
