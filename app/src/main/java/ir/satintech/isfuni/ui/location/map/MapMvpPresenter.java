

package ir.satintech.isfuni.ui.location.map;


import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.MvpPresenter;


public interface MapMvpPresenter<V extends MapMvpView>
        extends MvpPresenter<V> {

    void getLocationsList(Long category_id);

    void showLocationDetailActivity(Location item);
}


