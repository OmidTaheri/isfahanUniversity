
package ir.satintech.isfuni.ui.main;


import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.di.PerActivity;
import ir.satintech.isfuni.ui.base.MvpPresenter;


@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void getCategoryList();

    void showLocationActivity(Category item);

    void showAboutUsActivity( );

    void showSupportUsActivity( );
    void showSearchActivity(String query);
}
