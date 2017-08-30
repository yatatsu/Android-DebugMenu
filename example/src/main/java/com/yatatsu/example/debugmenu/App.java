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
        new AndroidDebugMenu.ConfigurationBuilder(this)
            // Add menu
            .addDebugMenuItem(new DebugMenuItem() {
              @Override public void invoke(Context context) {
                Log.d("AndroidDebugMenu", "debug");
              }

              @Override public String getName() {
                return "JUST LOGGING";
              }
            })
            // Add menu
            .addDebugMenuItem(new FireNotificationMenu())
            // Specify notification id for avoiding collision
            .notificationId(99)
            // Set title (optional)
            .title("debug menu for great app!")
            // Set message (optional)
            .description("click and open for debug!!")
            .build());
  }
}
