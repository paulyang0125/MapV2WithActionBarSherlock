package old;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.paulgooglemaptest.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class StreetviewFragment extends SupportMapFragment implements
		OnClickListener {
	private static final String KEY_POSITION = "position";
	EditText inputLatitude, inputLongitude;
	// SupportMapFragment mapFrag;
	private GoogleMap googleMap;
	private GoogleMap map;
	private LatLng myLat;
	final static float DEFAULT_Latitude = (float) 40.75773;
	final static float DEFAULT_Longitude = (float) -73.985708;

	public static StreetviewFragment newInstance(int position) {
		StreetviewFragment mapFrag = new StreetviewFragment();
		Bundle args = new Bundle();

		args.putInt(KEY_POSITION, position);
		mapFrag.setArguments(args);

		return (mapFrag);
		// bundle.putInt("position", position);
		// bundle.putString("title", title);
		// fragment.setArguments(bundle);

	}

//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//
//		View result = inflater.inflate(R.layout.streetview_frag, container,
//				false);
//
////	 SupportMapFragment fragment = (SupportMapFragment)
////	getFragmentManager().findFragmentById(R.id.map);
//	
//	
//		inputLatitude = (EditText) result.findViewById(R.id.LatitudeInput);
//		inputLongitude = (EditText) result.findViewById(R.id.LongitudeInput);
//		SupportMapFragment fragment = (SupportMapFragment) getActivity()
//		.getSupportFragmentManager().findFragmentById(R.id.map);
//		setMapp(fragment.getMap());
//
//		Button btn = (Button) result.findViewById(R.id.streetview_button);
//		btn.setOnClickListener(this);
//
////		SupportMapFragment mapFrag =
////		 (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
//
//		map.setMyLocationEnabled(true);
//		 myLat = new LatLng(DEFAULT_Latitude, DEFAULT_Longitude);
//		 CameraUpdate center = CameraUpdateFactory.newLatLng(myLat);
//		 CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
//		 map.moveCamera(center);
//		 map.animateCamera(zoom);
//		 //googleMap.getUiSettings().setMyLocationButtonEnabled(true);
//		return (result);
//	}

	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	 Bundle savedInstanceState) {
	
	
	 View root = super.onCreateView(inflater, container, savedInstanceState);
	 googleMap = getMap();
	 googleMap.setMyLocationEnabled(true);
	 myLat = new LatLng(DEFAULT_Latitude, DEFAULT_Longitude);
	 CameraUpdate center = CameraUpdateFactory.newLatLng(myLat);
	 CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
	 googleMap.moveCamera(center);
	 googleMap.animateCamera(zoom);
	 //googleMap.getUiSettings().setMyLocationButtonEnabled(true);
	
	 return root;
	 }

	// @Override
	// public void onActivityCreated(Bundle savedInstanceState) {
	// map = mapFrag.getMap();
	//
	// myLat = new LatLng(DEFAULT_Latitude, DEFAULT_Longitude);
	// CameraUpdate center = CameraUpdateFactory.newLatLng(myLat);
	// CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
	// map.moveCamera(center);
	// map.animateCamera(zoom);
	//
	// }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	 public GoogleMap getMapp() {
	 return map;
	 }
	
	 public void setMapp(GoogleMap map) {
	 this.map = map;
	 }

	// @Override
	// public void onDestroyView() {
	// super.onDestroyView();
	// SupportMapFragment fragment = (SupportMapFragment)
	// (getFragmentManager().findFragmentById(R.id.map));
	// FragmentTransaction ft =
	// getActivity().getSupportFragmentManager().beginTransaction();
	// ft.remove(fragment);
	// ft.commit();
	// }
}
