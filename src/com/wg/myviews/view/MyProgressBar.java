package com.wg.myviews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyProgressBar extends View {
	private int height;
	private int width;
	private int maxInt;
	private int curredInt;
	private int curredProgress=10;
	private Paint bgPaint;
	private Paint curredPaint;

	public MyProgressBar(Context context) {
		super(context);
		init();
	}

	private void init() {
		height = 10;
		width = 500;
		bgPaint = new Paint();
		bgPaint.setColor(0xff84bf96);
		bgPaint.setAntiAlias(true);
		bgPaint.setStyle(Paint.Style.FILL);
		curredPaint = new Paint();
		curredPaint.setAntiAlias(true);
		curredPaint.setColor(0xff007b65);
		curredPaint.setStyle(Paint.Style.FILL);

	}

	public MyProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		 setMeasuredDimension(width, height);

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		 RectF rect1 = new RectF(0, 0, getMeasuredWidth(), height);
		 canvas.drawRoundRect(rect1, height/2,height/2, bgPaint);
		 RectF rect2 = new RectF(0, 0, (getMeasuredWidth()/100)*curredProgress, height);
		 canvas.drawRoundRect(rect2, height/2,height/2, curredPaint);
		
	}

	public void setProgress(int progress) {
		curredProgress = progress;
		invalidate();
	}

}
