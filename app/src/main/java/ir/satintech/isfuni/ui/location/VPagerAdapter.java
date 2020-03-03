
package ir.satintech.isfuni.ui.location;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.ui.location.list.ListFragment;
import ir.satintech.isfuni.ui.location.map.MapFragment;




public class VPagerAdapter extends FragmentPagerAdapter {

    public final int PAGE_COUNT = 2;

    Category item;

    public VPagerAdapter(FragmentManager fm, Category item) {
        super(fm);
        this.item = item;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return MapFragment.newInstance(item.getId());


            case 1:
                return ListFragment.newInstance(item.getId());


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "مکان ها رو نقشه";

            case 1:
                return "لیست مکان ها";

            default:
                return null;
        }
    }
}
