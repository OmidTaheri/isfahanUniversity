
package ir.satintech.isfuni.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.ui.about.AboutUsActivity;
import ir.satintech.isfuni.ui.base.BaseActivity;
import ir.satintech.isfuni.ui.location.LocationActivity;
import ir.satintech.isfuni.ui.search.SearchActivity;
import ir.satintech.isfuni.ui.support.SupportUsActivity;
import ir.satintech.isfuni.utils.Dialog;

public class MainActivity extends BaseActivity implements MainMvpView, MainAdapter.Callback, AppBarLayout.OnOffsetChangedListener, MaterialSearchBar.OnSearchActionListener, Dialog.Callback {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;
    @BindView(R.id.main_image)
    ImageView mainImage;
    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout mainCollapsing;
    @BindView(R.id.main_appbar)
    AppBarLayout mainAppbar;
    @BindView(R.id.categorylist)
    RecyclerView categorylist;
    @BindView(R.id.category_progressBar)
    ProgressBar categoryProgressBar;
    @BindView(R.id.errore_text)
    TextView erroreText;
    @BindView(R.id.error_btn_retry)
    Button errorBtnRetry;
    @BindView(R.id.error_layout)
    ConstraintLayout errorLayout;
    @BindView(R.id.category_list_layout)
    ConstraintLayout categoryListLayout;
    @BindView(R.id.searchView)
    MaterialSearchBar searchView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    /////////////////////////////////////////////////////

    private static final String TAG = "UpdateCheck";


    private ActionBarDrawerToggle mDrawerToggle;

    ///
    boolean doubleBackToExitPressedOnce = false;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    private boolean mIsAvatarShown = true;
    private int mMaxScrollSize;
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            searchView.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            searchView.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        showMessage(getString(R.string.double_exit_message));

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void onPositiveClick() {

    }

    @Override
    public void onNegativeClick() {
    }


    ////////////////////////////


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_aboutus:
                mPresenter.showAboutUsActivity();
                return true;


            case R.id.action_supportus:
                mPresenter.showSupportUsActivity();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void setUp() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        mainAppbar.addOnOffsetChangedListener(this);
        mMaxScrollSize = mainAppbar.getTotalScrollRange();

        searchView.setOnSearchActionListener(this);

        mPresenter.getCategoryList();




    }


    @Override
    public void showLocationActivity(Category item) {

        Intent intent = LocationActivity.getStartIntent(MainActivity.this, item);
        startActivity(intent);
    }

    @Override
    public void visibility_progressBar(boolean show) {
        if (show) {
            categoryProgressBar.setVisibility(View.VISIBLE);
        } else {

            categoryProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void error_load_List(int message) {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            categoryProgressBar.setVisibility(View.GONE);


            erroreText.setText(getResources().getString(message));
        }

        errorBtnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errorLayout.setVisibility(View.GONE);
                categoryProgressBar.setVisibility(View.VISIBLE);

                mPresenter.getCategoryList();

            }
        });
    }

    @Override
    public void setListCategory(List<Category> items_list) {

        //////////////////////////////
        MyGridAutofitLayoutManager manager = new MyGridAutofitLayoutManager(this, categorylist);
        final GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(this, manager.getmColumnNumber());
        categorylist.setLayoutManager(layoutManager);
//////////

        final MainAdapter adapter = new MainAdapter(items_list, MainActivity.this, manager);
        adapter.setCallback(this);
        categorylist.setAdapter(adapter);


    }

    @Override
    public void showAboutUsActivity() {
        Intent intent = AboutUsActivity.getStartIntent(MainActivity.this);
        startActivity(intent);

    }

    @Override
    public void showSupportUsActivity() {
        Intent intent = SupportUsActivity.getStartIntent(MainActivity.this);
        startActivity(intent);
    }

    @Override
    public void showSearchActivity(String query) {
        Intent intent = SearchActivity.getStartIntent(MainActivity.this, query);
        startActivity(intent);
    }

    @Override
    public void onItemClick(Category item) {
        mPresenter.showLocationActivity(item);
    }


    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

        if (text.toString().equals("")) {

            showMessage("عبارت مورد نظر خود را وارد کنید");
        } else {
            mPresenter.showSearchActivity(text.toString());
        }
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }


}
