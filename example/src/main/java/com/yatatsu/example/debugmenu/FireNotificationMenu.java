package com.yatatsu.example.debugmenu;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.yatatsu.debugmenu.DebugMenuItem;


public class FireNotificationMenu implements DebugMenuItem {
  @Override public void invoke(@NonNull Context context) {
    Notification notification =
        new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_launcher)
            .setContentText("Notification for DEBUG")
            .setDefaults(Notification.DEFAULT_ALL)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_EVENT)
            .build();
    NotificationManager manager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    manager.notify(0, notification);
  }

  @NonNull
  @Override public String getName() {
    return "FIRE NOTIFICATION";
  }
}
