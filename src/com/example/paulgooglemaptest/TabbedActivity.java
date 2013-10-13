package com.example.paulgooglemaptest;



import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class TabbedActivity extends AbstractMapActivity{

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
	
	
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.tabbed, menu);
//		return true;
//	}

}
