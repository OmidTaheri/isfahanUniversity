

package ir.satintech.isfuni.ui.location;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.ui.base.BaseActivity;
import ir.satintech.isfuni.utils.GooglePlayServices;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;


public class LocationActivity extends BaseActivity implements LocationMvpView {

    @Inject
    LocationMvpPresenter<LocationMvpView> mPresenter;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vpager)
    ViewPager vpager;

    Category item;

    public static Intent getStartIntent(Context context, Category item) {
        Intent intent = new Intent(context, LocationActivity.class);
        intent.putExtra("item", item);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }


    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        item = getIntent().getExtras().getParcelable("item");

        getSupportActionBar().setTitle(item.getName());


        if (GooglePlayServices.isGooglePlayServicesAvailableByFinish(LocationActivity.this)) {
            vpager.setAdapter(new VPagerAdapter(getSupportFragmentManager(), item));
            tablayout.setupWithViewPager(vpager);

            vpager.setCurrentItem(1);

        }


        Typeface typeface = TypefaceUtils.load(getAssets(), getString(R.string.font_path_regular));

        for (int i = 0; i < tablayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_item_main, null);
            tv.setText(vpager.getAdapter().getPageTitle(i));
            tv.setTypeface(typeface);
            tablayout.getTabAt(i).setCustomView(tv);

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GooglePlayServices.REQUEST_CODE_RECOVER_PLAY_SERVICES:
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "لطفا google play service را نصب کنید",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    vpager.setAdapter(new VPagerAdapter(getSupportFragmentManager(), item));
                    tablayout.setupWithViewPager(vpager);
                    vpager.setCurrentItem(1);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
