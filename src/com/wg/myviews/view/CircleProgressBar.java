package com.wg.myviews.view;

import com.wg.myviews.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class CircleProgressBar extends View {
	private int height;
	private int width;
	private int maxInt;
	private int curredInt;
	private int curredProgress;
	private Paint bgPaint;
	private Paint curredPaint, textPaint;
	private float progressWidth;
	private int paintSizee;
	private float textSize; 

	public CircleProgressBar(Context context) {
		this(context,null);
	}

	private void init() {
		bgPaint = new Paint();
		bgPaint.setColor(0xff84bf96);
		bgPaint.setStyle(Paint.Style.STROKE);
		bgPaint.setStrokeWidth(progressWidth);
		bgPaint.setAntiAlias(true);
		curredPaint = new Paint();
		curredPaint.setAntiAlias(true);
		curredPaint.setStyle(Paint.Style.STROKE);
		curredPaint.setStrokeWidth(progressWidth);
		curredPaint.setColor(0xff007b65);

		
		textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(textSize); 
		textPaint.setColor(0xffed1941);

	}
	
	public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		//设置属性
		TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.circleProgressBar,defStyleAttr,0);

		textSize = typeArray.getDimensionPixelSize(R.styleable.circleProgressBar_textSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics()));
		progressWidth = typeArray.getDimensionPixelSize(R.styleable.circleProgressBar_progressWidth, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 10, getResources().getDisplayMetrics()));

		init();
	
	}

	public CircleProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		 super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		 int defSize = 150;
		 //设置宽度
		 int mode = MeasureSpec.getMode(widthMeasureSpec);
		 int size = MeasureSpec.getSize(widthMeasureSpec);
		 
		 if(mode==MeasureSpec.EXACTLY){//match_parent
			 width = size;
			 
		 }else if(mode == MeasureSpec.AT_MOST){//wrap_content
			 width = defSize;
		 }else{
			 width = size;
		 }
		 //设置高度
		 mode = MeasureSpec.getMode(heightMeasureSpec);
		 size = MeasureSpec.getSize(heightMeasureSpec);
		 
		 if(mode==MeasureSpec.EXACTLY){//match_parent
			 height = size;
			 
		 }else if(mode == MeasureSpec.AT_MOST){//wrap_content
			 height = defSize;
		 }else{
			 height = size;
		 }
		 
		setMeasuredDimension(width, height);
		 
//		  height = getMeasuredHeight();
//		 width = getMeasuredWidth();
		 
		 
		 paintSizee = Math.min(width, height);
		

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		RectF oval = new RectF(0 + progressWidth, 0 + progressWidth, paintSizee - progressWidth, paintSizee - progressWidth);
		canvas.drawArc(oval, 0, (360f / 100) * curredProgress, false, curredPaint);
		
		String text = curredProgress+"%";
		

		Rect rect = new Rect();
		textPaint.getTextBounds(text, 0, text.length(), rect);//获取文字的矩形范围！可以获取高度和宽度！
		float textWidth = textPaint.measureText(text) ;// 测量文字的宽度！
		textPaint.setTextAlign(Align.CENTER);
		canvas.drawText(text, (paintSizee / 2), ((paintSizee / 2) + (rect.height() / 2)), textPaint);

	}

	public void setProgress(int progress) {
		curredProgress = progress;
		invalidate();
	}

}
