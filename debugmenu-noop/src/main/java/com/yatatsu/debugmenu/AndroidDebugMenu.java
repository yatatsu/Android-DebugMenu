package com.yatatsu.debugmenu;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * no-op
 */
public class AndroidDebugMenu {

  public static void initialize(AndroidDebugMenu.Configuration configuration) {
  }

  private AndroidDebugMenu(@NonNull AndroidDebugMenu.Configuration configuration) {
  }

  public final static class Configuration {

    private Configuration(@NonNull AndroidDebugMenu.ConfigurationBuilder builder) {

    }

  }

  public final static class ConfigurationBuilder {

    public ConfigurationBuilder(@NonNull Context context) {
    }

    @NonNull
    public AndroidDebugMenu.ConfigurationBuilder addDebugMenuItem(@NonNull DebugMenuItem debugMenuItem) {
      return this;
    }

    @NonNull
    public AndroidDebugMenu.ConfigurationBuilder notificationId(int notificationId) {
      return this;
    }

    @NonNull
    public AndroidDebugMenu.ConfigurationBuilder title(@Nullable String title) {
      return this;
    }

    @NonNull
    public AndroidDebugMenu.ConfigurationBuilder description(@Nullable String description) {
      return this;
    }

    @NonNull
    public AndroidDebugMenu.Configuration build() {
      return new AndroidDebugMenu.Configuration(this);
    }
  }
}
