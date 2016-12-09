package llanes.ezquerro.juan.cmsshd.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import llanes.ezquerro.juan.cmsshd.R;
import llanes.ezquerro.juan.cmsshd.service.ServiceUtils;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            if (prefs.getBoolean(context.getString(R.string.pref_daemon), false)) {
                ServiceUtils.startSSHd(context);
            }
        }
    }
}
