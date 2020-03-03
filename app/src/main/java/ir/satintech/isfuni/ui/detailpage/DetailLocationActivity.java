package ir.satintech.isfuni.ui.detailpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.BaseActivity;
import ir.satintech.isfuni.ui.map.MapsActivity;
import ir.satintech.isfuni.utils.GetResourceByName;
import ir.satintech.isfuni.utils.Phone;
import ir.satintech.isfuni.utils.Share_data;
import ir.satintech.isfuni.utils.Website;


public class DetailLocationActivity extends BaseActivity implements DetailLocationMvpView {


    @BindView(R.id.location_image)
    ImageView locationImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.description_text)
    TextView descriptionText;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.telephon)
    TextView telephon;
    @BindView(R.id.routing)
    TextView routing;
    @BindView(R.id.share_location)
    TextView shareLocation;


    @Inject
    DetailLocationMvpPresenter<DetailLocationMvpView> mPresenter;


    Location item;

    public static Intent getStartIntent(Context context, Location item) {
        Intent intent = new Intent(context, DetailLocationActivity.class);
        intent.putExtra("Item", item);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();


    }

    @Override
    protected void setUp() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        item = getIntent().getExtras().getParcelable("Item");


        showDetailLocation(item);

        routing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(MapsActivity.getStartIntent(DetailLocationActivity.this, item.getLat(), item.getLog(), true));

            }
        });

        telephon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (item.getPhon() == null || item.getPhon().equals("")) {
                    telephon.setEnabled(false);
                    showMessage("تلفنی ثبت نشده است");
                } else {
                    Phone.call(item.getPhon(), DetailLocationActivity.this, DetailLocationActivity.this);
                }

            }
        });


        shareLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Share_data.shareLocation(item.getName(), item.getLat(), item.getLog(), DetailLocationActivity.this);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MapsActivity.getStartIntent(DetailLocationActivity.this, item.getLat(), item.getLog(), false));

            }
        });
    }


    @Override
    public void showDetailLocation(final Location detailLocation) {
        name.setText(detailLocation.getName());
        if (detailLocation.getSite() == null || detailLocation.getSite().equals("")) {
            descriptionText.setText("www.ui.ac.ir");
            descriptionText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Website.openWebSite("http://www.ui.ac.ir", DetailLocationActivity.this);
                }
            });
        } else {
            descriptionText.setText(detailLocation.getSite());

            descriptionText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Website.openWebSite(detailLocation.getSite(), DetailLocationActivity.this);
                }
            });
        }


        try {
            int ResId = GetResourceByName.getDrawable(DetailLocationActivity.this, item.getImage_url());


            Glide.with(DetailLocationActivity.this)
                    .load(ResId)
                    .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                    .into(locationImage);
        } catch (Exception e) {
        }


    }




    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
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
}
