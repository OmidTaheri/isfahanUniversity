

package ir.satintech.isfuni.ui.splash;


import android.content.Context;

import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.di.PerActivity;
import ir.satintech.isfuni.ui.base.MvpPresenter;




@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
    void delayToNextActivity(Context ctx);


}
