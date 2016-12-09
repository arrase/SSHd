package llanes.ezquerro.juan.cmsshd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import llanes.ezquerro.juan.cmsshd.Utils.SSHdResources;
import llanes.ezquerro.juan.cmsshd.Utils.SetupScript;
import llanes.ezquerro.juan.cmsshd.listeners.OptionsChangeListener;

public class SSHdActivity extends PreferenceActivity {
    private SharedPreferences prefs;
    private OptionsChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.sshd_setup);

        SSHdResources resources = new SSHdResources(this);
        if (!resources.areInstalled()) {
            resources.installSSHdFiles();
            SetupScript.run(resources.getSetupScriptPath());
        }

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        listener = new OptionsChangeListener(this);
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
