

package ir.satintech.isfuni.ui.map;


import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import ir.satintech.isfuni.ui.base.MvpView;



public interface MapsMvpView extends MvpView {

    public void Show_Direction_Onmap(List<LatLng> list);
}
