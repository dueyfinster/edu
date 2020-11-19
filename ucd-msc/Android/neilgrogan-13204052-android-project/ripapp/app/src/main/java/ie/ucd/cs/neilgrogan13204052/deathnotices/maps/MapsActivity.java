package ie.ucd.cs.neilgrogan13204052.deathnotices.maps;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ie.ucd.cs.neilgrogan13204052.deathnotices.R;
import ie.ucd.cs.neilgrogan13204052.deathnotices.database.NoticeORM;
import ie.ucd.cs.neilgrogan13204052.deathnotices.model.Notice;
import ie.ucd.cs.neilgrogan13204052.deathnotices.notices.DeathNoticeDetailFragment;
import ie.ucd.cs.neilgrogan13204052.deathnotices.notices.DeathNoticeListFragment;
import ie.ucd.cs.neilgrogan13204052.deathnotices.search.SearchSpecificActivity;

public class MapsActivity extends FragmentActivity {

    List<Notice> deathNoticeList;
    private boolean showOnlyOneNotice;

    private static final String TAG = "MapsActvity";
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mymap))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                Bundle bundleObject = getIntent().getExtras();

                if(bundleObject != null){
                    deathNoticeList = (List<Notice>) bundleObject.getSerializable(DeathNoticeDetailFragment.SHOW_ON_MAP);
                    showOnlyOneNotice = true;
                }else{
                    showOnlyOneNotice = false;
                    deathNoticeList = NoticeORM.getDeathNotices(getBaseContext());
                    if(deathNoticeList.isEmpty()){
                        //DeathNoticeListFragment.NoticeFetcher fetcher = new DeathNoticeListFragment.NoticeFetcher();
                        //fetcher.execute();
                        deathNoticeList = NoticeORM.getDeathNotices(getBaseContext());
                    }

                }
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        for(Notice notice : deathNoticeList){
            try {
                double lat = Double.valueOf(notice.getLat());
                double lon = Double.valueOf(notice.getLon());
                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(notice.getFirst_name() + " " + notice.getLast_name()));
            }catch(Exception e){
             Log.v(TAG, notice.toString() + " can't be added due to: " + e.getMessage());
            }
       }
        if(showOnlyOneNotice){
            Notice notice = deathNoticeList.get(0);
            double lat = Double.valueOf(notice.getLat());
            double lon = Double.valueOf(notice.getLon());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(lat, lon), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(lat, lon))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }else{
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            if (location != null)
            {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(location.getLatitude(), location.getLongitude()), 13));

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                        .zoom(7)                   // Sets the zoom
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }
        }

        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
