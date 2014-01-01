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

import java.util.Random;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Toast;

//import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;




import com.example.paulgooglemaptest.R;
import com.example.paulgooglemaptest.component.SherlockMapFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class DrawFragment extends SherlockMapFragment {
	private final String TAG = this.getClass().getSimpleName();
	private static final String KEY_POSITION = "position";
	private GoogleMap map;
	private LatLng myLat;
	private Projection proj;
	final static float DEFAULT_Latitude = (float) 40.75773;
	final static float DEFAULT_Longitude = (float) -73.985708;
	private float mAzimuth = 0.0f;
	private static final double EARTH_RADIUS = 6378100.0;

	
	Handler handler = new Handler();
	Random random = new Random();
	Runnable runner = new Runnable() {
        @Override
        public void run() {
            setHasOptionsMenu(true);
        }
    };


	public static DrawFragment newInstance(int position) {
		DrawFragment frag = new DrawFragment();
		Bundle args = new Bundle();

		args.putInt(KEY_POSITION, position);
		frag.setArguments(args);

		return (frag);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		handler.postDelayed(runner, random.nextInt(2000));

		View root = super.onCreateView(inflater, container, savedInstanceState);

		map = getMap();
		myLat = new LatLng(DEFAULT_Latitude, DEFAULT_Longitude);
		map.setMyLocationEnabled(true);
		if (savedInstanceState == null) {
			CameraUpdate center = CameraUpdateFactory.newLatLng(myLat);
			CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
			map.moveCamera(center);
			map.animateCamera(zoom);
		}
		// proj = map.getProjection();

		// map = getMap();
		// map.setMyLocationEnabled(true);
		//
		// CameraUpdate center = CameraUpdateFactory.newLatLng(myLat);
		// final View mapView = getView();
		// if (mapView.getViewTreeObserver().isAlive()) {
		// mapView.getViewTreeObserver().addOnGlobalLayoutListener(new
		// OnGlobalLayoutListener() {
		// public void onGlobalLayout() {
		// // remove the listener
		// // ! before Jelly Bean:
		// //mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
		// // ! for Jelly Bean and later:
		// //mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
		// // set map viewport
		// // CENTER is LatLng object with the center of the map
		// map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLat, 15));
		// // ! you can query Projection object here
		// Point markerScreenPosition =
		// map.getProjection().toScreenLocation(myLat);
		// // ! example output in my test code: (356, 483)
		// System.out.println(markerScreenPosition);
		// }
		// });
		// }

		// myLat = new LatLng(DEFAULT_Latitude, DEFAULT_Longitude);
		// CameraUpdate center = CameraUpdateFactory.newLatLng(myLat);
		// CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
		// map.moveCamera(center);
		// map.animateCamera(zoom);

		// drawCompass(map, myLat, mAzimuth);

		return (root);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);
		final View mapView = getView();
		mapView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// TODO Auto-generated method stub
						mapView.getViewTreeObserver()
								.removeOnGlobalLayoutListener(this);
						proj = map.getProjection();
						

					}

				});

	}

	@Override
	public void onResume() {
		super.onResume();
		// if (proj != null) {
		// drawCompass(myLat, mAzimuth);
		// }
		Log.i(TAG, "onResume");
	}

	private void drawCompass(LatLng center, float azimuth) {
		int COMPASS_SIZE = 500;
		// Projection proj = map.getProjection();

		Point xy_center = proj.toScreenLocation(center);

		Log.i(TAG, "xy_center:" + xy_center.toString());

		double azimuth_rad = Math.PI * azimuth / 180;
		String Points = "points=" + xy_center + "," + "LatLng="
				+ center.toString();
		Toast.makeText(getActivity(), Points, Toast.LENGTH_LONG).show();

		map.addCircle(new CircleOptions().center(center).radius(COMPASS_SIZE)
				.strokeColor(Color.RED));

		int pixelRadiu = convertMetersToPixels(center, COMPASS_SIZE, proj);

		float compass_head_x = (float) (xy_center.x + pixelRadiu
				* Math.cos(-Math.PI / 2 + azimuth_rad));

		float compass_head_y = (float) (xy_center.y + pixelRadiu
				* Math.sin(-Math.PI / 2 + azimuth_rad));

		float compass_base_right_x = (float) (xy_center.x + pixelRadiu
				* Math.cos(Math.PI / 3 + azimuth_rad));

		float compass_base_right_y = (float) (xy_center.y + pixelRadiu
				* Math.sin(Math.PI / 3 + azimuth_rad));

		float compass_base_left_x = (float) (xy_center.x + pixelRadiu
				* Math.cos(Math.PI * 2 / 3 + azimuth_rad));

		float compass_base_left_y = (float) (xy_center.y + pixelRadiu
				* Math.sin(Math.PI * 2 / 3 + azimuth_rad));

		Point compass_head = new Point((int) compass_head_x,
				(int) compass_head_y);
		Point compass_base_right = new Point((int) compass_base_right_x,
				(int) compass_base_right_y);
		Point compass_base_left = new Point((int) compass_base_left_x,
				(int) compass_base_left_y);

		String Points2 = "head=" + compass_head.toString() + "," + "right="
				+ compass_base_right.toString() + "left="
				+ compass_base_left.toString();

		Toast.makeText(getActivity(), Points2, Toast.LENGTH_LONG).show();

		// Log.d(getClass().getSimpleName(), String.format("%s:%s:%s",
		// compass_head.toString(),compass_base_right.toString(),
		// compass_base_left.toString()));

		// proj.fromScreenLocation(p);
		PolylineOptions line = new PolylineOptions()
				.add(proj.fromScreenLocation(compass_head),
						proj.fromScreenLocation(compass_base_right),
						proj.fromScreenLocation(compass_base_left),
						proj.fromScreenLocation(compass_head)).width(5)
				.color(Color.BLUE);
		map.addPolyline(line);

	}

	// 2. convert meters to pixels between 2 points in current zoom:

	private int convertMetersToPixels(LatLng center, int radiusInMeters,
			Projection proj) {

		double lat1 = radiusInMeters / EARTH_RADIUS;
		double lng1 = radiusInMeters
				/ (EARTH_RADIUS * Math.cos((Math.PI * center.latitude / 180)));

		double lat2 = center.latitude + lat1 * 180 / Math.PI;
		double lng2 = center.longitude + lng1 * 180 / Math.PI;

		Point p1 = proj.toScreenLocation(center);
		Point p2 = proj.toScreenLocation(new LatLng(lat2, lng2));

		return Math.abs(p1.x - p2.x);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(getActivity(),
				"Menu id  \"" + item.getItemId() + "\" clicked.",
				Toast.LENGTH_SHORT).show();
		
		if (item.getItemId() == R.id.action_bar_draw) {
			
			drawCompass(myLat, mAzimuth);
			return(true);
		}
		
		
		 if (item.getItemId() == R.id.legal) {
		      startActivity(new Intent(getActivity().getBaseContext(), com.example.paulgooglemaptest.LegalNoticesActivity.class));

		      return(true);
		    }

		    return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.drawmenu, menu);
		
		
	}

	
}
