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

package com.example.paulgooglemaptest.component;

import android.content.Context;
import android.graphics.Bitmap;

public class Model {
  String key;
  String title;
  String snippet;
  double lat;
  double lon;
  public Bitmap icon;

  public Model(Context ctxt, String key, double lat, double lon, int title,
        int snippet, Bitmap icon) {
    this.key=key;
    this.title=ctxt.getString(title);
    this.snippet=ctxt.getString(snippet);
    this.lat=lat;
    this.lon=lon;
    this.icon=icon;
  }

  public String getKey() {
    return(key);
  }
  
  public Bitmap getIcon() {
	    return(icon);
	  }

  public String getTitle() {
    return(title);
  }

  public String getSnippet() {
    return(snippet);
  }

  public double getLatitude() {
    return(lat);
  }

  public double getLongitude() {
    return(lon);
  }
}
