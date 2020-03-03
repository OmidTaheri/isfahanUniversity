package ir.satintech.isfuni.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;

import java.io.File;


public class Share_data {

    public void shareApplication(Activity start) {

        ApplicationInfo app = start.getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;

        android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_SEND);

        // MIME of .apk is "application/vnd.android.package-archive".
        // but Bluetooth does not accept this. Let's use "*/*" instead.
        intent.setType("*/*");


        // Append file and send Intent
        intent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
        start.startActivityForResult(android.content.Intent.createChooser(intent, "Share app via"), 1);
    }


    public static void shareLocation(String name, double latitude, double longitude, Activity start) {


        String uri = "http://maps.google.com/maps?saddr=" + latitude + "," + longitude;

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String ShareSub = name;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, ShareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, uri);
        start.startActivity(Intent.createChooser(sharingIntent, "اشتراک با"));
    }

}
