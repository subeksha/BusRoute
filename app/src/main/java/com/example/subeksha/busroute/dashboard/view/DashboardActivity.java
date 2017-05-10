package com.example.subeksha.busroute.dashboard.view;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.subeksha.busroute.BaseActivity;
import com.example.subeksha.busroute.R;
import com.example.subeksha.busroute.dashboard.presenter.RouteImplementor;
import com.example.subeksha.busroute.dashboard.presenter.RoutePresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.CameraPosition;

/**
 * Created by Subeksha on 5/10/17.
 */

public class DashboardActivity extends BaseActivity implements RouteView{
    private RoutePresenter routePresenter;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        routePresenter = new RouteImplementor(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

    @Override
    public void onMapLoaded(GoogleMap googleMap) {
        routePresenter.plotMarkers();
        routePresenter.plotPath();
    }

    @Override
    public void updateScaleView(Projection projection, CameraPosition cameraPosition) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        routePresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        routePresenter.onDestroy();
    }

}
