package com.wg.myviews.listactivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wg.myviews.CirImageActivity;
import com.wg.myviews.JniActivity;
import com.wg.myviews.MapActivity;
import com.wg.myviews.MediaRecorderActivity;
import com.wg.myviews.MediaRecorderVideoActivity2;
import com.wg.myviews.ScrollViewActivity;
import com.wg.myviews.ToggleButActivity;
import com.wg.myviews.TouchActivity;
import com.wg.myviews.VideoActivity;

public class MainActivity extends BaseListActivity {
	
	@Override
	public void setListDate(List<Map<String, Object>> list) {
		Map<String,Object> 	map = new HashMap<String,Object>();
		map.put("name", "ImageLoader");
		map.put("class", ImageLoaderActivity.class);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "�Զ��忪�ذ�ť");
		map.put("class", ToggleButActivity.class);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "Բ��ͷ��");
		map.put("class", CirImageActivity.class);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "�Զ���ViewGrup");
		map.put("class", ScrollViewActivity.class);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "Touch�¼�");
		map.put("class", TouchActivity.class);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("name", "jni");
		map.put("class", JniActivity.class);
		list.add(map);
		map = new HashMap<String,Object>();
		map.put("name", "�ٶȵ�ͼ");
		map.put("class", MapActivity.class);
		list.add(map);
		map = new HashMap<String,Object>();
		map.put("name", "Video");
		map.put("class", VideoActivity.class);
		list.add(map);
		map = new HashMap<String,Object>();
		map.put("name", "¼��");
		map.put("class", MediaRecorderActivity.class);
		list.add(map);
		map = new HashMap<String,Object>();
		map.put("name", "¼��");
		map.put("class", MediaRecorderActivity.class);
		list.add(map);
		map = new HashMap<String,Object>();
		map.put("name", "��Ƶ¼��");
		map.put("class", MediaRecorderVideoActivity2.class);
		list.add(map);
	}

}
