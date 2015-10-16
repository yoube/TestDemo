package com.wg.myviews.viewGrup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.wg.myviews.utils.MyScroller;

public class MyScrollView extends ViewGroup {

	private Context mContext;

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
//		SystemClock.uptimeMillis()
		initView();
	}

	private void initView() {

		// ʵ�����¼���������
		detector = new GestureDetector(mContext, new OnGestureListener() {// ���ü���

					@Override
					public boolean onSingleTapUp(MotionEvent e) {
						// ������ʱ����
						System.out.print("up");
						return false;
					}

					@Override
					public void onShowPress(MotionEvent e) {

					}

					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
						// ����ָ����
						scrollBy((int) distanceX, 0);
						return false;
					}

					@Override
					public void onLongPress(MotionEvent e) {
						// ������ʱ�ص�

					}

					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						// �����ٻ���ʱ�ص�
						return false;
					}

					@Override
					public boolean onDown(MotionEvent e) {
						// ����ָ����ʱ
						return false;
					}
				});

//		myScrolller = new MyScroller(mContext);
		//ϵͳ��Scroller
		myScrolller = new Scroller(mContext);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View view = getChildAt(i);
//			Log.w("wwww","i-->H�� "+view.getHeight());
			view.layout(getWidth() * i, 0, getWidth() * (i + 1), getHeight());
		}

	}

	/**
	 * ���Խ������ƹ����� �������� �����������ٻ���
	 */
	private GestureDetector detector;
	private int curredIndex;
	private float firstX;
	private float lastX;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		
//		detector.onTouchEvent(event);//ʹ�ù���������¼�	
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			//������ʱ
			firstX = lastX =  event.getX();
			
			break;
		case MotionEvent.ACTION_MOVE:
			//���ƶ�ʱ
			int temp = (int) (lastX - event.getX());
			lastX = event.getX();
			
			scrollBy(temp,0);
			
			break;
		case MotionEvent.ACTION_UP:
			//������ʱ
			float scroll = firstX-event.getX();
			Log.w("wwww","firstX : "+firstX);
			
			if(scroll>0){
				if(scroll>getWidth()/2){
					curredIndex++;
				}
			}else{
				
				if(Math.abs(scroll)>getWidth()/2){
					curredIndex--;
				}
			}
			
			//�ƶ���ĳ��
			moveTo(0);
			
			break;

		}
		
		return true;
		
	}
	/**
	 * �ƶ�������
	 */
	private Scroller myScrolller;
	
	public void moveTo(int nexId){
		//�жϱ߽�
		
		if(curredIndex<0){
			curredIndex = 0;
		}else if(curredIndex>=getChildCount()){
			
			curredIndex= getChildCount()-1;
		}
		
		//˲���ƶ���ĳ����
//		scrollTo(curredIndex*getWidth(), 0);
		
		//�����ƶ���ĳ����
		int temp = (int) (curredIndex*getWidth()-getScrollX());//�����ƶ��ľ���
		myScrolller.startScroll(getScrollX(),0,temp,0);
		
		invalidate();//ˢ��View
	}
	/**
	 * invalidate() �����ᵼ�´˷���ִ��
	 */
	@Override
	public void computeScroll() {
		
		if(myScrolller.computeScrollOffset()){
			scrollTo((int) myScrolller.getCurrX(),0);
			invalidate();
		}
	
	}

}
