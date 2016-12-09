package llanes.ezquerro.juan.cmsshd.Utils;

import android.content.Context;

import java.io.DataOutputStream;
import java.io.IOException;

public class SetupScript {
    public static void run(Context context) {

        final String scriptPath = new SSHdResources(context).getSetupScriptPath();

        new Thread(new Runnable() {
            public void run() {
                Process p;
                try {
                    p = Runtime.getRuntime().exec("su");

                    DataOutputStream os = new DataOutputStream(p.getOutputStream());

                    os.writeBytes("/bin/sh " + scriptPath + "\n");
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
