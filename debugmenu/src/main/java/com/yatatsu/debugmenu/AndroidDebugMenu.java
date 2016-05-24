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
  private final int notificationId;

  static volatile AndroidDebugMenu instance;

  public static void initialize(Builder builder) {
    if (instance == null) {
      synchronized (AndroidDebugMenu.class) {
        if (instance == null) {
          instance = builder.build();
          instance.startDebugMenu();
        }
      }
    }
  }

  static AndroidDebugMenu getInstance() {
    return instance;
  }

  private AndroidDebugMenu(Builder builder) {
    this.context = builder.context;
    this.debugMenuItems = builder.debugMenuItems;
    this.notificationId = builder.notificationId;
  }

  List<DebugMenuItem> getDebugMenuItems() {
    return debugMenuItems;
  }

  private void startDebugMenu() {
    String appName = context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    NotificationCompat.Builder notificationBuilder =
        new NotificationCompat.Builder(context).setContentTitle("AndroidDebugMenu")
            .setSmallIcon(R.drawable.ic_settings_black_24dp)
            .setContentText("Click to see debug menu for " + appName)
            .setAutoCancel(false);

    Intent resultIntent = new Intent(context, MenuActivity.class);
    PendingIntent resultPendingIntent =
        PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    notificationBuilder.setContentIntent(resultPendingIntent);
    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(notificationId, notificationBuilder.build());
  }

  public static class Builder {

    private final Context context;
    private List<DebugMenuItem> debugMenuItems;
    private int notificationId;

    public Builder(Context context) {
      this.context = context;
      this.debugMenuItems = new ArrayList<>();
    }

    public Builder addDevMenuItem(DebugMenuItem debugMenuItem) {
      debugMenuItems.add(debugMenuItem);
      return this;
    }

    public Builder notificationId(int notificationId) {
      this.notificationId = notificationId;
      return this;
    }

    public AndroidDebugMenu build() {
      return new AndroidDebugMenu(this);
    }
  }
}
