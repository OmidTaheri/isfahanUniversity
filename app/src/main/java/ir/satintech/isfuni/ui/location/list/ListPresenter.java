

package ir.satintech.isfuni.ui.location.list;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.BasePresenter;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;



public class ListPresenter<V extends ListMvpView> extends BasePresenter<V>
        implements ListMvpPresenter<V> {

    @Inject
    public ListPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getLocationsList(Long category_id) {
        getMvpView().visibility_progressBar(true);
        try {


            getMvpView().setListLocations(getDataManager().getLocationByCategory(category_id));
            getMvpView().visibility_progressBar(false);
        } catch (Exception e) {
            getMvpView().visibility_progressBar(false);
            getMvpView().error_load_List(R.string.ERROR_GENERAL);
        }

    }

    @Override
    public void showLocationDetailActivity(Location item) {
        getMvpView().showLocationDetailActivity( item);
    }
}
