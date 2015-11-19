package com.wg.myviews.view;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

public class CameraPreview extends ViewGroup implements Callback {
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private List<Size> mSupportedPreviewSizes;
	private Camera mCamera;
	private Size mPreviewSize;
	
	

	public CameraPreview(Context context, AttributeSet attrs) {
		super(context, attrs);
		surfaceView = new SurfaceView(context);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		addView(surfaceView);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
		final int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
		setMeasuredDimension(width, height);
		if (mSupportedPreviewSizes != null) {
			// 设置SurfacesView的大小
			mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width, height);
		}
	}

	private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
		final double ASPECT_TOLERANCE = 0.1;
		double targetRatio = (double) w / h;
		if (sizes == null)
			return null;

		// Log.e("w", ""+w);
		// Log.e("h", ""+h);
		// Log.e("targetRatio", ""+targetRatio);

		Size optimalSize = null;

		double minDiff = Double.MAX_VALUE;

		int targetHeight = h;

		// Try to find an size match aspect ratio and size
		for (Size size : sizes) {
			double ratio = (double) size.width / size.height;
			if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
				continue;
			if (Math.abs(size.height - targetHeight) < minDiff) {
				optimalSize = size;
				minDiff = Math.abs(size.height - targetHeight);
			}
		}

		// Cannot find the one match the aspect ratio, ignore the requirement
		if (optimalSize == null) {
			minDiff = Double.MAX_VALUE;
			for (Size size : sizes) {
				if (Math.abs(size.height - targetHeight) < minDiff) {
					optimalSize = size;
					minDiff = Math.abs(size.height - targetHeight);
				}
			}
		}
		return optimalSize;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed && getChildCount() > 0) {
			View child = getChildAt(0);
			int width = r - l;
			int height = b - t;
			int previewWidth = width;
			int previewHeight = height;
			if (mPreviewSize != null) {
				previewWidth = mPreviewSize.width;
				previewHeight = mPreviewSize.height;
			}

			// Log.e("width", ""+width);
			// Log.e("height", ""+height);
			//
			// Log.e("previewWidth", ""+previewWidth);
			// Log.e("previewHeight", ""+previewHeight);
			//
			// Log.e("width * previewHeight ", ""+(width * previewHeight));
			// Log.e("height * previewWidth ", ""+(height * previewWidth));
			// child.layout((width - previewWidth) / 2, 0, (height +
			// previewHeight) / 2, height);
			child.layout(0, 0, width, height);

		}

	}

	public void setCamera(Camera camera) {
		mCamera = camera;
		if (camera != null) {
			mCamera.setDisplayOrientation(90);
			mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
		}
	}

	public void switchCamera(Camera camera) {
		setCamera(camera);
		try {
			camera.setPreviewDisplay(surfaceHolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Camera.Parameters parameters = mCamera.getParameters();
//		parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
//		requestLayout();
//		mCamera.setParameters(parameters);
		mCamera.startPreview();

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (mCamera != null) {
			try {
				mCamera.setPreviewDisplay(holder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Camera.Parameters parameters = mCamera.getParameters();
		parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
		requestLayout();
		mCamera.setParameters(parameters);
		mCamera.startPreview();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mCamera != null) {
			mCamera.stopPreview();
		}
	}
	public SurfaceView getSurfaceView() {
		return surfaceView;
	}

	public void setSurfaceView(SurfaceView surfaceView) {
		this.surfaceView = surfaceView;
	}

	public SurfaceHolder getSurfaceHolder() {
		return surfaceHolder;
	}

	public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
		this.surfaceHolder = surfaceHolder;
	}

	public Camera getmCamera() {
		return mCamera;
	}

	public void setmCamera(Camera mCamera) {
		this.mCamera = mCamera;
	}

	public Size getmPreviewSize() {
		return mPreviewSize;
	}

	public void setmPreviewSize(Size mPreviewSize) {
		this.mPreviewSize = mPreviewSize;
	}
}
