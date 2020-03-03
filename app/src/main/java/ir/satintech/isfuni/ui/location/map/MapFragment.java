package ir.satintech.isfuni.ui.location.map;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
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



public class MapFragment extends BaseFragment implements
        MapMvpView, MapAdapter.Callback, OnMapReadyCallback, CustomScrollListener.Callback {

    private GoogleMap mMap;

    @Inject
    MapMvpPresenter<MapMvpView> mPresenter;


    List<Location> items;
    @BindView(R.id.location_list)
    RecyclerView locationList;
    Unbinder unbinder;
    LinearLayoutManager Lmanager;
    /////////
    Long category_id;
    HashMap<String, Marker> map_HashMap_markers = new HashMap<>();

    float zoomlevel = 18;
    int Yoffset = 300;

    ////
    public static MapFragment newInstance(Long category_id) {


        MapFragment fragment = new MapFragment();
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
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, v));
            mPresenter.onAttach(this);

        }


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        unbinder = ButterKnife.bind(this, v);
        return v;
    }


    @Override
    protected void setUp(View view) {

        category_id = getArguments().getLong("category_id", 1);
        mPresenter.getLocationsList(category_id);
        Yoffset = 300;


    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
        unbinder.unbind();


    }


    @Override
    public void setListLocations(List<Location> list) {


        items = list;

        MapAdapter adapter = new MapAdapter(list, getBaseActivity());
        adapter.setCallback(this);
        locationList.setAdapter(adapter);


        Lmanager = new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.HORIZONTAL, true);
        locationList.setLayoutManager(Lmanager);


        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.END);
        snapHelperStart.attachToRecyclerView(locationList);


    }

    @Override
    public void showLocationsDetailActivity(Location item) {
        Intent intent = DetailLocationActivity.getStartIntent(getBaseActivity(), item);
        startActivity(intent);
    }


    @Override
    public void onItemClick(Location item) {
        mPresenter.showLocationDetailActivity(item);
    }


    public void changeOffsetCenter(double latitude, double longitude) {
        Point mappoint = mMap.getProjection().toScreenLocation(new LatLng(latitude, longitude));
        mappoint.set(mappoint.x, mappoint.y + Yoffset); // change these values as you need , just hard coded a value if you want you can give it based on a ratio like using DisplayMetrics  as well
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mMap.getProjection().fromScreenLocation(mappoint), zoomlevel));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng isfahan = new LatLng(32.622279, 51.660197);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(isfahan, 18));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                marker.showInfoWindow();
                return true;
            }
        });


        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                zoomlevel = cameraPosition.zoom;
            }
        });
        if (items != null && items.size() > 0) {

            for (int i = 0; i < items.size(); i++) {
                LatLng location = new LatLng(items.get(i).getLat(), items.get(i).getLog());

                add_marker(items.get(i).getName(), mMap.addMarker(new MarkerOptions().position(location).title(items.get(i).getName())));

                if (i == 0) {

                    changeOffsetCenter(location.latitude, location.longitude);
                    map_HashMap_markers.get(items.get(i).getName()).showInfoWindow();
                }

            }

            CustomScrollListener scrollListener = new CustomScrollListener(Lmanager);
            scrollListener.setCallback(this);

            locationList.addOnScrollListener(scrollListener);

        } else {


            add_marker("دانشگاه اصفهان", mMap.addMarker(new MarkerOptions().position(isfahan).title("دانشگاه اصفهان")));
            changeOffsetCenter(isfahan.latitude, isfahan.longitude);

        }

    }

    @Override
    public void onItemSelected(int VisibleItemPosition) {

        if (items != null) {
            Location selected_item = items.get(VisibleItemPosition);

            LatLng location = new LatLng(selected_item.getLat(), selected_item.getLog());

            changeOffsetCenter(location.latitude, location.longitude);

            map_HashMap_markers.get(selected_item.getName()).showInfoWindow();

        }
    }


    public void add_marker(String name, Marker marker) {
        map_HashMap_markers.put(name, marker);

    }


}