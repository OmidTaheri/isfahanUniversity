package ir.satintech.isfuni.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;

import java.util.Locale;

import ir.satintech.isfuni.BuildConfig;


public class DeviceInfo {


    public static String getPackageName( Context context) {
        return context.getPackageName();
    }


    public String getAppVersion( Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }


    public String getDeviceName() {
        return Build.MODEL;
    }

    public String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }



    public String getOsVersion() {
        return BuildConfig.FLAVOR + VERSION.SDK_INT;
        ///return api number
    }

    public String getLanguage( Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = configuration.locale;
        return locale.getLanguage();
        //en  or  fa
    }

    public String getCountry( Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = configuration.locale;

        return locale.getCountry();

        //US  OR IR
        //VABASTE BE ZABAN NA MAKAN
    }


    public static String getScreenSize( Context context) {

        String ScreenSizeText = "";

        int screenSize = context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;


        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                ScreenSizeText = AppConstants.LARGE_STRING_SCREENSIZE;
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                ScreenSizeText = AppConstants.NORMAL_STRING_SCREENSIZE;
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                ScreenSizeText = AppConstants.SMALL_STRING_SCREENSIZE;
                break;

            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                ScreenSizeText = AppConstants.XLARGE_STRING_SCREENSIZE;
                break;
        }


        return ScreenSizeText;
    }


    public String getScreenDensity( Context context) {


        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();


//        int i = displayMetrics.densityDpi;
//        if (i == 0) {
//            return "";
//        }
//        if (i < 140) {
//            return AppConstants.LOW_STRING_DENSITY;
//        }
//        if (i > 200) {
//            return AppConstants.HIGH_STRING_DENSITY;
//        }
//        return AppConstants.MEDDLE_STRING_DENSITY;
return  null;

    }





    public boolean getDeviceMoreThan5Inch( Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();


            float yInches = displayMetrics.heightPixels / displayMetrics.ydpi;
            float xInches = displayMetrics.widthPixels / displayMetrics.xdpi;
            double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
            if (diagonalInches >= 7) {
                // 5inch device or bigger
                return true;
            } else {
                // smaller device
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public String getDeviceInch( Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float yInches = displayMetrics.heightPixels / displayMetrics.ydpi;
            float xInches = displayMetrics.widthPixels / displayMetrics.xdpi;
            double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
            return String.valueOf(diagonalInches);
        } catch (Exception e) {
            return "-1";
        }
    }
}
