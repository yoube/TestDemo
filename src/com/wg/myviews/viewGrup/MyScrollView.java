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

		// 实例化事件处理工具类
		detector = new GestureDetector(mContext, new OnGestureListener() {// 设置监听

					@Override
					public boolean onSingleTapUp(MotionEvent e) {
						// 当弹起时调用
						System.out.print("up");
						return false;
					}

					@Override
					public void onShowPress(MotionEvent e) {

					}

					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
						// 当手指滑动
						scrollBy((int) distanceX, 0);
						return false;
					}

					@Override
					public void onLongPress(MotionEvent e) {
						// 当长按时回调

					}

					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						// 当快速滑动时回调
						return false;
					}

					@Override
					public boolean onDown(MotionEvent e) {
						// 当手指按下时
						return false;
					}
				});

//		myScrolller = new MyScroller(mContext);
		//系统的Scroller
		myScrolller = new Scroller(mContext);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View view = getChildAt(i);
//			Log.w("wwww","i-->H： "+view.getHeight());
			view.layout(getWidth() * i, 0, getWidth() * (i + 1), getHeight());
		}

	}

	/**
	 * 可以解析手势工具类 解析滑动 、长按、快速滑动
	 */
	private GestureDetector detector;
	private int curredIndex;
	private float firstX;
	private float lastX;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		
//		detector.onTouchEvent(event);//使用工具类解析事件	
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			//当按下时
			firstX = lastX =  event.getX();
			
			break;
		case MotionEvent.ACTION_MOVE:
			//当移动时
			int temp = (int) (lastX - event.getX());
			lastX = event.getX();
			
			scrollBy(temp,0);
			
			break;
		case MotionEvent.ACTION_UP:
			//当弹起时
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
			
			//移动到某个
			moveTo(0);
			
			break;

		}
		
		return true;
		
	}
	/**
	 * 移动帮助类
	 */
	private Scroller myScrolller;
	
	public void moveTo(int nexId){
		//判断边界
		
		if(curredIndex<0){
			curredIndex = 0;
		}else if(curredIndex>=getChildCount()){
			
			curredIndex= getChildCount()-1;
		}
		
		//瞬间移动到某个点
//		scrollTo(curredIndex*getWidth(), 0);
		
		//缓慢移动到某个点
		int temp = (int) (curredIndex*getWidth()-getScrollX());//计算移动的距离
		myScrolller.startScroll(getScrollX(),0,temp,0);
		
		invalidate();//刷新View
	}
	/**
	 * invalidate() 方法会导致此方法执行
	 */
	@Override
	public void computeScroll() {
		
		if(myScrolller.computeScrollOffset()){
			scrollTo((int) myScrolller.getCurrX(),0);
			invalidate();
		}
	
	}

}
