package llanes.ezquerro.juan.cmsshd.permissions;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

public class PermissionManager {

    public static boolean isLollipopOrHigher() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestBatteryPermmssions(Context context) {
        final Context mContext = context;
        final String packageName = mContext.getPackageName();
        PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);

        if (pm.isIgnoringBatteryOptimizations(packageName))
            return;

        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        intent.setData(Uri.parse("package:" + packageName));
        mContext.startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestDropBatteryPermmssions(Context context) {
        final Context mContext = context;

        final String packageName = context.getPackageName();
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        if (!pm.isIgnoringBatteryOptimizations(packageName))
            return;

        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
        mContext.startActivity(intent);
    }
}

