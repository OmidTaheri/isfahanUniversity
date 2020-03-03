

package ir.satintech.isfuni.ui.main;

import java.util.List;

import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.MvpView;


public interface MainMvpView extends MvpView {
    void showLocationActivity(Category item);

    void visibility_progressBar(boolean show);

    void error_load_List(int message);

    void setListCategory(List<Category> items_list);

    void showAboutUsActivity( );
    void showSupportUsActivity( );

    void showSearchActivity(String query);
}
