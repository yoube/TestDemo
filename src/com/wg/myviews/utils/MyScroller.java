package com.wg.myviews.utils;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

/**
 * 计算移动的工具类
 * @author EXP
 *
 */
public class MyScroller {

	private int distanceY;
	private int distanceX;
	private int startY;
	private int startX;
	private long startTime;//启动时间
	private boolean isFlash; //是否在移动中

	public MyScroller(Context context){
		
		
	}

	/**
	 * 开始移动
	 * @param startX 开始移动的X值
	 * @param startY 开始移动的Y值
	 * @param disX 	X轴上移动的距离
	 * @param disY	Y轴上移动的距离
	 */
	public void startScroll(int startX, int startY, int disX, int disY) {
		this.startX = startX;
		this.startY = startY;
		this.distanceX = disX;
		this.distanceY = disY;
		
		this.startTime = SystemClock.uptimeMillis();//返回从开机到现在的时间值；
	
		this.isFlash = false;
	}
	
	
	private long currX;
	/**
	 * 默认运行时间
	 */
	int duration =200;
	/**
	 * 计算偏移量
	 */
	public boolean computeScrollOffset() {
		if(isFlash) return false;
		
		
		long passTiem = SystemClock.uptimeMillis()-startTime;//已经使用的时间
		
		
		if(passTiem<duration){//判断 时间是否在范围内
			/**
			 * 计算出要移动到那个点 = 开始点（startX）+ 速度（distanceX/duration） X 已经过去的时间（passTiem）；
			 */
			currX = startX + distanceX*passTiem/duration; 
		}else{
			currX = startX +distanceX;
			
			isFlash = true;
		}
		
		
		return true;
	}

	public long getCurrX() {
		return currX;
	}

	public void setCurrX(long currX) {
		this.currX = currX;
	}
	
}
