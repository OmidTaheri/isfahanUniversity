package ir.satintech.isfuni.utils;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import ir.satintech.isfuni.R;



public class Dialog {

    public interface Callback {

        void onPositiveClick();

        void onNegativeClick();
    }


    public static MaterialDialog Show_Update_Dialog(final Activity appcontext, final Callback callback) {


        MaterialDialog Dialog = new MaterialDialog.Builder(appcontext)
                .title(appcontext.getResources().getString(R.string.Dialog_title))
                .content(appcontext.getResources().getString(R.string.Dialog_content))
                .contentColorRes(R.color.darkPrimaryText)
                .iconRes(R.mipmap.ic_launcher)
                .backgroundColorRes(R.color.white)
                .dividerColorRes(R.color.darkDividers)
                .negativeColorRes(R.color.colorAccent)
                .positiveColorRes(R.color.colorAccent)
                .titleColorRes(R.color.darkPrimaryText)
                .titleGravity(GravityEnum.CENTER)
                .typeface("byekan_bold.ttf", "byekan.ttf")
                .positiveText("بله")
                .negativeText("خیر")
                .contentGravity(GravityEnum.END)
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        if (callback != null) {
                            callback.onPositiveClick();
                        }

                    }
                })

                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (callback != null) {
                            callback.onNegativeClick();
                        }

                    }
                })


                .build();
        Dialog.show();
        return Dialog;
    }


}
