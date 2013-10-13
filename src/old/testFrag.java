package old;

import com.example.paulgooglemaptest.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class testFrag extends Fragment {
	private static final String KEY_POSITION = "position";
	EditText inputLatitude, inputLongitude;
	private GoogleMap map;
	private LatLng myLat;
	final static float DEFAULT_Latitude = (float) 40.75773;
	final static float DEFAULT_Longitude = (float) -73.985708;
	
	public static testFrag newInstance(int position) {
		testFrag mapFrag = new testFrag();
		Bundle args = new Bundle();

		args.putInt(KEY_POSITION, position);
		mapFrag.setArguments(args);

		return (mapFrag);


	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View result = inflater.inflate(R.layout.streetview_frag, container,
				false);

		inputLatitude = (EditText) result.findViewById(R.id.LatitudeInput);
		inputLongitude = (EditText) result.findViewById(R.id.LongitudeInput);

		Button btn = (Button) result.findViewById(R.id.streetview_button);
		
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SupportMapFragment videoFragment = new NestedFrag();
				// we get the 'childFragmentManager' for our transaction
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				// make the back button return to the main screen
				// and supply the tag 'left' to the backstack
				transaction.addToBackStack("left");
				// add our new nested fragment
				transaction.add(getId(), videoFragment, "left");
				// commit the transaction
				transaction.commit();
			}
		});
		
		

		return (result);
	}
	

	
	

}
