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

import java.util.HashMap;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import com.example.paulgooglemaptest.R;

import com.example.paulgooglemaptest.component.SherlockMapFragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
//import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.paulgooglemaptest.component.Model;

;

public class InfoModelFragment extends SherlockMapFragment implements
		OnInfoWindowClickListener {
	// 25.034283,121.564769
	// 25.033612, 121.563859
	final static float DEFAULT_Latitude = (float) 25.033612;
	final static float DEFAULT_Longitude = (float) 121.563859;
	private static final String KEY_POSITION = "position";
	private GoogleMap map;
	private LatLng myLat;
	private HashMap<String, Model> models = new HashMap<String, Model>();

	public static InfoModelFragment newInstance(int position) {
		InfoModelFragment frag = new InfoModelFragment();
		Bundle args = new Bundle();

		args.putInt(KEY_POSITION, position);
		frag.setArguments(args);

		return (frag);

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bitmap enemy = BitmapFactory.decodeResource(getResources(),
				R.drawable.rsz_enemy);

		
		Model model = new Model(getActivity(),
				String.valueOf(R.string.build_101), 25.033612, 121.563859,
				R.string.build_101, R.string.build_101_snippet, enemy);

		models.put(model.getKey(), model);

		model = new Model(getActivity(),
				String.valueOf(R.string.build_101_parking_lot), 25.033719, 121.564062,
				R.string.build_101_parking_lot, R.string.build_101_parking_lot_snippet, enemy);
		
		models.put(model.getKey(), model);
		
		model = new Model(getActivity(),
				String.valueOf(R.string.world_trade), 25.033719, 121.564062,
				R.string.world_trade, R.string.world_trade_snippet, enemy);
		models.put(model.getKey(), model);
		
		

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View root = super.onCreateView(inflater, container, savedInstanceState);

		if (savedInstanceState == null) {
			map = getMap();
			// map.setMyLocationEnabled(true);
			myLat = new LatLng(DEFAULT_Latitude, DEFAULT_Longitude);

			CameraUpdate center = CameraUpdateFactory.newLatLng(myLat);
			CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
			map.moveCamera(center);
			map.animateCamera(zoom);

		}
		
//		// 101 25.033612,121.563859
//		addMarker(map, 25.033612, 121.563859, R.string.build_101,
//				R.string.build_101_snippet, enemy);
//		// Parking Lot 25.033719,121.564062
//
//		addMarker(map, 25.033719, 121.564062, R.string.build_101_parking_lot,
//				R.string.build_101_parking_lot_snippet, enemy);
//
//		// WT building 25.033875,121.562344
//
//		addMarker(map, 25.033875, 121.562344, R.string.world_trade,
//				R.string.world_trade_snippet, enemy);

		// put a model replace addMarker
		// public Model(Context ctxt, String key, double lat, double lon, int
		// title, int snippet, Bitmap icon)
		
		
		
		
		addMarkersFromModel(map);
		// addMarker(map, 40.70686417491799, -74.01572942733765,
		// R.string.downtown_club, R.string.heisman_trophy);

		map.setInfoWindowAdapter(new PopupAdapter(getActivity()
				.getLayoutInflater(), models));
		map.setOnInfoWindowClickListener(this);

		return (root);
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_LONG)
				.show();
	}

	private void addMarker(GoogleMap map, double lat, double lon, int title,
			int snippet, Bitmap myicon) {

		// LevelListDrawable d = (LevelListDrawable) getResources().getDrawable(
		// R.drawable.enemy);

		// d.setLevel(1234);
		// BitmapDrawable bd=(BitmapDrawable) d.getCurrent();

		// Bitmap icon =
		// BitmapFactory.decodeResource(getResources(),R.drawable.enemy);
		// Bitmap b=getResources().getDrawable(R.drawable.enemy);

		Bitmap icon = createIcon(myicon);

		// Bitmap bhalfsize=Bitmap.createScaledBitmap(icon,
		// icon.getWidth()/2,icon.getHeight()/2, false);

		map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
				.title(getString(title)).snippet(getString(snippet))
				.icon(BitmapDescriptorFactory.fromBitmap(icon)));

	}
	
	  private void addMarkersFromModel(GoogleMap map) {
		    for (Model model : models.values()) {
		      LatLng position=
		          new LatLng(model.getLatitude(), model.getLongitude());
		      
		      map.addMarker(new MarkerOptions().position(position)
		                                       .title(model.getTitle())
		                                       .snippet(model.getKey())
		                                       .draggable(true)
		                                       .icon(BitmapDescriptorFactory.fromBitmap(model.icon)));
		      								//This allows us to let the Maps V2 engine track the ID of the Marker and give us our key when we need it

		    }
		  }

	private Bitmap createIcon(Bitmap d) {
		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		Bitmap bmp = Bitmap.createBitmap(160, 160, conf);
		Canvas canvas1 = new Canvas(bmp);

		// paint defines the text color,
		// stroke width, size
		Paint color = new Paint();
		color.setTextSize(35);
		color.setColor(Color.BLUE);

		// modify canvas
		// canvas1.drawBitmap(BitmapFactory.decodeResource(getResources(),
		// R.drawable.rsz_enemy), 0,0, color);
		canvas1.drawBitmap(d, 0, 0, color);
		canvas1.drawText("Location!", 30, 40, color);

		return bmp;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(getActivity(),
				"Menu id  \"" + item.getItemId() + "\" clicked.",
				Toast.LENGTH_SHORT).show();

		if (item.getItemId() == R.id.action_bar_add_marker) {

			Bitmap droid = BitmapFactory.decodeResource(getResources(),
					R.drawable.droid_1);
			// 25.039474,121.568245, BELLAVITA, go shopping!!
			addMarker(map, 25.039474, 121.568245, R.string.d1,
					R.string.d1_snippet, droid);
			// 台北市議會, shitPlace 25.038502,121.562194

			addMarker(map, 25.033719, 121.564062, R.string.d2,
					R.string.d2_snippet, droid);

			// drawCompass(myLat, mAzimuth);
			return (true);
		}

		if (item.getItemId() == R.id.legal) {
			startActivity(new Intent(getActivity().getBaseContext(),
					com.example.paulgooglemaptest.LegalNoticesActivity.class));

			return (true);
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.marker_menu, menu);

	}

}
