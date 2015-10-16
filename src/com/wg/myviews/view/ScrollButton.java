package com.wg.myviews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ScrollButton extends View {

	Paint paint ;
	private int width;
	private int heigth;
	private int scrollPaddLeft;
	private int scrollPaddRight;
	public ScrollButton(Context context) {
		super(context);
	}

	public ScrollButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
		
	}
	
	private void initView() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		
		width = 400;
		heigth = 70;
		
		scrollPaddRight = width/2+10;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	
		setMeasuredDimension(width,heigth);
		
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		
		//绘制圆角矩形
		/*设置渐变色
		LinearGradient gradient = new LinearGradient(0,heigth/2, 
				width, heigth/2, 
				Color.RED,Color.BLACK, 
				Shader.TileMode.CLAMP);
		paint.setShader(gradient);*/
		
		paint.setARGB(0xFF,0, 0, 0);
		RectF rect = new RectF();
		rect.left = 0;
		rect.top = 0;
		rect.right = width;
		rect.bottom = heigth;
		
		int r = getHeight()/2;
		canvas.drawRoundRect(rect
				, r//设置X轴上的半径
				, r//设置Y轴上的半径
				,paint);
		
		
		paint.setARGB(0xFF,0xEA, 00, 00);
		rect.left = 2;
		rect.top = 2;
		rect.right = width-2;
		rect.bottom = heigth-2;
		
		canvas.drawRoundRect(rect
				, r//设置X轴上的半径
				, r//设置Y轴上的半径
				,paint);
		
		
		
		
		
		//绘制滑块
		paint.setARGB(0xFF,0xCB, 00, 00);
		rect.left = scrollPaddLeft+7;
		rect.top = 0+7;
		rect.right = scrollPaddRight-7;
		rect.bottom = heigth-7;
		
		canvas.drawRoundRect(rect, r, r, paint);
		
		paint.setARGB(0xFF,00, 00, 00);
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(35);
		canvas.drawText("最新", width/2/2, heigth/2+10, paint);
		paint.setARGB(0xFF,0xFF, 0xFF, 0xFF);
		canvas.drawText("最热", (width/2)+width/2/2, heigth/2+10, paint);
	}
	private float firstX;
	private float lastX;
/*	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
	
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			firstX = lastX = event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			
			float f = lastX- event.getX();
			
			lastX = event.getX();
			
			scrollPaddLeft -=f;
			scrollPaddRight -=f;
			
			finshView();
			break;
		case MotionEvent.ACTION_UP:
			
			break;
		}
		
		return true;
	}
*/	private void finshView(){
		scrollPaddLeft = scrollPaddLeft<0?0:scrollPaddLeft;
		scrollPaddLeft = scrollPaddLeft>width-(width/2+10)?width-(width/2+10):scrollPaddLeft;
		
		scrollPaddRight = scrollPaddRight<(width/2+10)?(width/2+10):scrollPaddRight;
		scrollPaddRight = scrollPaddRight>width?width:scrollPaddRight;
		
		

		invalidate();
	}
	public void  setScrollPaddLeft(float f){
		
		if(f<=0)return ;
		float max = width-width/2+10;
		Log.w("wwww","max: "+max);
		Log.w("wwww","f: "+f);
		
		scrollPaddLeft = (int) (max*f);
		
		scrollPaddRight = (int) (max*f)+width/2+10;
		
		finshView();
	}
	

}
