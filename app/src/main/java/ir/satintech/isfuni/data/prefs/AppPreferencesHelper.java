

package ir.satintech.isfuni.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.di.ApplicationContext;
import ir.satintech.isfuni.di.PreferenceInfo;



@Singleton
public class AppPreferencesHelper implements PreferencesHelper {


    private static final String PREF_KEY_FAVORITES_ITEMS = "PREF_KEY_FAVORITES_ITEMS";

    private static final String PREF_KEY_FAVORITES_ITEMS_NUMBER = "PREF_KEY_FAVORITES_ITEMS_NUMBER";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public void addFavoriteLocation(Location item) {

        boolean break_boolean = false;
        Gson gson = new Gson();
        SharedPreferences.Editor editor = mPrefs.edit();

        String list = mPrefs.getString(PREF_KEY_FAVORITES_ITEMS, "");

        List<Location> locationFromShared = new ArrayList<>();
        Type type = new TypeToken<List<Location>>() {
        }.getType();

        locationFromShared = gson.fromJson(list, type);


        String jsonNewlocationToAdd = gson.toJson(item);
        JSONArray jsonArraylocation = new JSONArray();

        try {
            if (list.length() != 0) {
                jsonArraylocation = new JSONArray(list);


                for (int i = 0; i < locationFromShared.size(); i++) {

                    if (locationFromShared.get(i).getId() == item.getId()) {
                        break_boolean = true;
                        break;

                    }

                }

                if (!break_boolean) {
                    jsonArraylocation.put(new JSONObject(jsonNewlocationToAdd));

                }


            } else {
                jsonArraylocation.put(new JSONObject(jsonNewlocationToAdd));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        editor.putString(PREF_KEY_FAVORITES_ITEMS, String.valueOf(jsonArraylocation));
        editor.apply();


    }

    @Override
    public void deleteFavoriteLocation(Location item) {
        Gson gson = new Gson();
        SharedPreferences.Editor editor = mPrefs.edit();

        String list = mPrefs.getString(PREF_KEY_FAVORITES_ITEMS, "");

        List<Location> locationFromShared = new ArrayList<>();
        Type type = new TypeToken<List<Location>>() {
        }.getType();


        locationFromShared = gson.fromJson(list, type);


        for (int i = 0; i < locationFromShared.size(); i++) {

            if (locationFromShared.get(i).getId() == item.getId()) {
                locationFromShared.remove(i);

                editor.putInt(PREF_KEY_FAVORITES_ITEMS_NUMBER + item.getId(), 0);
                editor.apply();

            }

        }

        String FavoriteString;

        if (locationFromShared != null || locationFromShared.size() != 0) {
            FavoriteString = gson.toJson(locationFromShared);
        } else {
            FavoriteString = "";
        }

        editor.putString(PREF_KEY_FAVORITES_ITEMS, FavoriteString);
        editor.apply();


    }

    @Override
    public boolean existInFavoriteLocation(int item_id) {
        Gson gson = new Gson();

        String list = mPrefs.getString(PREF_KEY_FAVORITES_ITEMS, "");

        List<Location> locationFromShared = new ArrayList<>();
        Type type = new TypeToken<List<Location>>() {
        }.getType();

        locationFromShared = gson.fromJson(list, type);

        if (locationFromShared != null) {
            for (int i = 0; i < locationFromShared.size(); i++) {

                if (locationFromShared.get(i).getId() == item_id) {
                    return true;

                }

            }
        }

        return false;
    }

    @Override
    public List<Location> getFavoriteLocations() {
        Gson gson = new Gson();

        String list = mPrefs.getString(PREF_KEY_FAVORITES_ITEMS, "");


        List<Location> locationFromShared = new ArrayList<>();
        Type type = new TypeToken<List<Location>>() {
        }.getType();


        locationFromShared = gson.fromJson(list, type);

        if (locationFromShared == null) {
            locationFromShared = new ArrayList<>();
        }

        return locationFromShared;
    }
}
