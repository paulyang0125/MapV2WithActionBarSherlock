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



package old;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class NestedFrag extends SupportMapFragment {
	private static final String KEY_POSITION = "position";
	EditText inputLatitude, inputLongitude;
	private GoogleMap googleMap;
	private LatLng myLat;
	final static float DEFAULT_Latitude = (float) 40.75773;
	final static float DEFAULT_Longitude = (float) -73.985708;
	
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
	 
	 

}
