/*
#                                                                                #
#  Copyright (c) 2013 Yao Nien, Yang, paulyang0125@gmail.com                     #  
#  Licensed under the Apache License, Version 2.0 (the "License"); you may not   #
#  use this file except in compliance with the License. You may obtain a copy    #
#  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required #
#  by applicable law or agreed to in writing, software distributed under the     #
#  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS  #
#  OF ANY KIND, either express or implied. See the License for the specific      #
#  language governing permissions and limitations under the License.             # 
#                                                                                #
*/

package com.example.paulgooglemaptest.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.paulgooglemaptest.R;
import com.example.paulgooglemaptest.TabbedActivity;
import com.example.paulgooglemaptest.component.SherlockMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class XYCoordinateFragment extends SherlockMapFragment implements
		OnTouchListener {

	private GoogleMap mMap;

	protected TabbedActivity activity;
	private FollowMeLocationSource followMeLocationSource;
	private Context mContext;
	private Projection proj;
	private Point xy_center;
	Toast toast;
	MarkerOptions options; 

	// Handler handler = new Handler();
	// Random random = new Random();
	// Runnable runner = new Runnable() {
	// @Override
	// public void run() {
	// setHasOptionsMenu(true);
	// }
	// };

	/*
	 * We need the Context in order to get a reference to the Location Manager
	 * (when instantiating this fragment from your activity use:
	 * PlaceMapFragment mapFragment = new PlaceMapFragment(this); )
	 */
	public XYCoordinateFragment(Context context) {
		this.mContext = context;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		activity = (TabbedActivity) getActivity();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// creates our custom LocationSource and initializes some of its members
		followMeLocationSource = new FollowMeLocationSource();
		setHasOptionsMenu(true);
		/*
		 * We can't be guaranteed that the map is available because Google Play
		 * services might not be available. (un-comment the following line when
		 * using this code in a FragmentActivity / Activity to try get a
		 * reference to the map here !)
		 */
		// setUpMapIfNeeded();
	}

	@Override
	public void onResume() {
		super.onResume();
		// ((TabbedActivity)activity).registerMyOnTouchListener(new
		// TabbedActivity.MyOnTouchListener() {
		//
		//
		//
		// @Override
		// public void onTouch(MotionEvent ev) {
		// // TODO Auto-generated method stub
		//
		// toast = Toast.makeText(getActivity(),
		// "Fragment1 got it",
		// Toast.LENGTH_SHORT);
		//
		// if (ev != null){
		// toast.show();
		//
		// }
		//
		// if(toast != null)
		// toast.cancel();
		// }
		// });

		/*
		 * We query for the best Location Provider everytime this fragment is
		 * displayed just in case a better provider might have become available
		 * since we last displayed it
		 */
		followMeLocationSource.getBestAvailableProvider();

		// Get a reference to the map/GoogleMap object

		if (followMeLocationSource.locationReady != false) {
			setUpMapIfNeeded();

		} else {

			Toast.makeText(getActivity(),
					"No avaiable GPS and network, pls wait a momoment",
					Toast.LENGTH_SHORT).show();

		}

		/*
		 * Enable the my-location layer (this causes our LocationSource to be
		 * automatically activated.) While enabled, the my-location layer
		 * continuously draws an indication of a user's current location and
		 * bearing, and displays UI controls that allow a user to interact with
		 * their location (for example, to enable or disable camera tracking of
		 * their location and bearing).
		 */
		mMap.setMyLocationEnabled(true);
		mMap.getUiSettings().setMyLocationButtonEnabled(false);
		mMap.setOnMapLongClickListener(new OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng point) {
				if (options == null) {
					options = new MarkerOptions()
							.anchor(0.5f, 0.5f)
							.position(point)
							.title("Marker")
							.draggable(true);
							//.icon(BitmapDescriptorFactory
							//		.fromResource(R.drawable.kruzok));
					mMap.addMarker(options);
					Toast.makeText(getActivity(),
							"my point:" + point.toString(),
							Toast.LENGTH_SHORT).show();
					
				} else {
					options.position(point);
					mMap.clear();
					mMap.addMarker(options);
					Toast.makeText(getActivity(),
							"my point:" + point.toString(),
							Toast.LENGTH_SHORT).show();
				}
				//new GetTask().execute(point);
			}
		});	

	}

	@Override
	public void onPause() {
		/*
		 * Disable the my-location layer (this causes our LocationSource to be
		 * automatically deactivated.)
		 */
		mMap.setMyLocationEnabled(false);

		super.onPause();
	}

	/**
	 * Sets up the map if it is possible to do so (i.e., the Google Play
	 * services APK is correctly installed) and the map has not already been
	 * instantiated. This will ensure that we only ever manipulate the map once
	 * when it {@link #mMap} is not null.
	 * <p>
	 * If it isn't installed {@link SupportMapFragment} (and
	 * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt
	 * for the user to install/update the Google Play services APK on their
	 * device.
	 */
	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			mMap = getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				// The Map is verified. It is now safe to manipulate the map:

				// Replace the (default) location source of the my-location
				// layer with our custom LocationSource
				mMap.setLocationSource(followMeLocationSource);

				// Set default zoom
				mMap.moveCamera(CameraUpdateFactory.zoomTo(15f));

				final View mapView = getView();
				mapView.getViewTreeObserver().addOnGlobalLayoutListener(
						new OnGlobalLayoutListener() {

							@Override
							public void onGlobalLayout() {
								// TODO Auto-generated method stub
								mapView.getViewTreeObserver()
										.removeOnGlobalLayoutListener(this);
								proj = mMap.getProjection();

							}

						});

			}
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.location_menu, menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Toast.makeText(getActivity(),
		// "Menu id  \"" + item.getItemId() + "\" clicked.",
		// Toast.LENGTH_SHORT).show();

		if (item.getItemId() == R.id.show_my_location) {

			xy_center = proj
					.toScreenLocation(followMeLocationSource.myPreviousLoc);
			Toast.makeText(
					getActivity(),
					"my location:"
							+ followMeLocationSource.myPreviousLoc.toString()
							+ "xy:" + xy_center.toString(), Toast.LENGTH_SHORT)
					.show();

			// Log.i(TAG, "my location:" + myPreviousLoc.toString() + "xy:" +
			// xy_center.toString());

			return (true);
		}

		if (item.getItemId() == R.id.legal) {
			startActivity(new Intent(getActivity().getBaseContext(),
					com.example.paulgooglemaptest.LegalNoticesActivity.class));

			return (true);
		}

		return super.onOptionsItemSelected(item);
	}

	/*
	 * Our custom LocationSource. We register this class to receive location
	 * updates from the Location Manager and for that reason we need to also
	 * implement the LocationListener interface.
	 */
	private class FollowMeLocationSource implements LocationSource,
			LocationListener {

		private OnLocationChangedListener mListener;
		private LocationManager locationManager;
		private final Criteria criteria = new Criteria();
		private String bestAvailableProvider;
		/*
		 * Updates are restricted to one every 10 seconds, and only when
		 * movement of more than 10 meters has been detected.
		 */
		private final int minTime = 10000; // minimum time interval between
											// location updates, in milliseconds
		private final int minDistance = 10; // minimum distance between location
											// updates, in meters
		private boolean locationReady;
		private LatLng myPreviousLoc;

		private FollowMeLocationSource() {
			// Get reference to Location Manager
			locationManager = (LocationManager) mContext
					.getSystemService(Context.LOCATION_SERVICE);

			// Specify Location Provider criteria
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			criteria.setPowerRequirement(Criteria.POWER_LOW);
			criteria.setAltitudeRequired(true);
			criteria.setBearingRequired(true);
			criteria.setSpeedRequired(true);
			criteria.setCostAllowed(true);
		}

		private void getBestAvailableProvider() {
			/*
			 * The preffered way of specifying the location provider (e.g. GPS,
			 * NETWORK) to use is to ask the Location Manager for the one that
			 * best satisfies our criteria. By passing the 'true' boolean we ask
			 * for the best available (enabled) provider.
			 */
			bestAvailableProvider = locationManager.getBestProvider(criteria,
					true);
			locationReady = true;
		}

		/*
		 * Activates this provider. This provider will notify the supplied
		 * listener periodically, until you call deactivate(). This method is
		 * automatically invoked by enabling my-location layer.
		 */
		@Override
		public void activate(OnLocationChangedListener listener) {
			// We need to keep a reference to my-location layer's listener so we
			// can push forward
			// location updates to it when we receive them from Location
			// Manager.
			mListener = listener;

			// Request location updates from Location Manager
			if (bestAvailableProvider != null) {
				locationManager.requestLocationUpdates(bestAvailableProvider,
						minTime, minDistance, this);
			} else {
				locationReady = false;
				// (Display a message/dialog) No Location Providers currently
				// available.
			}
		}

		/*
		 * Deactivates this provider. This method is automatically invoked by
		 * disabling my-location layer.
		 */
		@Override
		public void deactivate() {
			// Remove location updates from Location Manager
			locationManager.removeUpdates(this);

			mListener = null;
		}

		@Override
		public void onLocationChanged(Location location) {
			/*
			 * Push location updates to the registered listener.. (this ensures
			 * that my-location layer will set the blue dot at the new/received
			 * location)
			 */
			if (mListener != null) {
				mListener.onLocationChanged(location);
			}

			myPreviousLoc = new LatLng(location.getLatitude(),
					location.getLongitude());

			/*
			 * ..and Animate camera to center on that location ! (the reason for
			 * we created this custom Location Source !)
			 */
			mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(
					location.getLatitude(), location.getLongitude())));
		}

		@Override
		public void onStatusChanged(String s, int i, Bundle bundle) {

		}

		@Override
		public void onProviderEnabled(String s) {

		}

		@Override
		public void onProviderDisabled(String s) {

		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "Fragment1 got it", Toast.LENGTH_SHORT)
				.show();
		return true;
	}

}
