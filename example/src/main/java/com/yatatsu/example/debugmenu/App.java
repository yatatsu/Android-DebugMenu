package com.yatatsu.example.debugmenu;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.yatatsu.debugmenu.AndroidDebugMenu;
import com.yatatsu.debugmenu.DebugMenuItem;

public class App extends Application {

  @Override public void onCreate() {
    super.onCreate();

    AndroidDebugMenu.initialize(
        new AndroidDebugMenu.Builder(this)
            // Add menu
            .addDevMenuItem(new DebugMenuItem() {
              @Override public void invoke(Context context) {
                Log.d("AndroidDebugMenu", "debug");
              }

              @Override public String getName() {
                return "JUST LOGGING";
              }
            })
            // Add menu
            .addDevMenuItem(new FireNotificationMenu())
            // Specify notification id for avoiding collision
            .notificationId(99));
  }
}
