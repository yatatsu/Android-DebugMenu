package com.yatatsu.debugmenu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;

public final class AndroidDebugMenu {

  private static volatile AndroidDebugMenu instance;
  private final AndroidDebugMenu.Configuration configuration;
  private static final String DEFAULT_CHANNEL_NAME = "DebugMenu";

  public static void initialize(AndroidDebugMenu.Configuration configuration) {
    if (instance == null) {
      synchronized (AndroidDebugMenu.class) {
        if (instance == null) {
          instance = new AndroidDebugMenu(configuration);
          instance.startDebugMenu();
        }
      }
    }
  }

  static AndroidDebugMenu getInstance() {
    return instance;
  }

  private AndroidDebugMenu(AndroidDebugMenu.Configuration configuration) {
    this.configuration = configuration;
  }

  List<DebugMenuItem> getDebugMenuItems() {
    return configuration.debugMenuItems;
  }

  private void startDebugMenu() {
    Context context = configuration.context;
    String appName = context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    // create channel (for Android O+ target)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(DEFAULT_CHANNEL_NAME,
          "Debug Menu", NotificationManager.IMPORTANCE_MIN);
      channel.setShowBadge(false);
      channel.enableLights(false);
      channel.enableVibration(false);
      notificationManager.createNotificationChannel(channel);
    }

    NotificationCompat.Builder notificationBuilder =
        new NotificationCompat.Builder(context, DEFAULT_CHANNEL_NAME)
            .setContentTitle("AndroidDebugMenu")
            .setSmallIcon(R.drawable.ic_settings_black_24dp)
            .setContentText("Click to see debug menu for " + appName)
            .setAutoCancel(false);

    Intent resultIntent = new Intent(context, MenuActivity.class);
    PendingIntent resultPendingIntent =
        PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    notificationBuilder.setContentIntent(resultPendingIntent);
    notificationManager.notify(configuration.notificationId, notificationBuilder.build());
  }

  public final static class Configuration {
    private final Context context;
    private final List<DebugMenuItem> debugMenuItems;
    private final int notificationId;

    private Configuration(AndroidDebugMenu.ConfigurationBuilder builder) {
      this.context = builder.context;
      this.debugMenuItems = builder.debugMenuItems;
      this.notificationId = builder.notificationId;
    }
  }

  public final static class ConfigurationBuilder {

    private final Context context;
    private List<DebugMenuItem> debugMenuItems;
    private int notificationId;

    public ConfigurationBuilder(Context context) {
      this.context = context;
      this.debugMenuItems = new ArrayList<>();
    }

    public AndroidDebugMenu.ConfigurationBuilder addDebugMenuItem(DebugMenuItem debugMenuItem) {
      debugMenuItems.add(debugMenuItem);
      return this;
    }

    public AndroidDebugMenu.ConfigurationBuilder notificationId(int notificationId) {
      this.notificationId = notificationId;
      return this;
    }

    public AndroidDebugMenu.Configuration build() {
      return new AndroidDebugMenu.Configuration(this);
    }
  }
}
