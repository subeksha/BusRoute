package com.example.subeksha.busroute.dashboard.presenter;

import com.google.android.gms.maps.model.Polyline;

/**
 * Created by Subeksha on 5/10/17.
 */

public interface RoutePresenter extends BasePresenter {
    void onPolylineClick(Polyline polyline);

    void plotPath();

    void plotMarkers();

}
