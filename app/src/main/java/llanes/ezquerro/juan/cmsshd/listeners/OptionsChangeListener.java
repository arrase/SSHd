package llanes.ezquerro.juan.cmsshd.listeners;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import llanes.ezquerro.juan.cmsshd.R;

import static llanes.ezquerro.juan.cmsshd.daemon.Utils.startSSHd;
import static llanes.ezquerro.juan.cmsshd.daemon.Utils.stopSSHd;

public class OptionsChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Context mContext;

    public OptionsChangeListener(Context context) {
        mContext = context;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(mContext.getString(R.string.pref_enabled))) {
            if (sharedPreferences.getBoolean(s, false)) {
                startSSHd();
            } else {
                stopSSHd();
            }
            Log.d("SSHd", s);
        } else if (s.equals(mContext.getString(R.string.pref_daemon))) {
            Log.d("SSHd", s);
        } else if (s.equals(mContext.getString(R.string.pref_onion))) {
            Log.d("SSHd", s);
        } else if (s.equals(mContext.getString(R.string.pref_localhost))) {
            Log.d("SSHd", s);
        }
    }
}
