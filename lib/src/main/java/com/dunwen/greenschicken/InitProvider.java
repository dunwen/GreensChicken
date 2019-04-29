package com.dunwen.greenschicken;

import android.app.Activity;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.dunwen.greenschicken.socket.MainSocketServer;

public class InitProvider extends ContentProvider {

  @Override public boolean onCreate() {
    MainSocketServer.start();
    ((Application) getContext().getApplicationContext()).registerActivityLifecycleCallbacks(
        new Application.ActivityLifecycleCallbacks() {
          @Override
          public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            LibContext.getInstance().setCurrentActivity(activity);
          }

          @Override public void onActivityStarted(Activity activity) {

          }

          @Override public void onActivityResumed(Activity activity) {

          }

          @Override public void onActivityPaused(Activity activity) {

          }

          @Override public void onActivityStopped(Activity activity) {

          }

          @Override
          public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

          }

          @Override public void onActivityDestroyed(Activity activity) {

          }
        });
    return false;
  }

  @Override public Cursor query(Uri uri,
      String[] projection,
      String selection,
      String[] selectionArgs,
      String sortOrder) {
    return null;
  }

  @Override
  public String getType(Uri uri) {
    return null;
  }

  @Override public Uri insert(Uri uri,
      ContentValues values) {
    return null;
  }

  @Override public int delete(Uri uri,
      String selection,
      String[] selectionArgs) {
    return 0;
  }

  @Override public int update(Uri uri,
      ContentValues values,
      String selection,
      String[] selectionArgs) {
    return 0;
  }
}
