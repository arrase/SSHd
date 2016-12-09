package llanes.ezquerro.juan.cmsshd.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.DataOutputStream;
import java.io.IOException;

import llanes.ezquerro.juan.cmsshd.constants.SSHdConstants;

public class SSHdService extends Service {

    public SSHdService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final int id = startId;
        String action = intent.getAction();

        if (action.equals(SSHdConstants.ACTION_START)) {
            final String config_path = intent.getStringExtra(SSHdConstants.SSHD_CONFIG);

            new Thread(new Runnable() {
                public void run() {
                    Process p;
                    try {
                        p = Runtime.getRuntime().exec("su");

                        DataOutputStream os = new DataOutputStream(p.getOutputStream());
                        os.writeBytes("/system/bin/sshd -f " + config_path + "\n");
                        os.writeBytes("exit\n");
                        os.flush();
                        p.waitFor();

                    } catch (IOException | InterruptedException e) {
                        // Silent block
                    }

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
}
