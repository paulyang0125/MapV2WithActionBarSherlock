package com.example.paulgooglemaptest.Fragment;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class DrawFragment extends SupportMapFragment {

	private static final String KEY_POSITION = "position";
	private GoogleMap map;
	private LatLng myLat;
	final static float DEFAULT_Latitude = (float) 40.75773;
	final static float DEFAULT_Longitude = (float) -73.985708;
	private float mAzimuth = 0.0f;
	private static final double EARTH_RADIUS = 6378100.0;

	public static DrawFragment newInstance(int position) {
		DrawFragment frag = new DrawFragment();
		Bundle args = new Bundle();

		args.putInt(KEY_POSITION, position);
		frag.setArguments(args);

		return (frag);

	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = super.onCreateView(inflater, container, savedInstanceState);
		map = getMap();
		map.setMyLocationEnabled(true);
		myLat = new LatLng(DEFAULT_Latitude, DEFAULT_Longitude);
		CameraUpdate center = CameraUpdateFactory.newLatLng(myLat);
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
		map.moveCamera(center);
		map.animateCamera(zoom);
		drawCompass(map, myLat, mAzimuth);
		return (root);
	}

	private void drawCompass(GoogleMap map, LatLng center, float azimuth) {
		int COMPASS_SIZE = 500;
		Projection proj = map.getProjection();
		Point xy_center = proj.toScreenLocation(center);
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
				+ compass_base_right.toString() + "left=" +compass_base_left.toString() ;
		
		Toast.makeText(getActivity(), Points2, Toast.LENGTH_LONG).show();
		
		
		
		
//		Log.d(getClass().getSimpleName(), String.format("%s:%s:%s",
//				compass_head.toString(),compass_base_right.toString(),
//				compass_base_left.toString()));

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

}
