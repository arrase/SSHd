package llanes.ezquerro.juan.cmsshd.listeners;

import android.content.Context;
import android.content.SharedPreferences;

import llanes.ezquerro.juan.cmsshd.R;
import llanes.ezquerro.juan.cmsshd.service.ServiceUtils;

public class OptionsChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Context mContext;

    public OptionsChangeListener(Context context) {
        mContext = context;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(mContext.getString(R.string.pref_enabled))) {
            if (sharedPreferences.getBoolean(s, false)) {
                ServiceUtils.startSSHd(mContext);
            } else {
                ServiceUtils.stopSSHd();
            }
        }
    }
}
