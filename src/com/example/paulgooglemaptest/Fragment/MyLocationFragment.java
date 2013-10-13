package com.example.paulgooglemaptest.Fragment;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulgooglemaptest.TabbedActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MyLocationFragment extends SupportMapFragment implements
LocationSource, OnMyLocationChangeListener {
	

    protected TabbedActivity activity;
    //private FollowMeLocationSource followMeLocationSource;
    private Context mContext;
    
	private static final String KEY_POSITION = "position";
	private GoogleMap map = null;
	private OnLocationChangedListener mapLocationListener = null;
	private LocationManager locMgr = null;
	private Criteria crit = new Criteria();
	final static float DEFAULT_Latitude = (float) 40.75773;
	final static float DEFAULT_Longitude = (float) -73.985708;

	
	
	public MyLocationFragment(Context context) {
        this.mContext = context;
    }
	
	 @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);

	        activity = (TabbedActivity)getActivity();
	    }
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        map = getMap();
	        
	        
	        if (savedInstanceState == null) {
	            CameraUpdate center=
	                CameraUpdateFactory.newLatLng(new LatLng( DEFAULT_Latitude,
	                		DEFAULT_Longitude));
	            CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

	            map.moveCamera(center);
	            map.animateCamera(zoom);
	          }
	        
	        
			locMgr = (LocationManager)activity.getSystemService(Context.LOCATION_SERVICE);
			crit.setAccuracy(Criteria.ACCURACY_FINE);
			 map.setMyLocationEnabled(true);
		     map.getUiSettings().setMyLocationButtonEnabled(false);
	        
	        
	    

	    }
	
	
	
	
	

	@Override
	public void onMyLocationChange(Location lastKnownLocation) {
		// TODO Auto-generated method stub

		Log.d(getClass().getSimpleName(), String.format("%f:%f",
				lastKnownLocation.getLatitude(),
				lastKnownLocation.getLongitude()));

	}

	@Override
	public void onResume() {
		super.onResume();

//		locMgr.requestLocationUpdates(0L, 0.0f, crit, this, null);
		map.setLocationSource(this);
	}

	@Override
	public void onPause() {
		map.setLocationSource(null);
//		locMgr.removeUpdates(this);

		super.onPause();
	}

	@Override
	public void activate(OnLocationChangedListener listener) {
		// TODO Auto-generated method stub
		this.mapLocationListener=listener;
		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		this.mapLocationListener=null;
	}
	
	public void onLocationChanged(Location location) {
	    if (mapLocationListener != null) {
	      mapLocationListener.onLocationChanged(location);

	      LatLng latlng=
	          new LatLng(location.getLatitude(), location.getLongitude());
	      CameraUpdate cu=CameraUpdateFactory.newLatLng(latlng);

	      map.animateCamera(cu);
	    }
	  }
	
	  
}
