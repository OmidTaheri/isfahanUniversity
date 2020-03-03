


package ir.satintech.isfuni.di.component;

import android.app.Application;
import android.content.Context;

import ir.satintech.isfuni.AppLoader;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.di.ApplicationContext;
import ir.satintech.isfuni.di.module.ApplicationModule;
import ir.satintech.isfuni.di.module.NetworkModule;


import javax.inject.Singleton;

import dagger.Component;




@Singleton
@Component(modules = {ApplicationModule.class,NetworkModule.class})
public interface ApplicationComponent {

    void inject(AppLoader app);



    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}