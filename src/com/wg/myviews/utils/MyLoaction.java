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
		option.setLocationMode(LocationMode.Hight_Accuracy);//���ö�λ���ȡ�ģʽ�кܶ�߾��ȣ��͹��ģ����豸��
		option.setCoorType("bd09ll");//���÷��صĶ�λ�������ϵ
		int span = 1100;
		option.setScanSpan(span);//���ö�λ��Ƶ�ʣ�0Ϊֻ��λһ�Ρ�1000����Ч
		option.setIsNeedAddress(true);//�Ƿ���Ҫ��ַ��Ϣ��Ĭ�Ϸ�
		option.setOpenGps(true);//�Ƿ���Ҫ��GPS
		option.setLocationNotify(true);//����GPS��Чʱÿ�����һ��GPS��Ϣ
		option.setIsNeedLocationDescribe(true);//�����Ƿ�����ת��
		option.setEnableSimulateGps(false);//��ѡ��Ĭ��false�������Ƿ���Ҫ����gps��������Ĭ����Ҫ
        
		mLocationClient.setLocOption(option);
	}
}
