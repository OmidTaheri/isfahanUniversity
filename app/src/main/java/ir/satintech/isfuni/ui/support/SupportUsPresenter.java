
package ir.satintech.isfuni.ui.support;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.ui.base.BasePresenter;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;




public class SupportUsPresenter<V extends SupportUsMvpView> extends BasePresenter<V>
        implements SupportUsMvpPresenter<V> {

    private static final String TAG = "LocationPresenter";

    @Inject
    public SupportUsPresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void sendEmail() {
        getMvpView().sendEmail();
    }


    @Override
    public void openTelegram() {
        getMvpView().openTelegramChannel();
    }


}
