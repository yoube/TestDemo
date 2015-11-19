package com.wg.myviews.listactivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wg.myviews.ImageLoaderListActivity;
import com.wg.myviews.ScrollViewActivity;

public class ImageLoaderActivity extends BaseListActivity {

	@Override
	public void setListDate(List<Map<String, Object>> list) {
		HashMap<String, Object> map;

		map = new HashMap<String, Object>();
		map.put("name", "ListView");
		map.put("class", ImageLoaderListActivity.class);
		list.add(map);
	}
}