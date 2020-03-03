


package ir.satintech.isfuni.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import ir.satintech.isfuni.di.ActivityContext;
import ir.satintech.isfuni.di.PerActivity;
import ir.satintech.isfuni.ui.about.AboutUsMvpPresenter;
import ir.satintech.isfuni.ui.about.AboutUsMvpView;
import ir.satintech.isfuni.ui.about.AboutUsPresenter;

import ir.satintech.isfuni.ui.base.MvpView;
import ir.satintech.isfuni.ui.detailpage.DetailLocationMvpPresenter;
import ir.satintech.isfuni.ui.detailpage.DetailLocationMvpView;
import ir.satintech.isfuni.ui.detailpage.DetailLocationPresenter;
import ir.satintech.isfuni.ui.location.LocationMvpPresenter;
import ir.satintech.isfuni.ui.location.LocationMvpView;
import ir.satintech.isfuni.ui.location.LocationPresenter;
import ir.satintech.isfuni.ui.location.VPagerAdapter;
import ir.satintech.isfuni.ui.location.list.ListMvpPresenter;
import ir.satintech.isfuni.ui.location.list.ListMvpView;
import ir.satintech.isfuni.ui.location.list.ListPresenter;
import ir.satintech.isfuni.ui.location.map.MapMvpPresenter;
import ir.satintech.isfuni.ui.location.map.MapMvpView;
import ir.satintech.isfuni.ui.location.map.MapPresenter;
import ir.satintech.isfuni.ui.main.MainMvpPresenter;
import ir.satintech.isfuni.ui.main.MainMvpView;
import ir.satintech.isfuni.ui.main.MainPresenter;
import ir.satintech.isfuni.ui.map.MapsMvpPresenter;
import ir.satintech.isfuni.ui.map.MapsMvpView;
import ir.satintech.isfuni.ui.map.MapsPresenter;
import ir.satintech.isfuni.ui.search.SearchMvpPresenter;
import ir.satintech.isfuni.ui.search.SearchMvpView;
import ir.satintech.isfuni.ui.search.SearchPresenter;
import ir.satintech.isfuni.ui.splash.SplashMvpPresenter;
import ir.satintech.isfuni.ui.splash.SplashMvpView;
import ir.satintech.isfuni.ui.splash.SplashPresenter;
import ir.satintech.isfuni.ui.support.SupportUsMvpPresenter;
import ir.satintech.isfuni.ui.support.SupportUsMvpView;
import ir.satintech.isfuni.ui.support.SupportUsPresenter;
import ir.satintech.isfuni.utils.rx.AppSchedulerProvider;
import ir.satintech.isfuni.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AboutUsMvpPresenter<AboutUsMvpView> provideAboutUsPresenter(
            AboutUsPresenter<AboutUsMvpView> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    LocationMvpPresenter<LocationMvpView> provideLocationPresenter(
            LocationPresenter<LocationMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ListMvpPresenter<ListMvpView> provideListPresenter(
            ListPresenter<ListMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    MapMvpPresenter<MapMvpView> provideMapPresenter(
            MapPresenter<MapMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    DetailLocationMvpPresenter<DetailLocationMvpView> provideDetailLocationPresenter(
            DetailLocationPresenter<DetailLocationMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SearchMvpPresenter<SearchMvpView> provideSearchPresenter(
            SearchPresenter< SearchMvpView> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    MapsMvpPresenter<MapsMvpView> provideMapsPresenter(
            MapsPresenter< MapsMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    SupportUsMvpPresenter<SupportUsMvpView> provideSupportUsPresenter(
            SupportUsPresenter< SupportUsMvpView> presenter) {
        return presenter;
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
