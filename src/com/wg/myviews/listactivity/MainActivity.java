package com.wg.myviews.listactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wg.myviews.CirImageActivity;
import com.wg.myviews.JniActivity;
import com.wg.myviews.MapActivity;
import com.wg.myviews.ScrollViewActivity;
import com.wg.myviews.ToggleButActivity;
import com.wg.myviews.TouchActivity;

public class MainActivity extends BaseListActivity {
	
	@Override
	public void setListDate(List<Map<String, Object>> list) {
		Map<String,Object> 	map = new HashMap<String,Object>();
		map.put("name", "ImageLoader");
		map.put("class", ImageLoaderActivity.class);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "自定义开关按钮");
		map.put("class", ToggleButActivity.class);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "圆形头像");
		map.put("class", CirImageActivity.class);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "自定义ViewGrup");
		map.put("class", ScrollViewActivity.class);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "Touch事件");
		map.put("class", TouchActivity.class);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "jni");
		map.put("class", JniActivity.class);
		list.add(map);
		map = new HashMap<String,Object>();
		map.put("name", "百度地图");
		map.put("class", MapActivity.class);
		list.add(map);
	}

}
