
package ir.satintech.isfuni.ui.about;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.ui.base.BasePresenter;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;




public class AboutUsPresenter<V extends AboutUsMvpView> extends BasePresenter<V>
        implements AboutUsMvpPresenter<V> {

    private static final String TAG = "LocationPresenter";

    @Inject
    public AboutUsPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }




    @Override
    public void openSite() {
        getMvpView().openWebsite();
    }

    @Override
    public void openTelegram() {
        getMvpView().openTelegramChannel();
    }

    @Override
    public void openInstagram() {

        getMvpView().openInstagram();

    }


}
