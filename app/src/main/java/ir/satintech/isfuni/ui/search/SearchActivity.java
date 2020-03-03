package ir.satintech.isfuni.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.BaseActivity;
import ir.satintech.isfuni.ui.detailpage.DetailLocationActivity;
import ir.satintech.isfuni.utils.AppLogger;


public class SearchActivity extends BaseActivity implements SearchMvpView, SearchAdapter.Callback {

    @Inject
    SearchMvpPresenter<SearchMvpView> mPresenter;


    String query;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_list)
    RecyclerView searchList;
    @BindView(R.id.main_progress)
    ProgressBar mainProgress;
    @BindView(R.id.errore_text)
    TextView erroreText;
    @BindView(R.id.error_btn_retry)
    Button errorBtnRetry;
    @BindView(R.id.error_layout)
    ConstraintLayout errorLayout;

    public static Intent getStartIntent(Context context, String query) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("query", query);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_search2);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();

    }

    @Override
    protected void setUp() {

        query = getIntent().getExtras().getString("query");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(query);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        doMySearch(query);
    }


    private void doMySearch(String query) {

        mPresenter.search(query);
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }


    @Override
    public void setupListofSearch(List<Location> items_list) {


        SearchAdapter adapter = new SearchAdapter(items_list, this);
        adapter.setCallback(this);
        searchList.setAdapter(adapter);

        searchList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showLocationDetailActivity(Location item) {
        startActivity(DetailLocationActivity.getStartIntent(this, item));
    }

    @Override
    public void visibility_progressBar(boolean show) {
        if (show) {
            mainProgress.setVisibility(View.VISIBLE);
        } else {

            mainProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void error_load_List(int message, final String query) {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            mainProgress.setVisibility(View.GONE);


            erroreText.setText(getResources().getString(message));
        }

        errorBtnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errorLayout.setVisibility(View.GONE);
                mainProgress.setVisibility(View.VISIBLE);

                mPresenter.search(query);

            }
        });
    }


    @Override
    public void onItemClick(Location item) {
        mPresenter.showLocationDetailActivity(item);
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
