package com.wg.myviews.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.wg.myviews.R;

public class ToggleButton extends View implements OnClickListener{
	//控件背景图片
	private Bitmap backgroundBitmap;
	//控件滑动图片
	private Bitmap slideBtn;
	private Paint paint;
	private boolean curredStatus = false;
	private float slideeWidth;
	
	

	public ToggleButton(Context context) {
		super(context);
	
	}

	public ToggleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		curredStatus = attrs.getAttributeBooleanValue("http://schemas.android.com/apk/res/com.wg.myviews","endable",false);
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		//初始化位图
		backgroundBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.switch_background);
		slideBtn = BitmapFactory.decodeResource(getResources(),R.drawable.slide_button);

		if(curredStatus){
			slideeWidth = backgroundBitmap.getWidth()-slideBtn.getWidth();
		}

		
		//初始化画笔
		paint = new Paint();
		//设置抗锯齿功能
		paint.setAntiAlias(true);
		//设置点击监听
		setOnClickListener(this);
	}

	/**
	 * 测量View大小
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		/*
		 * 设置当前view的大小    (以像素为单位)
		 * width ：设置宽
		 * heigth：设置高       
		 */
		setMeasuredDimension(backgroundBitmap.getWidth(),backgroundBitmap.getHeight());		
	}
	//确定父类中的位置
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}
	/**
	 * 绘制view内容
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		//画背景图片
		canvas.drawBitmap(backgroundBitmap, 0, 0, paint);
		//画滑块
		canvas.drawBitmap(slideBtn, slideeWidth, 0, paint);
	}
	
	//滑动事件
	private float firstX;
	private float lastX;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//当按下时
			firstX = lastX = event.getX();
			isClickd = true;
			break;
		case MotionEvent.ACTION_MOVE:
			//判断是否拖动,解决与clink事件的冲突
			if((Math.abs(firstX-event.getX()))>10){
				isClickd = false;
			}
			
			//当移动时
			int  dis = (int) (event.getX() - lastX); 
			lastX = event.getX();
			
			slideeWidth+=dis;
			flushView();
			break;
		case MotionEvent.ACTION_UP:
			//当弹起时
			float max = backgroundBitmap.getWidth()-slideBtn.getWidth();
			if(slideeWidth>(max/2)){
				slideeWidth = backgroundBitmap.getWidth()-slideBtn.getWidth();
			}else{
				slideeWidth = 0;
			}
			
			flushView();
			break;

		}
		
		return true;
		
		
		
	}

	//设置点击事件
	private boolean isClickd ;
	@Override
	public void onClick(View v) {
		if (isClickd){
			
			curredStatus = !curredStatus;
			if(curredStatus){
				slideeWidth = backgroundBitmap.getWidth()-slideBtn.getWidth();
			}else{
				slideeWidth = 0;
			}
			flushView();
		}
//		Toast.makeText(getContext(),""+getCurredStatus(),Toast.LENGTH_SHORT).show();
		
	}
	
	private void flushView(){
		float slideWidthMax = backgroundBitmap.getWidth()-slideBtn.getWidth();
		//超出界限不刷新
		slideeWidth = slideeWidth<0?0:slideeWidth;
		
		slideeWidth = slideeWidth>slideWidthMax?slideWidthMax:slideeWidth;
			
		
		invalidate();//重绘控件
	}
	
	public boolean getCurredStatus(){
		return curredStatus;
	}

}
