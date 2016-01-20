package com.wg.myviews;

import com.wg.myviews.view.CircleProgressBar;
import com.wg.myviews.view.HorizontalProgressBarWithNumber;
import com.wg.myviews.view.MyProgressBar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MyViewActivity extends Activity {
	
	private MyProgressBar myBar;
	private CircleProgressBar myCiBar;
	private HorizontalProgressBarWithNumber myNumBar;
	private SeekBar seekBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_badu);
		myCiBar = (CircleProgressBar) findViewById(R.id.my_circle_view);
		myBar = (MyProgressBar) findViewById(R.id.my_scroll_view);
		myNumBar = (HorizontalProgressBarWithNumber) findViewById(R.id.my_numberprogress_view);
		seekBar = (SeekBar) findViewById(R.id.seekbar);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if(fromUser){
					myBar.setProgress(progress);
					myCiBar.setProgress(progress);
					myNumBar.setProgress(progress);
				}
			}
		});
	}

}
