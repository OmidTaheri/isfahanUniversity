

package ir.satintech.isfuni;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.activeandroid.ActiveAndroid;



import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.di.component.ApplicationComponent;
import ir.satintech.isfuni.di.component.DaggerApplicationComponent;
import ir.satintech.isfuni.di.module.ApplicationModule;


import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class AppLoader extends MultiDexApplication {

    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);


        CalligraphyConfig.initDefault(mCalligraphyConfig);

        ActiveAndroid.initialize(this);

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
