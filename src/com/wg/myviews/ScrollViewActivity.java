package com.wg.myviews;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.wg.myviews.viewGrup.MyScrollView;

public class ScrollViewActivity extends Activity {

	private MyScrollView mMyScrollView;
	private int res[] = { R.drawable.dr1, R.drawable.dr2, R.drawable.dr3,R.drawable.ic_launcher };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myscroll);

		mMyScrollView = (MyScrollView) findViewById(R.id.my_scroll_view);

		for (int i = 0; i < res.length; i++) {

			ImageView view = new ImageView(getApplicationContext());
			view.setBackgroundResource(res[i]);
			mMyScrollView.addView(view);
		}

	}

}
