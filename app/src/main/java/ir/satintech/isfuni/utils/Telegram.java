package ir.satintech.isfuni.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;


public class Telegram {


    private Telegram() {
    }

    public static void openTelegramChannel(String URL, Activity activity) {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(URL));
        final String appName = "org.telegram.messenger";

        if (isAppAvailable(activity.getApplicationContext(), appName)) {
            i.setPackage(appName);
            activity.startActivity(i);
        }
    }

    public static boolean isAppAvailable(Context context, String appName) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
