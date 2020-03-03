
package ir.satintech.isfuni.ui.location.map;


import java.util.List;

import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.MvpView;



public interface MapMvpView extends MvpView {


    void setListLocations(List<Location> items_list);

    void showLocationsDetailActivity(Location item);

}
