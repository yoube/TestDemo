package com.wg.myviews;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.wg.myviews.view.MyProgressBar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

public class BaduPushActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_badu);
		PushManager.startWork(getApplicationContext(),PushConstants.LOGIN_TYPE_API_KEY, "lL2bk5LOWp89KxseXiWbD7gM");
		
	}
	

}
