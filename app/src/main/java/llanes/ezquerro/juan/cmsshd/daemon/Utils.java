package llanes.ezquerro.juan.cmsshd.daemon;


import java.io.DataOutputStream;
import java.io.IOException;

public class Utils {
    public static void startSSHd() {

        new Thread(new Runnable() {
            public void run() {
                Process p;
                try {
                    p = Runtime.getRuntime().exec("su");

                    DataOutputStream os = new DataOutputStream(p.getOutputStream());

                    os.writeBytes("/system/bin/sshd -f /data/ssh/sshd_config -D\n");
                    os.writeBytes("exit\n");
                    os.flush();
                    p.waitFor();

                } catch (IOException | InterruptedException e) {
                    // Silent block
                }

            }
        }).start();
    }

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
}
