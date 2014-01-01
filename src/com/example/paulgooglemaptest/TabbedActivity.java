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





import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;


public class TabbedActivity extends AbstractMapActivity{
	//private ArrayList<MyOnTouchListener> onTouchListeners = new  ArrayList<MyOnTouchListener>(10);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
			
		   if (readyToGo()) {
			      setContentView(R.layout.activity_tabbed);

			      ViewPager pager=(ViewPager)findViewById(R.id.pager);

			      pager.setAdapter(buildAdapter());
			    }
		//setContentView(R.layout.activity_tabbed);
	}

	
	 private PagerAdapter buildAdapter() {
		    return(new MapPageAdapter(this, getSupportFragmentManager()));
		  }
	
	 
//	 @Override
//	 public boolean dispatchTouchEvent(MotionEvent ev) {
//	     for (MyOnTouchListener listener : onTouchListeners) {
//	         listener.onTouch(ev);
//	     }
//	     return super.dispatchTouchEvent(ev);
//	 }
//	 public void registerMyOnTouchListener(MyOnTouchListener listener){
//	     onTouchListeners.add(listener);
//	 }
//	 public interface MyOnTouchListener {
//	     public void onTouch(MotionEvent ev);
//	 }
	
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.tabbed, menu);
//		return true;
//	}

}
