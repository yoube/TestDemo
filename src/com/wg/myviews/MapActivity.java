package com.wg.myviews;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.wg.myviews.utils.MyLoaction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MapActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化SDK 在之前
		SDKInitializer.initialize(getApplicationContext());

		setContentView(R.layout.activity_map_layout);
		initView();
	}

	private MapView mapView;
	private LocationClient location;
	private BaiduMap map;

	private void initView() {
		mapView = (MapView) findViewById(R.id.bmapView);
		map = mapView.getMap();
		map.setMyLocationEnabled(true);
		location = MyLoaction.getinstas();
		findViewById(R.id.action_start).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				location.start();
			}
		});
		;
		findViewById(R.id.action_stop).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				location.stop();
			}
		});
		;
		location.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation loc) {
				// loc.loa
				// 构造定位数据
				MyLocationData locData = new MyLocationData.Builder().accuracy(loc.getRadius()).direction(100)// 此处设置开发者获取到的方向信息，顺时针0-360
						.latitude(loc.getLatitude()).longitude(loc.getLongitude()).build();
				map.setMyLocationData(locData);

				LatLng ll = new LatLng(loc.getLatitude(), loc.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				map.animateMapStatus(u);

				// BitmapDescriptor mCurrentMarker =
				// BitmapDescriptorFactory.fromResource(R.drawable.channel1);
				// MyLocationConfiguration config = new
				// MyLocationConfiguration(com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.NORMAL,
				// true,
				// mCurrentMarker);
				// map.setMyLocationConfigeration(config);
				// 当不需要定位图层时关闭定位图层
				// map.setMyLocationEnabled(false);

				StringBuffer sb = new StringBuffer(256);
				sb.append("\n type :");
				sb.append(loc.getLocType());
				sb.append("\n time :");
				sb.append(loc.getTime());
				sb.append("\n Latitiude :");
				sb.append(loc.getLatitude());
				sb.append("\n loc.getLongitude() :");
				sb.append(loc.getLongitude());

				switch (loc.getLocType()) {
				case BDLocation.TypeGpsLocation:
					sb.append("\nspeed : ");
					sb.append(loc.getSpeed());// 单位：公里每小时
					sb.append("\nsatellite : ");
					sb.append(loc.getSatelliteNumber());
					sb.append("\nheight : ");
					sb.append(loc.getAltitude());// 单位：米
					sb.append("\ndirection : ");
					sb.append(loc.getDirection());// 单位度
					sb.append("\naddr : ");
					sb.append(loc.getAddrStr());
					sb.append("\ndescribe : ");
					sb.append("gps定位成功");
					break;
				case BDLocation.TypeNetWorkLocation:
					sb.append("\naddr : ");
					sb.append(loc.getAddrStr());
					// 运营商信息
					sb.append("\noperationers : ");
					sb.append(loc.getOperators());
					sb.append("\ndescribe : ");
					sb.append("网络定位成功");
					break;

				}
				Log.e(" static ", sb.toString());
			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

}
