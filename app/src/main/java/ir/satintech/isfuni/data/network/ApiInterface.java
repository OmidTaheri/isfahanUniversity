package ir.satintech.isfuni.data.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET(ApiEndPoint.ENDPOINT_GOOGLE_API)
    Observable<ResponseBody> Google_Direction_API(@Query("origin") String origin, @Query("destination") String destination, @Query("language") String language, @Query("units") String units
            , @Query("sensor") String sensor, @Query("mode") String mode, @Query("key") String key);


}
