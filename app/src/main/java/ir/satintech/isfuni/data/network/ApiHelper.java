


package ir.satintech.isfuni.data.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;



public interface ApiHelper {


    Observable<ResponseBody> Google_Direction_API(String origin, String destination, String language, String units
            , String sensor, String mode, String key);


}
