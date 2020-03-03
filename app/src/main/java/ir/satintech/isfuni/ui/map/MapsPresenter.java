

package ir.satintech.isfuni.ui.map;

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



public class MapsPresenter<V extends MapsMvpView> extends BasePresenter<V>
        implements MapsMvpPresenter<V> {

    private static final String TAG = "MapsPresenter";

    @Inject
    public MapsPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void Google_Direction(Input_Google_Direction input_item) {
        StringBuilder urlString_origin = new StringBuilder();
        urlString_origin.append(Double.toString(input_item.getSourcelat()));
        urlString_origin.append(",");
        urlString_origin.append(Double.toString(input_item.getSourcelog()));


        StringBuilder urlString_destination = new StringBuilder();
        urlString_destination.append(Double.toString(input_item.getDestlat()));
        urlString_destination.append(",");
        urlString_destination.append(Double.toString(input_item.getDestlog()));


        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().Google_Direction_API(urlString_origin.toString(), urlString_destination.toString(), input_item.getLanguage(), input_item.getUnits(), input_item.getSensor(), input_item.getMode(), input_item.getKey())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();


                        Output_Google_Direction_JSONParser result = GoogleJsonParser.Google_Direction_JSONParser(response.string());

                        getMvpView().Show_Direction_Onmap(result.getPoints_list());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();


                        getMvpView().onError("خطا در دریافت اطلاعات.چند لحظه دیگر امتحان کنید");
                        AppLogger.i(throwable.getMessage());
                        AppLogger.i(throwable.getCause().toString());

                    }
                }));

    }
}
