package llanes.ezquerro.juan.cmsshd.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.io.DataOutputStream;
import java.io.IOException;

import llanes.ezquerro.juan.cmsshd.R;
import llanes.ezquerro.juan.cmsshd.constants.SSHdConstants;

public class SSHdService extends Service {

    public SSHdService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final int id = startId;

        if (intent != null && intent.getAction().equals(SSHdConstants.ACTION_START)) {

            final String config_path = intent.getStringExtra(SSHdConstants.SSHD_CONFIG);

            final NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotificationManager.notify(id, getNotification());

            new Thread(new Runnable() {
                public void run() {
                    Process p;
                    try {
                        p = Runtime.getRuntime().exec("su");

                        DataOutputStream os = new DataOutputStream(p.getOutputStream());
                        os.writeBytes("/system/bin/sshd -D -q -f " + config_path + "\n");
                        os.writeBytes("exit\n");
                        os.flush();
                        p.waitFor();

                    } catch (IOException | InterruptedException e) {
                        // Silent block
                    }

                    mNotificationManager.cancel(id);
                    stopSelf(id);
                }
            }).start();
        }

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification getNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setOngoing(true)
                        .setContentTitle(getString(R.string.sshd_is_running))
                        .setContentText(getString(R.string.login_info));

        return mBuilder.build();
    }
}
