

package ir.satintech.isfuni.ui.base;


import android.util.Log;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.DataManager;

import ir.satintech.isfuni.utils.AppConstants;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;
import retrofit2.Response;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public int handleApiError(Throwable throwable) {

        if (throwable instanceof HttpException) {

            Response<?> response = ((HttpException) throwable).response();


            switch (response.code()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    return R.string.ERROR_UNAUTHORIZED;


                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                case HttpsURLConnection.HTTP_NOT_FOUND:
                case HttpsURLConnection.HTTP_UNAVAILABLE:
                    return R.string.ERROR_NOT_FOUND;


                case HttpsURLConnection.HTTP_BAD_REQUEST:
                    return R.string.ERROR_GENERAL;

                default:
                    return R.string.ERROR_GENERAL;

            }

        } else if (throwable instanceof SocketTimeoutException) {
            return R.string.ERROR_GENERAL;
        } else if (throwable instanceof IOException) {
            return R.string.ERROR_GENERAL;
        } else if (throwable instanceof IllegalStateException) {
            return R.string.ERROR_GENERAL;
        } else {

            return R.string.ERROR_GENERAL;

        }
    }



    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
