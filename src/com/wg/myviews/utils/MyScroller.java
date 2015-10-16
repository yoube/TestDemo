package com.wg.myviews.utils;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

/**
 * �����ƶ��Ĺ�����
 * @author EXP
 *
 */
public class MyScroller {

	private int distanceY;
	private int distanceX;
	private int startY;
	private int startX;
	private long startTime;//����ʱ��
	private boolean isFlash; //�Ƿ����ƶ���

	public MyScroller(Context context){
		
		
	}

	/**
	 * ��ʼ�ƶ�
	 * @param startX ��ʼ�ƶ���Xֵ
	 * @param startY ��ʼ�ƶ���Yֵ
	 * @param disX 	X�����ƶ��ľ���
	 * @param disY	Y�����ƶ��ľ���
	 */
	public void startScroll(int startX, int startY, int disX, int disY) {
		this.startX = startX;
		this.startY = startY;
		this.distanceX = disX;
		this.distanceY = disY;
		
		this.startTime = SystemClock.uptimeMillis();//���شӿ��������ڵ�ʱ��ֵ��
	
		this.isFlash = false;
	}
	
	
	private long currX;
	/**
	 * Ĭ������ʱ��
	 */
	int duration =200;
	/**
	 * ����ƫ����
	 */
	public boolean computeScrollOffset() {
		if(isFlash) return false;
		
		
		long passTiem = SystemClock.uptimeMillis()-startTime;//�Ѿ�ʹ�õ�ʱ��
		
		
		if(passTiem<duration){//�ж� ʱ���Ƿ��ڷ�Χ��
			/**
			 * �����Ҫ�ƶ����Ǹ��� = ��ʼ�㣨startX��+ �ٶȣ�distanceX/duration�� X �Ѿ���ȥ��ʱ�䣨passTiem����
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
