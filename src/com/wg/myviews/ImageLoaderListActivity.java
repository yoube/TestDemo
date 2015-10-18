package com.wg.myviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.wg.myviews.utils.Constants;

public class ImageLoaderListActivity extends ListActivity{
	
	private List<String> listData = new ArrayList<String>();
	DisplayImageOptions options;
	ImageLoader imgLoader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		listData.addAll(Arrays.asList(Constants.IMG_PATH));
		imgLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
									.showStubImage(R.drawable.ic_launcher)
									.cacheOnDisk(true)//…Ë÷√¥≈≈Ãª∫¥Ê
									.cacheInMemory(true)//…Ë÷√ƒ⁄¥Êª∫¥Ê
									.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
		imgLoader.init(config);
		setListAdapter(adapter);
	}
	class Holder {
		ImageView img;
		TextView text;
	}
	private BaseAdapter adapter = new BaseAdapter() {
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String path = listData.get(position);
			Holder hoder =null;
			if(convertView ==null){
				hoder = new Holder();
				convertView =  getLayoutInflater().from(
						getApplicationContext()).inflate(
								R.layout.activity_image_loader_list_item, null);
				hoder.img = (ImageView) convertView.findViewById(R.id.imgloader_list_item_img);
				hoder.text = (TextView) convertView.findViewById(R.id.imgloader_list_item_text);
				convertView.setTag(hoder);
			}else{
				hoder = (Holder) convertView.getTag();
			}
			hoder.text.setText(path);
			imgLoader.displayImage(path,hoder.img, options);
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
	};

}
