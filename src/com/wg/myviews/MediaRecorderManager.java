package com.wg.myviews;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;

public class MediaRecorderManager {
	private MediaRecorder mr;
	private MediaPlayer mp;
	static MediaRecorderManager manager;

	private MediaRecorderManager() {

		mp = new MediaPlayer();
		mr = new MediaRecorder();
		

		// 创建一个临时的音频输出文件
		// audioFile = File.createTempFile("record_", ".amr");
		// 第4步：指定音频输出文件
		// mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
		// 第5步：调用prepare方法
		// mediaRecorder.prepare();
		// 第6步：调用start方法开始录音
		// mediaRecorder.start();
	}

	public static MediaRecorderManager getInstall() {
		if (manager == null) {
			manager = new MediaRecorderManager();
		}
		return manager;
	}

	private File audioFile;

	public void start() {
		// mr.reset();
		try {
			// 第1步：设置音频来源（MIC表示麦克风）
			mr.setAudioSource(MediaRecorder.AudioSource.MIC);
			// 第2步：设置音频输出格式（默认的输出格式）
			mr.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
			// 第3步：设置音频编码方式（默认的编码方式）
			mr.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
			// 创建一个临时的音频输出文件
			audioFile = getFile();
			
//			audioFile = File.createTempFile("record_", ".amr");
			// 第4步：指定音频输出文件
			mr.setOutputFile(audioFile.getAbsolutePath());
			// 第5步：调用prepare方法
			mr.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 第6步：调用start方法开始录音
		mr.start();
	}

	private File getFile() {
		File dir = new File(Environment.getExternalStorageDirectory(),"MyViews/cache/");
		if(!dir.exists()){
			dir.mkdirs();
		}
		 File file = new File(dir,SystemClock.uptimeMillis()+".aac");
		 
		 if(!file.exists()){
			 try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		
		return file;
	}

	public String stop() {
		String path = audioFile.getAbsolutePath();
		mr.stop();
		return path;
	}

	public void play(String path) {
		Log.e("path -:> ", path);
		try {
			if(mp.isPlaying()){
				mp.pause();
			}else{
				mp.reset();
				mp.setDataSource(path);
				mp.prepare();
				mp.start();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
