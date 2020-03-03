/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package ir.satintech.isfuni.di.component;

import dagger.Component;
import ir.satintech.isfuni.di.PerActivity;
import ir.satintech.isfuni.di.module.ActivityModule;
import ir.satintech.isfuni.ui.about.AboutUsActivity;
import ir.satintech.isfuni.ui.detailpage.DetailLocationActivity;
import ir.satintech.isfuni.ui.location.LocationActivity;
import ir.satintech.isfuni.ui.location.list.ListFragment;
import ir.satintech.isfuni.ui.location.map.MapFragment;
import ir.satintech.isfuni.ui.main.MainActivity;
import ir.satintech.isfuni.ui.map.MapsActivity;
import ir.satintech.isfuni.ui.search.SearchActivity;
import ir.satintech.isfuni.ui.splash.SplashActivity;
import ir.satintech.isfuni.ui.support.SupportUsActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);


    void inject(SplashActivity activity);


    void inject(AboutUsActivity activity);

    void inject(LocationActivity activity);

    void inject(SupportUsActivity activity);


    void inject(ListFragment fragment);


    void inject(MapFragment fragment);

    void inject(DetailLocationActivity activity);

    void inject(SearchActivity activity);

    void inject(MapsActivity activity);


}
