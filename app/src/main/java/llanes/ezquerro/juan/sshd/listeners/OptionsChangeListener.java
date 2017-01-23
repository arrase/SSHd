package llanes.ezquerro.juan.sshd.listeners;

import android.content.Context;
import android.content.SharedPreferences;

import llanes.ezquerro.juan.sshd.R;
import llanes.ezquerro.juan.sshd.permissions.PermissionManager;
import llanes.ezquerro.juan.sshd.service.ServiceUtils;
import llanes.ezquerro.juan.sshd.setup.SSHdResources;
import llanes.ezquerro.juan.sshd.setup.SetupScript;

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
        } else if (s.equals(mContext.getString(R.string.pref_key))) {
            String pubKey = sharedPreferences.getString(s, null);
            if (pubKey != null && pubKey.length() > 0) {
                SSHdResources resources = new SSHdResources(mContext);
                if (!resources.areInstalled()) {
                    resources.installSSHdFiles();
                    SetupScript.run(resources.getSetupScriptPath());

                    if (resources.saveAuthorizedKey(pubKey)) {
                        ServiceUtils.setupAuthKey(resources.getAuthKeysPath());
                    }
                }
            }
        } else if (s.equals(mContext.getString(R.string.pref_battery_optimizations))) {
            if (sharedPreferences.getBoolean(s, false)) {
                PermissionManager.requestDropBatteryPermmssions(mContext);
            } else {
                PermissionManager.requestBatteryPermmssions(mContext);
            }
        }
    }
}
