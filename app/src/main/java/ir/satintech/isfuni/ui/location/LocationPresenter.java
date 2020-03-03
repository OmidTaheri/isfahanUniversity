

package ir.satintech.isfuni.ui.location;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.ui.base.BasePresenter;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;



public class LocationPresenter<V extends LocationMvpView> extends BasePresenter<V>
        implements LocationMvpPresenter<V> {

    private static final String TAG = "LocationPresenter";

    @Inject
    public LocationPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
