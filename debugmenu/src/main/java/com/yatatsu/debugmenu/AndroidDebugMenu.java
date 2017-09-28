package com.yatatsu.debugmenu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;

public final class AndroidDebugMenu {

  private static volatile AndroidDebugMenu instance;
  private final AndroidDebugMenu.Configuration configuration;
  private static final String DEFAULT_CHANNEL_NAME = "DebugMenu";

  public static void initialize(@NonNull AndroidDebugMenu.Configuration configuration) {
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
    ApplicationInfo applicationInfo = context.getApplicationInfo();
    String appName = applicationInfo.loadLabel(context.getPackageManager()).toString();
    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    // create channel (for Android O+ target)
    final NotificationCompat.Builder notificationBuilder;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        && applicationInfo.targetSdkVersion >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(DEFAULT_CHANNEL_NAME, "Debug Menu",
          NotificationManager.IMPORTANCE_MIN);
      channel.setShowBadge(false);
      channel.enableLights(false);
      channel.enableVibration(false);
      notificationManager.createNotificationChannel(channel);
      // constructor with channel is added in support library r26
      notificationBuilder = new NotificationCompat.Builder(context, DEFAULT_CHANNEL_NAME);
    } else {
      notificationBuilder = new NotificationCompat.Builder(context);
    }

    notificationBuilder
        .setContentTitle(configuration.title == null ? "AndroidDebugMenu" : configuration.title)
        .setSmallIcon(R.drawable.ic_settings_black_24dp)
        .setContentText(configuration.description == null
            ? "Click to see debug menu for " + appName : configuration.description)
        .setAutoCancel(false);

    Intent resultIntent = new Intent(context, MenuActivity.class);
    PendingIntent resultPendingIntent =
        PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    notificationBuilder.setContentIntent(resultPendingIntent);
    notificationManager.notify(configuration.notificationId, notificationBuilder.build());
  }

  public final static class Configuration {
    @NonNull private final Context context;
    @NonNull private final List<DebugMenuItem> debugMenuItems;
    private final int notificationId;
    @Nullable private final String title;
    @Nullable private final String description;

    private Configuration(@NonNull AndroidDebugMenu.ConfigurationBuilder builder) {
      this.context = builder.context;
      this.debugMenuItems = builder.debugMenuItems;
      this.notificationId = builder.notificationId;
      this.title = builder.title;
      this.description = builder.description;
    }
  }

  public final static class ConfigurationBuilder {

    @NonNull private final Context context;
    @NonNull private List<DebugMenuItem> debugMenuItems;
    private int notificationId;
    @Nullable private String title;
    @Nullable private String description;

    public ConfigurationBuilder(@NonNull Context context) {
      this.context = context;
      this.debugMenuItems = new ArrayList<>();
    }

    /**
     * Add {@link DebugMenuItem}
     *
     * @param debugMenuItem
     * @return
     */
    public AndroidDebugMenu.ConfigurationBuilder addDebugMenuItem(
        @NonNull DebugMenuItem debugMenuItem) {
      debugMenuItems.add(debugMenuItem);
      return this;
    }

    /**
     * Set notificationId
     *
     * @param notificationId id
     * @return builder instance
     */
    public AndroidDebugMenu.ConfigurationBuilder notificationId(int notificationId) {
      this.notificationId = notificationId;
      return this;
    }

    /**
     * Set title for notification
     *
     * @param title title
     * @return builder instance
     */
    public AndroidDebugMenu.ConfigurationBuilder title(@Nullable String title) {
      this.title = title;
      return this;
    }

    /**
     * Set description for notification
     * @param description description
     * @return builder instance
     */
    public AndroidDebugMenu.ConfigurationBuilder description(@Nullable String description) {
      this.description = description;
      return this;
    }

    public AndroidDebugMenu.Configuration build() {
      return new AndroidDebugMenu.Configuration(this);
    }
  }
}
