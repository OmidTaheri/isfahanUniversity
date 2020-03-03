

package ir.satintech.isfuni.ui.location.list;


import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.MvpPresenter;



public interface ListMvpPresenter<V extends ListMvpView>
        extends MvpPresenter<V> {

    void getLocationsList(Long category_id);

    void showLocationDetailActivity(Location item);
}


