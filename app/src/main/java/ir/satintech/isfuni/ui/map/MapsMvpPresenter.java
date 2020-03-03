
package ir.satintech.isfuni.ui.map;


import ir.satintech.isfuni.data.network.model.Input_Google_Direction;
import ir.satintech.isfuni.di.PerActivity;
import ir.satintech.isfuni.ui.base.MvpPresenter;



@PerActivity
public interface MapsMvpPresenter<V extends MapsMvpView> extends MvpPresenter<V> {



    void Google_Direction(Input_Google_Direction input_item);

}
