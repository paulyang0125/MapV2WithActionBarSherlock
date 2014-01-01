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

import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.paulgooglemaptest.R;
import com.example.paulgooglemaptest.component.SherlockMapFragment;

public class SupportMapFragmentWithMenu extends SherlockMapFragment {

	Handler handler = new Handler();
	Random random = new Random();
	Runnable runner = new Runnable() {
        @Override
        public void run() {
            setHasOptionsMenu(true);
        }
    };
    
    public static SupportMapFragmentWithMenu newInstance(int position,String title) {
    	SupportMapFragmentWithMenu fragment = new SupportMapFragmentWithMenu();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		handler.postDelayed(runner, random.nextInt(2000));
		//setHasOptionsMenu(true);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.simple_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(getActivity(),
				"Menu id  \"" + item.getItemId() + "\" clicked.",
				Toast.LENGTH_SHORT).show();
		return true;
	}
}
