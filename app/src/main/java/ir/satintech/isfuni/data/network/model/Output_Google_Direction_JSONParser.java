package ir.satintech.isfuni.data.network.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


public class Output_Google_Direction_JSONParser {

    String src_address_string;
    String dest_address_string;
    String duration;
    String distance;
    List<LatLng> points_list;

    public Output_Google_Direction_JSONParser(String src_address_string, String dest_address_string, String duration, String distance, List<LatLng> points_list) {
        this.src_address_string = src_address_string;
        this.dest_address_string = dest_address_string;
        this.duration = duration;
        this.distance = distance;
        this.points_list = points_list;
    }

    public String getSrc_address_string() {
        return src_address_string;
    }

    public void setSrc_address_string(String src_address_string) {
        this.src_address_string = src_address_string;
    }

    public String getDest_address_string() {
        return dest_address_string;
    }

    public void setDest_address_string(String dest_address_string) {
        this.dest_address_string = dest_address_string;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<LatLng> getPoints_list() {
        return points_list;
    }

    public void setPoints_list(List<LatLng> points_list) {
        this.points_list = points_list;
    }
}
