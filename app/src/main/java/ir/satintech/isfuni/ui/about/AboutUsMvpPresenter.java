

package ir.satintech.isfuni.ui.about;


import ir.satintech.isfuni.di.PerActivity;
import ir.satintech.isfuni.ui.base.MvpPresenter;



@PerActivity
public interface AboutUsMvpPresenter<V extends AboutUsMvpView> extends MvpPresenter<V> {


    void openSite();
    void openTelegram();
    void openInstagram();

}
