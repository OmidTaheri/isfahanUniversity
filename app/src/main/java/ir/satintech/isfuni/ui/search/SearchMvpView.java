

package ir.satintech.isfuni.ui.search;


import android.graphics.Movie;

import java.util.List;

import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.MvpView;




public interface SearchMvpView extends MvpView {


    void setupListofSearch(List<Location> items_list);


    void showLocationDetailActivity(Location item);


    void visibility_progressBar(boolean show);

    void error_load_List(int message, String query);

}
