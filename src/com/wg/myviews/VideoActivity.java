package com.wg.myviews;


import java.io.FileInputStream;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class VideoActivity extends Activity {
	MediaPlayer mp ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化SDK 在之前

//		setContentView(R.layout.activity_map_layout);

//		initView();
	}

	private void initView() {
		mp = new  MediaPlayer();
		
		FileInputStream input = null;
		try {
			mp.setDataSource(input.getFD());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
