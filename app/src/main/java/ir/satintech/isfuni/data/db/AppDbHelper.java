


package ir.satintech.isfuni.data.db;



import android.content.Context;

import com.activeandroid.query.Select;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.di.ApplicationContext;




@Singleton
public class AppDbHelper implements DbHelper {

    @Inject
    public AppDbHelper(@ApplicationContext Context context) {
    }


    public List<Location> ListAllLocations() {


        return new Select().from(Location.class).execute();
    }


    public Location SelectLocation(Long id) {
        Location item = Location.load(Location.class, id);
        return item;
    }

    @Override
    public List<Category> ListAllCategories() {
        return new Select().from(Category.class).execute();
    }

    @Override
    public List<Location> getLocationByCategory(Long category_id) {
        return new Select()
                .from(Location.class)
                .where("Category = ?", category_id)
                .execute();
    }

    @Override
    public void insertLocation(Location item) {
        item.save();
    }

    @Override
    public void insertCategory(Category item) {
        item.save();
    }

    @Override
    public List<Location> searchName(String query) {
        return new Select()
                .from(Location.class)
                .where("name LIKE '%" + query + "%'").execute();
    }
}
