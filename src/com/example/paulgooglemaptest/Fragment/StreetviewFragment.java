package com.example.paulgooglemaptest.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paulgooglemaptest.R;
import com.example.paulgooglemaptest.TabbedActivity;

public class StreetviewFragment extends Fragment {
	private static final String KEY_POSITION = "position";
	EditText inputLatitude, inputLongitude;

	public static StreetviewFragment newInstance(int position) {
		StreetviewFragment mapFrag = new StreetviewFragment();
		Bundle args = new Bundle();

		args.putInt(KEY_POSITION, position);
		mapFrag.setArguments(args);

		return (mapFrag);

	}

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

				String lat = inputLatitude.getText().toString();
				String lon = inputLongitude.getText().toString();
				if (v.getId() == R.id.streetview_button && lat != null
						&& lon != null) {

					String strUri = "google.streetview:cbll=" + lat + "," + lon;
					Toast.makeText((TabbedActivity)getActivity(), strUri, Toast.LENGTH_LONG)
							.show();
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(strUri));
					startActivity(intent);

				}
			}
		});

		return (result);
	}

}
