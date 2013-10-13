package com.example.paulgooglemaptest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.paulgooglemaptest.R;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class InfoModelFragment extends SupportMapFragment implements OnInfoWindowClickListener {
	final static float DEFAULT_Latitude = (float) 40.75773;
	final static float DEFAULT_Longitude = (float) -73.985708;
	private static final String KEY_POSITION = "position";
	private GoogleMap map;
	private LatLng myLat;

	public static InfoModelFragment newInstance(int position) {
		InfoModelFragment frag = new InfoModelFragment();
		Bundle args = new Bundle();

		args.putInt(KEY_POSITION, position);
		frag.setArguments(args);

		return (frag);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = super.onCreateView(inflater, container, savedInstanceState);

		if (savedInstanceState == null) {
			map = getMap();
			map.setMyLocationEnabled(true);
			myLat = new LatLng(DEFAULT_Latitude, DEFAULT_Longitude);
			CameraUpdate center = CameraUpdateFactory.newLatLng(myLat);
			CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
			map.moveCamera(center);
			map.animateCamera(zoom);

		}

		// 101 25.033612,121.563859
		addMarker(map, 25.033612, 121.563859, R.string.build_101,
				R.string.build_101_snippet);
		// Parking Lot 25.033719,121.564062

		addMarker(map, 25.033719, 121.564062, R.string.build_101_parking_lot,
				R.string.build_101_parking_lot_snippet);

		// WT building 25.033875,121.562344

		addMarker(map, 25.033875, 121.562344, R.string.world_trade,
				R.string.world_trade_snippet);

		// addMarker(map, 40.70686417491799, -74.01572942733765,
		// R.string.downtown_club, R.string.heisman_trophy);

		map.setInfoWindowAdapter(new PopupAdapter(getActivity().getLayoutInflater()));
		map.setOnInfoWindowClickListener(this);

		return (root);
	}
	
	  @Override
	  public void onInfoWindowClick(Marker marker) {
	    Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_LONG).show();
	  }

	private void addMarker(GoogleMap map, double lat, double lon, int title,
			int snippet) {

		map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
				.title(getString(title)).snippet(getString(snippet))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.enemy)));

	}

}
