
package ir.satintech.isfuni.data.prefs;

import java.util.List;

import ir.satintech.isfuni.data.db.model.Location;

public interface PreferencesHelper {

    void addFavoriteLocation(Location item);

    void deleteFavoriteLocation(Location item);

    boolean existInFavoriteLocation(int item_id);

    List<Location> getFavoriteLocations();

}
