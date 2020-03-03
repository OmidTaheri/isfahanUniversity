


package ir.satintech.isfuni.di.module;

import android.app.Application;
import android.content.Context;

import ir.satintech.isfuni.BuildConfig;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.AppDataManager;
import ir.satintech.isfuni.data.DataManager;
import ir.satintech.isfuni.data.db.AppDbHelper;
import ir.satintech.isfuni.data.db.DbHelper;
import ir.satintech.isfuni.data.network.ApiHelper;
import ir.satintech.isfuni.data.network.AppApiHelper;
import ir.satintech.isfuni.data.prefs.AppPreferencesHelper;
import ir.satintech.isfuni.data.prefs.PreferencesHelper;
import ir.satintech.isfuni.di.ApiInfo;
import ir.satintech.isfuni.di.ApplicationContext;
import ir.satintech.isfuni.di.DatabaseInfo;
import ir.satintech.isfuni.di.PreferenceInfo;
import ir.satintech.isfuni.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;



@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }





    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }




    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }


    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/byekan.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
