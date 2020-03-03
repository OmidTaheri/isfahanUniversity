package ir.satintech.isfuni.data.network;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.satintech.isfuni.data.network.model.Output_Google_Direction_JSONParser;





public class GoogleJsonParser {


    public static Output_Google_Direction_JSONParser Google_Direction_JSONParser(String result) {

        Output_Google_Direction_JSONParser output_google_direction_jsonParser = null;
        try {
             JSONObject json = new JSONObject(result);
            String status = json.getString("status");

            if (status.equals("ZERO_RESULTS")) {

            } else {

                JSONArray routeArray = json.getJSONArray("routes");
                JSONObject routes = routeArray.getJSONObject(0);

                JSONArray legsArray = routes.getJSONArray("legs");
                JSONObject legs = legsArray.getJSONObject(0);

                String dest_address = legs.getString("end_address");
                String src_address = legs.getString("start_address");
                JSONObject distance_object = legs.getJSONObject("distance");
                JSONObject duration_object = legs.getJSONObject("duration");
                String distance = distance_object.getString("text");
                String duration = duration_object.getString("text");


                String dest_address_string = "";
                String dest_address_array[] = dest_address.split("،");

                for (int i = 1; i < dest_address_array.length - 1; i++) {
                    dest_address_string += dest_address_array[i].toString();
                }


                String src_address_string = "";
                String src_address_array[] = src_address.split("،");
                for (int i = 1; i < src_address_array.length - 1; i++) {
                    src_address_string += src_address_array[i].toString();
                }


                JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
                String encodedString = overviewPolylines.getString("points");
                List<LatLng> list = decodePoly(encodedString);


                output_google_direction_jsonParser = new Output_Google_Direction_JSONParser(src_address_string, dest_address_string, duration, distance, list);


            }


        } catch (JSONException e) {

        }
        return output_google_direction_jsonParser;
    }


    private static List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }



}
