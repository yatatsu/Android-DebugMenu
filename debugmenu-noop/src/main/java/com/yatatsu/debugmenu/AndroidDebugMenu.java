package com.yatatsu.debugmenu;

import android.content.Context;

/**
 * no-op
 */
public class AndroidDebugMenu {

  public static void initialize(Builder builder) {
  }

  private AndroidDebugMenu(Builder builder) {
  }

  public static class Builder {

    public Builder(Context context) {
    }

    public Builder addDevMenuItem(DebugMenuItem debugMenuItem) {
      return this;
    }

    public Builder notificationId(int notificationId) {
      return this;
    }

    public AndroidDebugMenu build() {
      return new AndroidDebugMenu(this);
    }
  }
}
