package com.wg.myviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wg.myviews.viewGrup.MyScrollView;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {
	
	private List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Map<String,Object> 	map = new HashMap<String,Object>();
		map.put("name", "优酷菜单");
		map.put("class", YoukuActivity.class);
		listData.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "自定义开关按钮");
		map.put("class", ToggleButActivity.class);
		listData.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "圆形头像");
		map.put("class", CirImageActivity.class);
		listData.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "自定义ViewGrup");
		map.put("class", ScrollViewActivity.class);
		listData.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "Touch事件");
		map.put("class", TouchActivity.class);
		listData.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "jni");
		map.put("class", JniActivity.class);
		listData.add(map);
		
		
		setListAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Map<String,Object> map = listData.get(position);
				TextView v = (TextView) getLayoutInflater().from(getApplicationContext()).inflate(android.R.layout.simple_list_item_1, null);
				
				convertView = v;
				v.setText((String)map.get("name"));
				v.setTextColor(Color.BLACK);
				return convertView;
			}
			
			
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return listData.get(position);
			}
			
			@Override
			public int getCount() {
				return listData.size();
			}
		});
		
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String,Object> map = listData.get(position);
		startActivity(new Intent(this,(Class)map.get("class")));
	}

}
