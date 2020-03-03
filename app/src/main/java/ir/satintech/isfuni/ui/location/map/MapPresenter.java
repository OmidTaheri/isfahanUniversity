

package ir.satintech.isfuni.ui.location.map;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.BasePresenter;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;



public class MapPresenter<V extends MapMvpView> extends BasePresenter<V>
        implements MapMvpPresenter<V> {

    @Inject
    public MapPresenter(DataManager dataManager,
                        SchedulerProvider schedulerProvider,
                        CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getLocationsList(Long category_id) {

        try {


            getMvpView().setListLocations(getDataManager().getLocationByCategory(category_id));

        } catch (Exception e) {

        }

    }

    @Override
    public void showLocationDetailActivity(Location item) {
        getMvpView().showLocationsDetailActivity( item);
    }
}
