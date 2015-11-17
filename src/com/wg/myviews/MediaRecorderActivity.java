package com.wg.myviews;

import com.wg.myviews.view.AudioButton;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MediaRecorderActivity extends Activity{
	private ListView list;
	private AudioButton action;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_recorder);
		initView();
	}
	ArrayAdapter<String> adapter;
	private void initView(){
		list= (ListView) findViewById(R.id.list);
		adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String path = adapter.getItem(position);
				MediaRecorderManager.getInstall().play(path);
			}
		});
		adapter.add("/storage/emulated/0/KuwoMusic/music/愿得一人心-李行亮.aac");
		list.setAdapter(adapter);
		action = (AudioButton) findViewById(R.id.action_btn);
		
		action.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					MediaRecorderManager.getInstall().start();
					break;
				case MotionEvent.ACTION_MOVE:
					
					break;
				case MotionEvent.ACTION_UP:
					String path = MediaRecorderManager.getInstall().stop();
					adapter.add(path);
					break;

				}
				return false;
			}
		});
	}

}
