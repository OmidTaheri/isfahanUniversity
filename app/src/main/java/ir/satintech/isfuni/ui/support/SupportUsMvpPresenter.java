
package ir.satintech.isfuni.ui.support;


import ir.satintech.isfuni.di.PerActivity;
import ir.satintech.isfuni.ui.base.MvpPresenter;



@PerActivity
public interface SupportUsMvpPresenter<V extends SupportUsMvpView> extends MvpPresenter<V> {


    void sendEmail();
    void openTelegram();


}
