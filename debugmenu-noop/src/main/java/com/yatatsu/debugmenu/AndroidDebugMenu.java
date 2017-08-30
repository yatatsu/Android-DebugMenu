package com.yatatsu.debugmenu;

import android.content.Context;

/**
 * no-op
 */
public class AndroidDebugMenu {

  public static void initialize(AndroidDebugMenu.Configuration configuration) {
  }

  private AndroidDebugMenu(AndroidDebugMenu.Configuration configuration) {
  }

  public final static class Configuration {

    private Configuration(AndroidDebugMenu.ConfigurationBuilder builder) {

    }

  }

  public final static class ConfigurationBuilder {

    public ConfigurationBuilder(Context context) {
    }

    public AndroidDebugMenu.ConfigurationBuilder addDebugMenuItem(DebugMenuItem debugMenuItem) {
      return this;
    }

    public AndroidDebugMenu.ConfigurationBuilder notificationId(int notificationId) {
      return this;
    }

    public AndroidDebugMenu.ConfigurationBuilder title(String title) {
      return this;
    }

    public AndroidDebugMenu.ConfigurationBuilder description(String description) {
      return this;
    }

    public AndroidDebugMenu.Configuration build() {
      return new AndroidDebugMenu.Configuration(this);
    }
  }
}
