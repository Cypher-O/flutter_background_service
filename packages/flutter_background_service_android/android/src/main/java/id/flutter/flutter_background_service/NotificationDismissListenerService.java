package android.src.main.java.id.flutter.flutter_background_service;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationDismissListenerService extends NotificationListenerService {

    public class DismissListenerServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // Service is connected, you can perform any necessary setup here
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // Service is disconnected, perform cleanup if needed
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        if (sbn.getPackageName().equals(getPackageName())) {
            // The user dismissed a notification from your app
            int dismissedNotificationId = sbn.getId();

            // Notify the Flutter part of your plugin about the dismissal
            if (backgroundServiceInstance != null) {
                backgroundServiceInstance.sendNotificationDismissedEvent(dismissedNotificationId);
            }
        }
    }

}
}
