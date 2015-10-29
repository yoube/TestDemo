package com.wg.myviews.utils;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

import android.content.Context;

public class MyLoaction {
	
	public static LocationClient mLocationClient;
	public static LocationClient getinstas(){
		if(mLocationClient==null){
			
		}
		return mLocationClient;
	}
	public static void init(Context context){
		mLocationClient = new LocationClient(context);
		
		LocationClientOption option =  new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位精度。模式有很多高精度，低功耗，仅设备；
		option.setCoorType("bd09ll");//设置返回的定位结果坐标系
		int span = 1100;
		option.setScanSpan(span);//设置定位的频率，0为只定位一次。1000才有效
		option.setIsNeedAddress(true);//是否需要地址信息，默认否
		option.setOpenGps(true);//是否需要打开GPS
		option.setLocationNotify(true);//设置GPS有效时每秒输出一次GPS信息
		option.setIsNeedLocationDescribe(true);//设置是否语义转换
		option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        
		mLocationClient.setLocOption(option);
	}
}
