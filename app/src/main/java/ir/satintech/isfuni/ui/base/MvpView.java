
package ir.satintech.isfuni.ui.base;

import android.support.annotation.StringRes;

public interface MvpView {

    void showLoading();

    void hideLoading();


    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

}
