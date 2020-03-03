package ir.satintech.isfuni.utils;

import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;



public class GooglePlayServices {

    public static final int REQUEST_CODE_RECOVER_PLAY_SERVICES = 2018;

    public static boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, REQUEST_CODE_RECOVER_PLAY_SERVICES).show();
            } else {
                Toast.makeText(activity, "دستگاه شما این امکان را پشتیبانی نمیکند",
                        Toast.LENGTH_LONG).show();
            }
            return false;
        }
        return true;
    }




    public static boolean isGooglePlayServicesAvailableByFinish(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.showErrorDialogFragment(activity, status, REQUEST_CODE_RECOVER_PLAY_SERVICES);
            } else {
                Toast.makeText(activity, "دستگاه شما این امکان را پشتیبانی نمیکند",
                        Toast.LENGTH_LONG).show();
                activity.finish();
            }
            return false;
        }
        return true;
    }


}
