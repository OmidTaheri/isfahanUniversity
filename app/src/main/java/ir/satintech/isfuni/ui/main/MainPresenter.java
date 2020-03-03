

package ir.satintech.isfuni.ui.main;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.ui.base.BasePresenter;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;



public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = "LocationPresenter";

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getCategoryList() {
        getMvpView().visibility_progressBar(true);
        try {


            getMvpView().setListCategory(getDataManager().ListAllCategories());
            getMvpView().visibility_progressBar(false);
        } catch (Exception e) {
            getMvpView().visibility_progressBar(false);
            getMvpView().error_load_List(R.string.ERROR_GENERAL);
        }

    }

    @Override
    public void showLocationActivity(Category item) {
        getMvpView().showLocationActivity(item);
    }

    @Override
    public void showAboutUsActivity() {
        getMvpView().showAboutUsActivity();
    }

    @Override
    public void showSupportUsActivity() {
        getMvpView().showSupportUsActivity();
    }

    @Override
    public void showSearchActivity(String query) {
        getMvpView().showSearchActivity(query);
    }
}
