package llanes.ezquerro.juan.sshd.setup;

import java.io.DataOutputStream;
import java.io.IOException;

public class SetupScript {
    public static void run(String path) {

        final String scriptPath = path;

        new Thread(new Runnable() {
            public void run() {
                Process p;
                try {
                    p = Runtime.getRuntime().exec("su");

                    DataOutputStream os = new DataOutputStream(p.getOutputStream());

                    os.writeBytes("/system/bin/sh " + scriptPath + "\n");
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
