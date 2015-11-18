package com.wg.myviews;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MediaRecorderVideoActivity extends Activity {
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private Button action;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_media_recorder_video);
		preview = new Preview(this);
		setContentView(preview);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
//		camera = Camera.open();
//		if (camera != null) {
//			Camera.Parameters params = camera.getParameters();
//			camera.setParameters(params);
//		}
		camera = Camera.open();
//		 camera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);
	     cameraCurrentlyLocked = defaultCameraId;
	     Log.e("preview",""+preview);
	     preview.setCamera(camera);

	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		camera.stopPreview();
//		previewRunning = false;
//		camera.release();
		if (camera != null) {
            preview.setCamera(null);
            camera.release();
            camera = null;
        }
	}

	private Camera camera;
	private int numberOfcameras ,defaultCameraId,cameraCurrentlyLocked;
	private boolean previewRunning;
	private Preview preview;

	private void initView() {
		Log.e("numberOfcameras", "相机可用个数：" + numberOfcameras);

		numberOfcameras = Camera.getNumberOfCameras();
//		preview = (Preview) findViewById(R.id.surface_view);

		CameraInfo cameraInfo = new CameraInfo();
		for (int i = 0; i < numberOfcameras; i++) {
			Camera.getCameraInfo(i, cameraInfo);
			if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK) {
				defaultCameraId = i;
			}
		}
		// surfaceView = (SurfaceView) findViewById(R.id.surface_view);
		// surfaceHolder = surfaceView.getHolder();
		// surfaceHolder.addCallback(new Callback() {
		//
		//
		// @Override
		// public void surfaceDestroyed(SurfaceHolder holder) {
		//
		// }
		//
		// @Override
		// public void surfaceCreated(SurfaceHolder holder) {
		//
		// }
		//
		// @Override
		// public void surfaceChanged(SurfaceHolder holder, int format, int
		// width, int height) {
		// if (previewRunning) {
		// camera.stopPreview();
		// }
		//
		// Camera.Parameters p = camera.getParameters();
		// List<Size> list = p.getSupportedPictureSizes();
		// for (Size size : list) {
		// if (size.width >= width && size.height >= height) {
		// width = size.width;
		// height = size.height;
		// Log.e("width", ""+width);
		// Log.e("height", ""+height);
		// break;
		// }
		// }
		// p.setPreviewSize(width, height);
		//// p.setPreviewFrameRate(3);
		//// p.setPictureSize(width, height);
		// p.setPreviewFormat(PixelFormat.JPEG);
		// camera.setParameters(p);
		//
		// try {
		// camera.setPreviewDisplay(holder);
		// camera.startPreview();
		// previewRunning = true;
		// } catch (IOException e) {
		// Log.e("TAG", e.getMessage());
		// e.printStackTrace();
		// }
		// }
		// });
		// surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

//		action = (Button) findViewById(R.id.action_btn);
//		action.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//
//			}
//		});
	}

	public class Preview extends ViewGroup implements SurfaceHolder.Callback {
		private final String TAG = "Preview";

		SurfaceView mSurfaceView;
		SurfaceHolder mHolder;
		Size mPreviewSize;
		List<Size> mSupportedPreviewSizes;
		Camera mCamera;

		Preview(Context context) {
			super(context);

			mSurfaceView = new SurfaceView(context);
			addView(mSurfaceView);

			// Install a SurfaceHolder.Callback so we get notified when the
			// underlying surface is created and destroyed.
			mHolder = mSurfaceView.getHolder();
			mHolder.addCallback(this);
			mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}

		public Preview(Context context, AttributeSet attrs) {
			super(context, attrs);
			mSurfaceView = new SurfaceView(context);
			addView(mSurfaceView);

			// Install a SurfaceHolder.Callback so we get notified when the
			// underlying surface is created and destroyed.
			mHolder = mSurfaceView.getHolder();
			mHolder.addCallback(this);
			mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		}

		public void setCamera(Camera camera) {
			mCamera = camera;
			if (mCamera != null) {
				mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
				camera.setDisplayOrientation(90);
				requestLayout();
			}
		}

		public void switchCamera(Camera camera) {
			setCamera(camera);
			try {
				camera.setPreviewDisplay(mHolder);
			} catch (IOException exception) {
				Log.e(TAG, "IOException caused by setPreviewDisplay()", exception);
			}
			Camera.Parameters parameters = camera.getParameters();
			parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
			requestLayout();

			camera.setParameters(parameters);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// We purposely disregard child measurements because act as a
			// wrapper to a SurfaceView that centers the camera preview instead
			// of stretching it.
			final int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
			final int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
			setMeasuredDimension(width, height);

			if (mSupportedPreviewSizes != null) {
//				mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width, height);
				mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, height, width);
			}
		}

		@Override
		protected void onLayout(boolean changed, int l, int t, int r, int b) {
			if (changed && getChildCount() > 0) {
				final View child = getChildAt(0);

				final int width = r - l;
				final int height = b - t;

				int previewWidth = width;
				int previewHeight = height;
				if (mPreviewSize != null) {
					previewWidth = mPreviewSize.width;
					previewHeight = mPreviewSize.height;
				}
				
				Log.e("width", ""+width);
				Log.e("height", ""+height);
				
				Log.e("previewWidth", ""+previewWidth);
				Log.e("previewHeight", ""+previewHeight);
				
				Log.e("width * previewHeight ", ""+(width * previewHeight));
				Log.e("height * previewWidth ", ""+(height * previewWidth));
				child.layout((width - previewWidth) / 2, 0, (height + previewHeight) / 2, height);
				// Center the child SurfaceView within the parent.
//				if (width * previewHeight > height * previewWidth) {
//					final int scaledChildWidth = previewWidth * height / previewHeight;
//					child.layout((width - scaledChildWidth) / 2, 0, (width + scaledChildWidth) / 2, height);
//				} else {
//					final int scaledChildHeight = previewHeight * width / previewWidth;
//					child.layout(0, (height - scaledChildHeight) / 2, width, (height + scaledChildHeight) / 2);
//				}
			}
		}

		public void surfaceCreated(SurfaceHolder holder) {
			// The Surface has been created, acquire the camera and tell it
			// where
			// to draw.
			try {
				if (mCamera != null) {
					mCamera.setPreviewDisplay(holder);
				}
			} catch (IOException exception) {
				Log.e(TAG, "IOException caused by setPreviewDisplay()", exception);
			}
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			// Surface will be destroyed when we return, so stop the preview.
			if (mCamera != null) {
				mCamera.stopPreview();
			}
		}

		private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
			final double ASPECT_TOLERANCE = 0.1;
			double targetRatio = (double) w / h;
			if (sizes == null)
				return null;
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

			// Cannot find the one match the aspect ratio, ignore the
			// requirement
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

		public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
			// Now that the size is known, set up the camera parameters and
			// begin
			// the preview.
			Camera.Parameters parameters = mCamera.getParameters();
			parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
			requestLayout();

			mCamera.setParameters(parameters);
			mCamera.startPreview();
		}
	}

}
