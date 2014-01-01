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


import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulgooglemaptest.component.Model;
import com.example.paulgooglemaptest.R;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

class PopupAdapter implements InfoWindowAdapter {
  LayoutInflater inflater=null;
  HashMap<String, Model> models=null;
  

  PopupAdapter(LayoutInflater inflater, HashMap<String, Model> models) {
    this.inflater=inflater;
    this.models=models;
  }

  @Override
  public View getInfoWindow(Marker marker) {
    return(null);
  }

  @Override
  public View getInfoContents(Marker marker) {
	  
    View popup=inflater.inflate(R.layout.popup, null);

    
    if (marker != null && popup!= null){
 
    	TextView tv=(TextView)popup.findViewById(R.id.title);
    tv.setText(marker.getTitle());
 
    tv=(TextView)popup.findViewById(R.id.snippet);
    
    //tv.setText(marker.getSnippet());
    tv.setText(models.get(marker.getSnippet()).getSnippet());

    return(popup);
	  }
    //Toast.makeText(, "pop is null", Toast.LENGTH_LONG)
	//.show();
    return(popup);
  }
}