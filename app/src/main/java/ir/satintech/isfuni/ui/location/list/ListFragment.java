package ir.satintech.isfuni.ui.location.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.di.component.ActivityComponent;
import ir.satintech.isfuni.ui.base.BaseFragment;
import ir.satintech.isfuni.ui.detailpage.DetailLocationActivity;
import ir.satintech.isfuni.ui.main.MainActivity;




public class ListFragment extends BaseFragment implements
        ListMvpView, ListAdapter.Callback {

    @Inject
    ListMvpPresenter<ListMvpView> mPresenter;


    @BindView(R.id.location_list)
    RecyclerView locationList;
    Unbinder unbinder;
    @BindView(R.id.location_progressBar)
    ProgressBar locationProgressBar;
    @BindView(R.id.errore_text)
    TextView erroreText;
    @BindView(R.id.error_btn_retry)
    Button errorBtnRetry;
    @BindView(R.id.error_layout)
    ConstraintLayout errorLayout;
    @BindView(R.id.location_list_layout)
    ConstraintLayout locationListLayout;
    /////////
    Long category_id;

    public static ListFragment newInstance(Long category_id) {

        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putLong("category_id", category_id);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, v));
            mPresenter.onAttach(this);

        }


        unbinder = ButterKnife.bind(this, v);
        return v;
    }


    @Override
    protected void setUp(View view) {

        category_id = getArguments().getLong("category_id", 1);


            mPresenter.getLocationsList(category_id);

    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
        unbinder.unbind();


    }


    @Override
    public void onItemClick(Location item) {
        mPresenter.showLocationDetailActivity(item);
    }

    @Override
    public void setListLocations(List<Location> items_list) {


        ListAdapter adapter = new ListAdapter(items_list, getBaseActivity());
        adapter.setCallback(this);
        locationList.setAdapter(adapter);


        locationList.setLayoutManager(
                new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showLocationDetailActivity(Location item) {
        Intent intent = DetailLocationActivity.getStartIntent(getBaseActivity(),item);
        startActivity(intent);
    }

    @Override
    public void visibility_progressBar(boolean show) {
        if (show) {
            locationProgressBar.setVisibility(View.VISIBLE);
        } else {

            locationProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void error_load_List(int message) {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            locationProgressBar.setVisibility(View.GONE);


            erroreText.setText(getResources().getString(message));
        }

        errorBtnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errorLayout.setVisibility(View.GONE);
                locationProgressBar.setVisibility(View.VISIBLE);
                mPresenter.getLocationsList(category_id);

            }
        });
    }
}