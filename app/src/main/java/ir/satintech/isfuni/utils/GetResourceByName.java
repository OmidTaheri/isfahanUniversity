package ir.satintech.isfuni.utils;

import android.content.Context;
import android.content.res.Resources;

public class GetResourceByName {


    public static int getDrawable(Context ctx, String name) {
        Resources res = ctx.getResources();
        int resID = res.getIdentifier(name, "drawable", ctx.getPackageName());
        return resID;
    }

}
