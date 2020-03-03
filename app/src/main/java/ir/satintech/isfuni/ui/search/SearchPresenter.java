

package ir.satintech.isfuni.ui.search;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.BasePresenter;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;



public class SearchPresenter<V extends SearchMvpView> extends BasePresenter<V>
        implements SearchMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public SearchPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void search(String query) {
        getMvpView().visibility_progressBar(true);
        try {

            getMvpView().setupListofSearch(getDataManager().searchName(query));
            getMvpView().visibility_progressBar(false);

        } catch (Exception e) {
            getMvpView().visibility_progressBar(false);
            getMvpView().error_load_List(R.string.ERROR_GENERAL,query);
        }
    }

    @Override
    public void showLocationDetailActivity(Location item) {
        getMvpView().showLocationDetailActivity(item);
    }
}
