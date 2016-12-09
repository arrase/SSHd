package llanes.ezquerro.juan.cmsshd.service;


import android.content.Context;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import llanes.ezquerro.juan.cmsshd.setup.SSHdResources;

public class ServiceUtils {
    public static void stopSSHd() {

        new Thread(new Runnable() {
            public void run() {
                Process p;
                try {
                    p = Runtime.getRuntime().exec("su");

                    DataOutputStream os = new DataOutputStream(p.getOutputStream());

                    os.writeBytes("/system/bin/kill $(cat /data/ssh/sshd.pid)\n");
                    os.writeBytes("exit\n");
                    os.flush();
                    p.waitFor();

                } catch (IOException | InterruptedException e) {
                    // Silent block
                }
            }
        }).start();
    }

    public static boolean isRunning(Context context) {
        SSHdResources resources = new SSHdResources(context);
        File pid = new File(resources.getPidfilePath());
        return pid.exists();
    }
}
