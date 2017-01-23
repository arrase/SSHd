package llanes.ezquerro.juan.sshd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;

import llanes.ezquerro.juan.sshd.delegate.AppCompatPreferenceActivity;
import llanes.ezquerro.juan.sshd.listeners.OptionsChangeListener;
import llanes.ezquerro.juan.sshd.permissions.PermissionManager;

public class SSHdActivity extends AppCompatPreferenceActivity {
    private SharedPreferences prefs;
    private OptionsChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.sshd_setup);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        listener = new OptionsChangeListener(this);
        prefs.registerOnSharedPreferenceChangeListener(listener);

        if (!PermissionManager.isLollipopOrHigher()) {
            Preference doze = findPreference(getString(R.string.pref_battery_optimizations));
            doze.setSummary(getString(R.string.only_for_lollipop_or_higher));
            doze.setEnabled(false);
        }
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
