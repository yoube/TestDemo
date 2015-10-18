package com.wg.myviews.listactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wg.myviews.ScrollViewActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BaseListActivity extends ListActivity {
	
	private List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListDate(listData);
		
		setListAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Map<String, Object> map = listData.get(position);
				TextView v = (TextView) getLayoutInflater().from(
						getApplicationContext()).inflate(
						android.R.layout.simple_list_item_1, null);

				convertView = v;
				v.setText((String) map.get("name"));
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
	
	public void setListDate(List<Map<String, Object>> list){
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> map = listData.get(position);
		startActivity(new Intent(this, (Class) map.get("class")));
	}

}
