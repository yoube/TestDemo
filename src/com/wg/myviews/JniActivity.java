package com.wg.myviews;

import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class JniActivity extends Activity implements OnClickListener{
	
	
	private Button btn;
	
	private Button addBtn;
	private Button addStrBtn;
	private Button changeArrBtn;
	//C 返回一个字符串
	public native String fromC();
	
	//C 进行两个数相加
	public native int add(int x,int y);
	
	//c 拼接字符串
	public native String addString(String string);
	
	//c 设置数组值
	public native int[] setValue(int[] array);
	
	//加载第三方库
	static{
		System.loadLibrary("hello-jni");//参照Andorid.mk文件
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jni);
		
		btn = (Button) findViewById(R.id.jni_btn);
		btn.setOnClickListener(this);

		addBtn = (Button) findViewById(R.id.jni_add_btn);
		addBtn.setOnClickListener(this);

		addStrBtn = (Button) findViewById(R.id.jni_add_str_btn);
		addStrBtn.setOnClickListener(this);

		changeArrBtn = (Button) findViewById(R.id.jni_change_arr_btn);
		changeArrBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jni_btn:
			String string = fromC();
			Toast.makeText(getApplicationContext(), string,Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.jni_add_btn:
			
			int i = add(3,5);
			Toast.makeText(getApplicationContext(), "3+5="+i,Toast.LENGTH_SHORT).show();
			break;
		case R.id.jni_add_str_btn:
			
			string = addString("hello ");
			Toast.makeText(getApplicationContext(), string,Toast.LENGTH_SHORT).show();
			break;
		case R.id.jni_change_arr_btn:
			
			int[] oldArr = new int[]{1,2,3,4,5};
			int[] newArr = setValue(new int[]{1,2,3,4,5});
			Toast.makeText(getApplicationContext(), "oldArray:"+Arrays.toString(oldArr)+"newArray:"+Arrays.toString(newArr),Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		
	}

}
