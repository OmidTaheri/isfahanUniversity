


package ir.satintech.isfuni.data.network;




import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import okhttp3.ResponseBody;



@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiInterface mApiInterface;

     @Inject
    public AppApiHelper(ApiInterface apiInterface) {
            mApiInterface=apiInterface;
    }


    public ApiInterface getApiInterface() {
        return mApiInterface;
    }


    @Override
    public Observable<ResponseBody> Google_Direction_API(String origin, String destination, String language, String units, String sensor, String mode, String key) {
        return getApiInterface().Google_Direction_API(  origin,   destination,   language,   units,   sensor,   mode,   key);
    }
}

