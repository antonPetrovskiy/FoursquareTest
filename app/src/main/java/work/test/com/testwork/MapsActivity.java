package work.test.com.testwork;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import work.test.com.testwork.adapter.ViewPageAdapter;
import work.test.com.testwork.entity.Venues;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Venues> itemList;
    private ArrayList<Marker> markerList;
    private MapsPresenter presenter;
    private SupportMapFragment mapFragment;
    private boolean isShowing;
    private Animation hide;
    private Animation show;

    private ViewPager infoCards;
    private ViewPageAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapsInitializer.initialize(getApplicationContext());
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);



        mapFragment.getMapAsync(this);
        init();

    }

    public void init(){



        infoCards = findViewById(R.id.pagerView);
        infoCards.setVisibility(View.GONE);
        markerList = new ArrayList<>();
        hide = AnimationUtils.loadAnimation(this, R.anim.hide_bot);
        show = AnimationUtils.loadAnimation(this, R.anim.show_bot);

        MapsModel mapsModel = new MapsModel();
        presenter = new MapsPresenter(mapsModel);
        presenter.attachView(this);
        presenter.loadVenues();


    }

    public void setFocus(){
        if(isShowing) {
            CameraUpdate track = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.valueOf(itemList.get(infoCards.getCurrentItem()).getVenue().getLocation().getLat()), Double.valueOf(itemList.get(infoCards.getCurrentItem()).getVenue().getLocation().getLng())), 15, 0, 0));
            mMap.animateCamera(track);
            markerList.get(infoCards.getCurrentItem()).showInfoWindow();
        }
    }

    public void showVenues(List<Venues> venues){
        itemList = venues;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getApplicationContext().getResources().getDrawable(R.drawable.point);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 75, 75, false);
        for(int i = 0 ; i < venues.size(); i++){
            String s = venues.get(i).getVenue().getName();
            Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.valueOf(venues.get(i).getVenue().getLocation().getLat()), Double.valueOf(venues.get(i).getVenue().getLocation().getLng())))
                            .title(s).snippet("id"+i).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            markerList.add(marker);
            marker.setTag(venues.get(i).getVenue());

        }

        viewPagerAdapter = new ViewPageAdapter(this,venues);
        infoCards.setAdapter(viewPagerAdapter);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        try {
//            // Customise the styling of the base map using a JSON object defined
//            // in a raw resource file.
//            boolean success = googleMap.setMapStyle(
//                    MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));
//
//            if (!success) {
//                Log.e("Log", "Style parsing failed.");
//            }
//        } catch (Resources.NotFoundException e) {
//            Log.e("Log", "Can't find style. Error: ", e);
//        }
        LatLng me = new LatLng(50.4185, 30.5510);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.4185, 30.5510), 15));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(!isShowing){
                    isShowing=true;
                    infoCards.startAnimation(show);
                }
                infoCards.setVisibility(View.VISIBLE);
                infoCards.setCurrentItem(Integer.parseInt(marker.getSnippet().substring(2)));

                return false;
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(isShowing){
                    isShowing=false;
                    infoCards.startAnimation(hide);
                }else{
                    isShowing=true;
                    infoCards.startAnimation(show);
                }
            }
        });
    }




}
