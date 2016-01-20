package com.wg.myviews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class HorizontalProgressBarWithNumber extends View {

	private static final int DEFAULT_TEXT_SIZE = 10;
	private static final int DEFAULT_TEXT_COLOR = 0xff008792;
	private static final int DEFAULT_MAX_PROGRESS_COLOR = 0xff50b7c1;

	private int height;
	private int width;
	private int curredInt;
	private int progressheight;
	private int curredProgress = 10;
	private Paint bgPaint, textPaint;
	private Paint curredPaint;

	public HorizontalProgressBarWithNumber(Context context) {
		this(context, null);
		init();
	}

	private void init() {
		bgPaint = new Paint();
		bgPaint.setColor(DEFAULT_MAX_PROGRESS_COLOR);
		bgPaint.setAntiAlias(true);
		bgPaint.setStyle(Paint.Style.FILL);
		curredPaint = new Paint();
		curredPaint.setAntiAlias(true);
		curredPaint.setColor(DEFAULT_TEXT_COLOR);
		curredPaint.setStyle(Paint.Style.FILL);

		textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(DEFAULT_TEXT_SIZE);
		textPaint.setColor(DEFAULT_TEXT_COLOR);

	}

	public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		init();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		if (heightMode != MeasureSpec.EXACTLY) {

			float textHeight = (textPaint.descent() + textPaint.ascent());// 获取字体的实际显示的高度
																			// ------参照字体方面的知识！
			int exceptHeight = (int) (getPaddingTop() + getPaddingBottom()
					+ Math.max(progressheight, Math.abs(textHeight)));

			heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight, MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		width = getMeasuredWidth();
		height = getMeasuredHeight();
		Log.e("height", "" + height);

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		Log.e("getHeight() ", "" + getHeight());

		canvas.translate(getPaddingLeft(), getHeight() / 2);
		
		
		
		
		
//		RectF rect1 = new RectF(0, 0, getMeasuredWidth(), height);
//		canvas.drawRoundRect(rect1, height / 2, height / 2, bgPaint);

//		canvas.drawText(text, x, y, textPaint);
//		RectF rect2 = new RectF(0, 0, (getMeasuredWidth() / 100) * curredProgress, height);
//		canvas.drawRoundRect(rect2, height / 2, height / 2, curredPaint);

	}

	public void setProgress(int progress) {
		curredProgress = progress;
		invalidate();
	}

}
