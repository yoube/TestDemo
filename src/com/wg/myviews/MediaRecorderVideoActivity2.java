package com.wg.myviews;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import com.wg.myviews.view.CameraPreview;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MediaRecorderVideoActivity2 extends Activity {
	private CameraPreview cameraPreview;
	private Button action1, action2;
	private MediaRecorder mediaRecorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);// 拍照过程屏幕一直处于高亮
		setContentView(R.layout.activity_media_recorder_video);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		camera = Camera.open();
		// camera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);
		cameraCurrentlyLocked = defaultCameraId;
		Log.e("preview", "" + cameraPreview);
		cameraPreview.setCamera(camera);

	}

	@Override
	protected void onPause() {
		super.onPause();
		// camera.stopPreview();
		// previewRunning = false;
		// camera.release();
		if (camera != null) {
			cameraPreview.setCamera(null);
			camera.release();
			camera = null;
		}
	}

	private Camera camera;
	private int numberOfcameras, defaultCameraId, cameraCurrentlyLocked;
	private boolean previewRunning;

	private void initView() {

		// mediaRecorder = new MediaRecorder();
		// //设置相机录制
		// mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		// //设置输出格式
		// mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		// //设置视频编码
		// mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
		// //设置分辨率
		// mediaRecorder.setVideoSize(camera.getParameters().getPreviewSize().width,
		// camera.getParameters().getPreviewSize().width);
		// //设置视频的帧率
		// mediaRecorder.setVideoFrameRate(20);
		// mediaRecorder.setPreviewDisplay(cameraPreview.getSurfaceHolder().getSurface());
		//
		// //设置保存路径
		// File lastFileName = newFileName();
		// mediaRecorder.setOutputFile(lastFileName.getAbsolutePath());

		// camera.

		numberOfcameras = Camera.getNumberOfCameras();
		Log.e("numberOfcameras", "相机可用个数：" + numberOfcameras);
		// preview = (Preview) findViewById(R.id.surface_view);

		CameraInfo cameraInfo = new CameraInfo();
		for (int i = 0; i < numberOfcameras; i++) {
			Camera.getCameraInfo(i, cameraInfo);
			if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK) {
				defaultCameraId = i;
			}
		}
		cameraPreview = (CameraPreview) findViewById(R.id.surface_view);

		action1 = (Button) findViewById(R.id.action_btn1);
		action2 = (Button) findViewById(R.id.action_btn2);
		action1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mediaRecorder == null) {
					
					
					action1.setText("停止  录制");
					mediaRecorder = new MediaRecorder();
					// 设置相机录制
					mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
					// 设置输出格式
					mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					// 设置视频编码
					mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
					// 设置分辨率
//					mediaRecorder.setVideoSize(cameraPreview.getmPreviewSize().width,cameraPreview.getmPreviewSize().height);
					// 设置视频的帧率
//					mediaRecorder.setVideoFrameRate(20);
					mediaRecorder.setPreviewDisplay(cameraPreview.getSurfaceHolder().getSurface());
					// 设置保存路径
//					mediaRecorder.setCamera(camera);
					File lastFileName = newFileName();
					mediaRecorder.setOutputFile(lastFileName.getAbsolutePath());
					try {
//						camera.unlock();
						mediaRecorder.prepare();
//						if (camera != null) {
//							cameraPreview.setCamera(null);
//							camera.stopPreview();
//							camera.release();
//							camera = null;
//						}
						mediaRecorder.start();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					action1.setText("开始  录制");
					mediaRecorder.stop();
					mediaRecorder.release();
					mediaRecorder = null;
				}

			}
		});
		action2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switchCamera();
			}
		});
	}
	public void test() throws Exception{
		Class clazz = CameraPreview.class;
		Method method = clazz.getMethod("getOptimalPreviewSize",clazz);
		Object obj = clazz.newInstance();
		method.invoke(obj, null);
		
	}

	private File newFileName() {
		File dir = new File(Environment.getExternalStorageDirectory(), "MyViews/cache/");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dir, new Date().getTime() + ".3gp");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return file;
	}

	public void switchCamera() {
		if (numberOfcameras == 1) {
			Toast.makeText(getApplicationContext(), "你的摄像头就一个，无法切换", Toast.LENGTH_SHORT).show();
			return;
		}
		// 释放资源
		if (camera != null) {
			cameraPreview.setCamera(null);
			camera.release();
			camera = null;
		}
		int index = (cameraCurrentlyLocked + 1) % numberOfcameras;
		camera = Camera.open(index);
		cameraCurrentlyLocked = index;
		cameraPreview.switchCamera(camera);

	}

}
