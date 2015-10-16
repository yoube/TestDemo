package com.wg.myviews;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wg.myviews.view.ScrollButton;
import com.wg.myviews.view.ToggleButton;

public class ToggleButActivity extends Activity {
	
	private ToggleButton togglebtn ;
	private ViewPager page;
	private ScrollButton scrollbtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toggle);
		togglebtn = (ToggleButton) findViewById(R.id.my_toggleBtn);
		scrollbtn = (ScrollButton) findViewById(R.id.my_scroll);
		page = (ViewPager) findViewById(R.id.view_page);
		page.setAdapter(new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				
				return arg0==arg1;
			}
			
			@Override
			public int getCount() {
				return 2;
			}
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				TextView view = new TextView(getApplicationContext());
				
				view.setText("View");
				container.addView(view);
				return view;
			}
		});
		page.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				scrollbtn.setScrollPaddLeft(arg1);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
				
			}
		});
		
	}

}
