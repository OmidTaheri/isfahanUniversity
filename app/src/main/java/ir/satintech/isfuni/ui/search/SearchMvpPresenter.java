

package ir.satintech.isfuni.ui.search;


import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.di.PerActivity;
import ir.satintech.isfuni.ui.base.MvpPresenter;


@PerActivity
public interface SearchMvpPresenter<V extends SearchMvpView> extends MvpPresenter<V> {


    void search(String query);

    void showLocationDetailActivity(Location item);
}
