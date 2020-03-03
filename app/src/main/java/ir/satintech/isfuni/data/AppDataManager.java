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

package ir.satintech.isfuni.data;


import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ir.satintech.isfuni.data.db.DbHelper;
import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.data.network.ApiHelper;
import ir.satintech.isfuni.data.prefs.PreferencesHelper;
import ir.satintech.isfuni.di.ApplicationContext;
import okhttp3.ResponseBody;


@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;

    private final ApiHelper mApiHelper;


    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper, ApiHelper apiHelper
    ) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public List<Location> ListAllLocations() {
        return mDbHelper.ListAllLocations();
    }

    @Override
    public Location SelectLocation(Long id) {
        return mDbHelper.SelectLocation(id);
    }

    @Override
    public List<Category> ListAllCategories() {
        return mDbHelper.ListAllCategories();
    }

    @Override
    public List<Location> getLocationByCategory(Long category_id) {
        return mDbHelper.getLocationByCategory(category_id);
    }

    @Override
    public void insertLocation(Location item) {
        mDbHelper.insertLocation(item);
    }


    @Override
    public void insertCategory(Category item) {
        mDbHelper.insertCategory(item);
    }

    @Override
    public List<Location> searchName(String query) {
        return mDbHelper.searchName(query);
    }

    @Override
    public void addFavoriteLocation(Location item) {
        mPreferencesHelper.addFavoriteLocation(item);
    }

    @Override
    public void deleteFavoriteLocation(Location item) {
        mPreferencesHelper.deleteFavoriteLocation(item);
    }

    @Override
    public boolean existInFavoriteLocation(int item_id) {
        return mPreferencesHelper.existInFavoriteLocation(item_id);
    }

    @Override
    public List<Location> getFavoriteLocations() {
        return mPreferencesHelper.getFavoriteLocations();
    }

    @Override
    public Observable<ResponseBody> Google_Direction_API(String origin, String destination, String language, String units, String sensor, String mode, String key) {
        return mApiHelper.Google_Direction_API(origin, destination, language, units, sensor, mode, key);
    }
}
