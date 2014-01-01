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


package com.example.paulgooglemaptest;

import com.example.paulgooglemaptest.Fragment.DefaultFragment;
import com.example.paulgooglemaptest.Fragment.DrawFragment;
import com.example.paulgooglemaptest.Fragment.InfoModelFragment;
import com.example.paulgooglemaptest.Fragment.PlaceMapFragment;
import com.example.paulgooglemaptest.Fragment.StreetviewFragment;
import com.example.paulgooglemaptest.Fragment.SupportMapFragmentWithMenu;
import com.example.paulgooglemaptest.Fragment.XYCoordinateFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

//import com.example.paulgooglemaptest.Fragment.StreetviewFragment;
//import com.google.android.gms.maps.SupportMapFragment;

public class MapPageAdapter extends FragmentStatePagerAdapter {
	Context ctxt = null;

	private final String[] TITLES = { "Street view", "Draw a poly",
			"Show makers", "Show home", "XY coordinate detect", "Animation" };

	public MapPageAdapter(Context ctxt, FragmentManager mgr) {
		super(mgr);
		this.ctxt = ctxt;
	}

	@Override
	public int getCount() {
		return TITLES.length;
		// return (10);
	}

	@Override
	public Fragment getItem(int position) {
		if (position == 0) {
			return StreetviewFragment.newInstance(position);
		} else if (position == 1) {
			return DrawFragment.newInstance(position);
		} else if (position == 2) {
			return InfoModelFragment.newInstance(position);
		} else if (position == 3) {
			return (new PlaceMapFragment(ctxt));
		} else if (position == 4) {
			return (new XYCoordinateFragment(ctxt));
		} else {
			// return doNothingFrang.newInstance(position);
			return (new DefaultFragment());
		}

		//return(DrawFragment.newInstance(position));
		//return InfoModelFragment.newInstance(position);
		//return (new XYCoordinateFragment(ctxt));
	//return SupportMapFragmentWithMenu.newInstance(position, "Fragment with menu");

	}

	// @Override
	// public String getPageTitle(int position) {
	// return(ctxt.getString(R.string.map_page_title) + String.valueOf(position
	// + 1));
	// }
	@Override
	public CharSequence getPageTitle(int position) {
		return (TITLES[position] + String.valueOf(position + 1));
	}
}