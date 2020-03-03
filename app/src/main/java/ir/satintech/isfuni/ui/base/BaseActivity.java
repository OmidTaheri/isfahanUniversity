

package ir.satintech.isfuni.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import ir.satintech.isfuni.AppLoader;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.di.component.ActivityComponent;
import ir.satintech.isfuni.di.component.DaggerActivityComponent;
import ir.satintech.isfuni.di.module.ActivityModule;

import ir.satintech.isfuni.utils.CommonUtils;
import ir.satintech.isfuni.utils.NetworkUtils;

import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public abstract class BaseActivity extends AppCompatActivity
        implements MvpView, BaseFragment.Callback {

    private ProgressDialog mProgressDialog;

    private ActivityComponent mActivityComponent;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((AppLoader) getApplication()).getComponent())
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);

        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.red));

        textView.setTextColor(ContextCompat.getColor(this, R.color.white));


        textView.setGravity(Gravity.RIGHT);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);


        snackbar.show();
    }


    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    protected abstract void setUp();
}
