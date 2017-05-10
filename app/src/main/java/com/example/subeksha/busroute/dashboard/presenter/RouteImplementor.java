package com.example.subeksha.busroute.dashboard.presenter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.example.subeksha.busroute.BaseActivity;
import com.example.subeksha.busroute.R;
import com.example.subeksha.busroute.dashboard.view.RouteView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Subeksha on 5/10/17.
 */

public class RouteImplementor implements RoutePresenter, OnMapReadyCallback,
        GoogleMap.OnMapLoadedCallback, GoogleMap.OnCameraMoveListener,
        GoogleMap.OnPolylineClickListener {

    private SupportMapFragment mapFrag;
    private GoogleMap googleMap;
    private Activity activity;
    private RouteView view;
    private List<Marker> markers;
    private List<LatLng> LatLngs;


    public RouteImplementor(BaseActivity activity) {
        this.activity = activity;
        if (activity instanceof RouteView) {
            this.view = (RouteView) activity;
        } else {
            throw new IllegalArgumentException("Must implement MapView class.");
        }

        mapFrag = (SupportMapFragment) activity.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setMapToolbarEnabled(false);

        googleMap.setOnMapLoadedCallback(this);
        googleMap.setOnCameraMoveListener(this);

    }

    @Override
    public void onMapLoaded() {
        view.onMapLoaded(googleMap);
    }

    @Override
    public void onCameraMove() {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }

    @Override
    public void plotPath() {
        googleMap.addPolyline(new PolylineOptions()
                .addAll(LatLngs)
                .color(ContextCompat.getColor(activity, R.color.accent))
                .geodesic(true)
                .clickable(true)
                .width(10f));
        googleMap.setOnPolylineClickListener(this);
    }

    @Override
    public void plotMarkers() {
        markers = new ArrayList<>();
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (MarkerOptions markerOptions : getRouteMarkers()) {
            markers.add(googleMap.addMarker(markerOptions));
            boundsBuilder.include(markerOptions.getPosition());
        }
        LatLngBounds bounds = boundsBuilder.build();
        googleMap.moveCamera(CameraUpdateFactory
                .newLatLngBounds(bounds, 200));
    }

    /**
     * Dummy contents-------------->
     */
    private List<LatLng> getCoordinates() {
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(28.2918072, 83.869586));
        latLngs.add(new LatLng(28.3119894, 83.8237738));
        latLngs.add(new LatLng(28.3718068, 83.8248333));
        latLngs.add(new LatLng(28.3718068, 83.8248333));
        latLngs.add(new LatLng(28.4208056, 83.8102769));
        latLngs.add(new LatLng(28.4973827, 83.8960379));
        latLngs.add(new LatLng(28.5266036, 83.9054793));
        latLngs.add(new LatLng(28.5304141, 83.8758804));
        return latLngs;
    }

    private List<MarkerOptions> getRouteMarkers() {
        List<LatLng> latLngs = getCoordinates();
        MarkerOptions phediMarkerOptions = new MarkerOptions().position(latLngs.get(0)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_)).title("Phedi");
        MarkerOptions dhampusMarkerOptions = new MarkerOptions().position(latLngs.get(1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_)).title("Dhampus");
        MarkerOptions landrukMarkerOptions = new MarkerOptions().position(latLngs.get(2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_)).title("Landruk");
        MarkerOptions jhinuDandaMarkerOptions = new MarkerOptions().position(latLngs.get(3)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_)).title("Jhinu Danda");
        MarkerOptions chhomrongMarkerOptions = new MarkerOptions().position(latLngs.get(4)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_)).title("Chhomrong");
        MarkerOptions deuraliMarkerOptions = new MarkerOptions().position(latLngs.get(5)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_)).title("Deurali Camp");
        MarkerOptions mbcMarkerOptions = new MarkerOptions().position(latLngs.get(6)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_)).title("Machhapuchhre Base Camp");
        MarkerOptions abcMarkerOptions = new MarkerOptions().position(latLngs.get(7)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_)).title("Annapurna Base Camp");

        List<MarkerOptions> list = new ArrayList<>();
        list.add(phediMarkerOptions);
        list.add(dhampusMarkerOptions);
        list.add(landrukMarkerOptions);
        list.add(jhinuDandaMarkerOptions);
        list.add(chhomrongMarkerOptions);
        list.add(deuraliMarkerOptions);
        list.add(mbcMarkerOptions);
        list.add(abcMarkerOptions);

        return list;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

}
