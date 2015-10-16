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
	//�ؼ�����ͼƬ
	private Bitmap backgroundBitmap;
	//�ؼ�����ͼƬ
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
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		//��ʼ��λͼ
		backgroundBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.switch_background);
		slideBtn = BitmapFactory.decodeResource(getResources(),R.drawable.slide_button);

		if(curredStatus){
			slideeWidth = backgroundBitmap.getWidth()-slideBtn.getWidth();
		}

		
		//��ʼ������
		paint = new Paint();
		//���ÿ���ݹ���
		paint.setAntiAlias(true);
		//���õ������
		setOnClickListener(this);
	}

	/**
	 * ����View��С
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		/*
		 * ���õ�ǰview�Ĵ�С    (������Ϊ��λ)
		 * width �����ÿ�
		 * heigth�����ø�       
		 */
		setMeasuredDimension(backgroundBitmap.getWidth(),backgroundBitmap.getHeight());		
	}
	//ȷ�������е�λ��
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}
	/**
	 * ����view����
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		//������ͼƬ
		canvas.drawBitmap(backgroundBitmap, 0, 0, paint);
		//������
		canvas.drawBitmap(slideBtn, slideeWidth, 0, paint);
	}
	
	//�����¼�
	private float firstX;
	private float lastX;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//������ʱ
			firstX = lastX = event.getX();
			isClickd = true;
			break;
		case MotionEvent.ACTION_MOVE:
			//�ж��Ƿ��϶�,�����clink�¼��ĳ�ͻ
			if((Math.abs(firstX-event.getX()))>10){
				isClickd = false;
			}
			
			//���ƶ�ʱ
			int  dis = (int) (event.getX() - lastX); 
			lastX = event.getX();
			
			slideeWidth+=dis;
			flushView();
			break;
		case MotionEvent.ACTION_UP:
			//������ʱ
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

	//���õ���¼�
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
		//�������޲�ˢ��
		slideeWidth = slideeWidth<0?0:slideeWidth;
		
		slideeWidth = slideeWidth>slideWidthMax?slideWidthMax:slideeWidth;
			
		
		invalidate();//�ػ�ؼ�
	}
	
	public boolean getCurredStatus(){
		return curredStatus;
	}

}
