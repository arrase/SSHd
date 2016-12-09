package llanes.ezquerro.juan.cmsshd.service;


import android.content.Context;
import android.content.Intent;

import java.io.DataOutputStream;
import java.io.IOException;

import llanes.ezquerro.juan.cmsshd.constants.SSHdConstants;
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

    public static void startSSHd(Context context) {
        SSHdResources resources = new SSHdResources(context);
        Intent intent = new Intent(context, SSHdService.class);
        intent.setAction(SSHdConstants.ACTION_START);
        intent.putExtra(SSHdConstants.SSHD_CONFIG, resources.getConfigFilePath());
        context.startService(intent);
    }
}
