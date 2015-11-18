package com.wg.myviews.view;

import android.content.Context;
import android.graphics.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup;

public class CameraPreview  extends ViewGroup implements Callback{
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	public CameraPreview(Context context, AttributeSet attrs) {
		super(context, attrs);
		surfaceView = new SurfaceView(context);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);	
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		
	}

	public void setCamera(Camera camera){
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
