package ir.satintech.isfuni.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;



public class Phone {


    public static void call(String phone_number, Context cntx, Activity start) {

        try {
            String contact_number = phone_number;
            Intent callIntent = new Intent(android.content.Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + contact_number));
            start.startActivity(callIntent);


        } catch (android.content.ActivityNotFoundException ex) {

        }
    }


    @SuppressLint("MissingPermission")
    public void ussd(String usssd, Context cntx, Activity start) {

        try {
            String USSD = Uri.encode(usssd) + Uri.encode("#");
            System.out.println("" + USSD);
            android.content.Intent callIntent = new android.content.Intent(android.content.Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + USSD));

            if (ActivityCompat.checkSelfPermission(cntx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(start, new String[]{
                                Manifest.permission.CALL_PHONE},
                        110);
            } else {
                start.startActivity(callIntent);
            }
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

}
