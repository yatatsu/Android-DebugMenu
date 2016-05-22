package com.yatatsu.debugmenu;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;

public class AndroidDebugMenu {

  private final Context context;
  private final List<DebugMenuItem> debugMenuItems;

  static volatile AndroidDebugMenu instance;

  public static AndroidDebugMenu initialize(Builder builder) {
    if (instance == null) {
      synchronized (AndroidDebugMenu.class) {
        if (instance == null) {
          instance = builder.build();
          instance.startDebugMenu();
        }
      }
    }
    return instance;
  }

  static AndroidDebugMenu getInstance() {
    return instance;
  }

  private AndroidDebugMenu(Builder builder) {
    this.context = builder.context;
    this.debugMenuItems = builder.debugMenuItems;
  }

  List<DebugMenuItem> getDebugMenuItems() {
    return debugMenuItems;
  }

  private void startDebugMenu() {
    NotificationCompat.Builder notificationBuilder =
        new NotificationCompat.Builder(context).setContentTitle("AndroidDebugMenu")
            .setSmallIcon(android.R.drawable.ic_menu_call)
            .setContentText("Click to see current metrics")
            .setAutoCancel(false);

    Intent resultIntent = new Intent(context, MenuActivity.class);
    PendingIntent resultPendingIntent =
        PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    notificationBuilder.setContentIntent(resultPendingIntent);
    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(hashCode(), notificationBuilder.build());
  }

  public static class Builder {

    private final Context context;
    private List<DebugMenuItem> debugMenuItems;

    public Builder(Context context) {
      this.context = context;
      this.debugMenuItems = new ArrayList<>();
    }

    public Builder addDevMenuItem(DebugMenuItem debugMenuItem) {
      debugMenuItems.add(debugMenuItem);
      return this;
    }

    public AndroidDebugMenu build() {
      return new AndroidDebugMenu(this);
    }
  }
}
