

package ir.satintech.isfuni.ui.location.list;


import java.util.List;

import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.MvpView;



public interface ListMvpView extends MvpView {


    void setListLocations(List<Location> items_list);

    void showLocationDetailActivity(Location item);

    void visibility_progressBar(boolean show);

    void error_load_List(int message);

}
