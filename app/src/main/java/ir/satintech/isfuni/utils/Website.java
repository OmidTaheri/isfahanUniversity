package ir.satintech.isfuni.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;


public class Website {

    private Website() {

    }


    public static void openWebSite(String URL, Activity activity) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        activity.startActivity(browserIntent);

    }

}
