package com.wg.myviews;

import java.util.List;

import com.baidu.android.pushservice.PushMessageReceiver;

import android.content.Context;
import android.util.Log;

public class MyPushMessageReceiver extends PushMessageReceiver{

	@Override
	public void onBind(Context arg0, int arg1, String arg2, String arg3, String arg4, String arg5) {
		Log.e("onBind", "arg0"+arg0 +" arg1 "+ arg1+""+" arg2 "+ arg2+""+" arg3 "+ arg3+""+" arg4 "+ arg4+" arg5 "+ arg5+"");
		
	}

	@Override
	public void onDelTags(Context arg0, int arg1, List<String> arg2, List<String> arg3, String arg4) {
		Log.e("onDelTags", "arg0"+arg0 +" arg1"+ arg1+""+" arg2"+ arg2+""+" arg3"+ arg3+""+" arg4"+ arg4);
		
	}

	@Override
	public void onListTags(Context arg0, int arg1, List<String> arg2, String arg3) {
		Log.e("onListTags", "arg0"+arg0 +" arg1"+ arg1+""+" arg2"+ arg2+""+" arg3"+ arg3);
		
	}

	@Override
	public void onMessage(Context arg0, String arg1, String arg2) {
		Log.e("onMessage", "arg0"+arg0 +" arg1"+ arg1+""+" arg2"+ arg2);
		
	}

	@Override
	public void onNotificationArrived(Context arg0, String arg1, String arg2, String arg3) {
		Log.e("onNotificationArrived", "arg0"+arg0 +" arg1"+ arg1+""+" arg2"+ arg2+""+" arg3"+ arg3);
		
	}

	@Override
	public void onNotificationClicked(Context arg0, String arg1, String arg2, String arg3) {
		Log.e("onNotificationClicked", "arg0"+arg0 +" arg1"+ arg1+""+" arg2"+ arg2+""+" arg3"+ arg3);
		
	}

	@Override
	public void onSetTags(Context arg0, int arg1, List<String> arg2, List<String> arg3, String arg4) {
		Log.e("onSetTags", "arg0"+arg0 +" arg1"+ arg1+""+" arg2"+ arg2+""+" arg3"+ arg3+""+" arg4"+ arg4);
		
	}

	@Override
	public void onUnbind(Context arg0, int arg1, String arg2) {
		Log.e("onUnbind", "arg0"+arg0 +" arg1"+ arg1+""+" arg2"+ arg2);
		
	}

}
