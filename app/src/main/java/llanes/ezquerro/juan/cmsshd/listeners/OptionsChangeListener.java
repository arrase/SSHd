package llanes.ezquerro.juan.cmsshd.listeners;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import llanes.ezquerro.juan.cmsshd.R;
import llanes.ezquerro.juan.cmsshd.constants.SSHdConstants;
import llanes.ezquerro.juan.cmsshd.service.SSHdService;
import llanes.ezquerro.juan.cmsshd.service.ServiceUtils;
import llanes.ezquerro.juan.cmsshd.setup.SSHdResources;

public class OptionsChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Context mContext;

    public OptionsChangeListener(Context context) {
        mContext = context;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(mContext.getString(R.string.pref_enabled))) {
            if (sharedPreferences.getBoolean(s, false)) {
                SSHdResources resources = new SSHdResources(mContext);
                Intent intent = new Intent(mContext, SSHdService.class);
                intent.setAction(SSHdConstants.ACTION_START);
                intent.putExtra(SSHdConstants.SSHD_CONFIG, resources.getConfigFilePath());
                mContext.startService(intent);
            } else {
                ServiceUtils.stopSSHd();
            }
        } else if (s.equals(mContext.getString(R.string.pref_daemon))) {
            Log.d("SSHd", s);
        } else if (s.equals(mContext.getString(R.string.pref_localhost))) {
            Log.d("SSHd", s);
        }
    }
}
