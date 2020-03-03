

package ir.satintech.isfuni.ui.detailpage;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.data.network.GoogleJsonParser;
import ir.satintech.isfuni.data.network.model.Input_Google_Direction;
import ir.satintech.isfuni.data.network.model.Output_Google_Direction_JSONParser;
import ir.satintech.isfuni.ui.base.BasePresenter;
import ir.satintech.isfuni.utils.AppLogger;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;
import okhttp3.ResponseBody;
import retrofit2.Call;



public class DetailLocationPresenter<V extends DetailLocationMvpView> extends BasePresenter<V>
        implements DetailLocationMvpPresenter<V> {

    private static final String TAG = "MapsPresenter";

    @Inject
    public DetailLocationPresenter(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }




}
