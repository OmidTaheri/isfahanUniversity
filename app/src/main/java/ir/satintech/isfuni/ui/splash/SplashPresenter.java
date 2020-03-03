

package ir.satintech.isfuni.ui.splash;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.ui.base.BasePresenter;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;


public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void delayToNextActivity(final Context ctx) {


        Maybe.empty() // returns maybe instance that calls onComplete right away
                .delay(6, TimeUnit.SECONDS) // posting delay of 3 seconds
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }


                        getMvpView().launchMainActivity();


                    }
                })
                .subscribe();


    }


}
