package ir.satintech.isfuni.ui.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.ui.base.BaseActivity;
import ir.satintech.isfuni.utils.AppConstants;
import ir.satintech.isfuni.utils.Instagram;
import ir.satintech.isfuni.utils.Telegram;
import ir.satintech.isfuni.utils.Website;


public class AboutUsActivity extends BaseActivity implements AboutUsMvpView {


    @Inject
    AboutUsMvpPresenter<AboutUsMvpView> mPresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.AppBar)
    AppBarLayout AppBar;
    @BindView(R.id.program_logo)
    ImageView programLogo;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.satintech_logo)
    ImageView satintechLogo;
    @BindView(R.id.instagram)
    ImageView instagram;
    @BindView(R.id.telegram)
    ImageView telegram;
    @BindView(R.id.website)
    ImageView website;


    public static Intent getStartIntent(Context context) {
        return new Intent(context, AboutUsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

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

        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openTelegram();
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openInstagram();
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openSite();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void openTelegramChannel() {
        Telegram.openTelegramChannel(AppConstants.SATIN_TECH_TELEGRAM, this);
    }

    @Override
    public void openInstagram() {
        Instagram.openInstagramPage(AppConstants.SATIN_TECH_INSTAGRAM_PAGE, this);
    }

    @Override
    public void openWebsite() {
        Website.openWebSite(AppConstants.SATIN_TECH_WEBPAGE, this);
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
