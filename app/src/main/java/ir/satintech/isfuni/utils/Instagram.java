package ir.satintech.isfuni.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;


public class Instagram {

    private Instagram() {
    }

    public static void openInstagramPage(String URL, Activity activity) {

        Uri uri = Uri.parse(URL);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        final String appName = "com.instagram.android";

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
