package com.example.subeksha.busroute.dashboard.view;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.CameraPosition;

/**
 * Created by Subeksha on 5/10/17.
 */

public interface RouteView {
    void onMapLoaded(GoogleMap googleMap);

    void updateScaleView(Projection projection, CameraPosition cameraPosition);
}
