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
		

		// ����һ����ʱ����Ƶ����ļ�
		// audioFile = File.createTempFile("record_", ".amr");
		// ��4����ָ����Ƶ����ļ�
		// mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
		// ��5��������prepare����
		// mediaRecorder.prepare();
		// ��6��������start������ʼ¼��
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
			// ��1����������Ƶ��Դ��MIC��ʾ��˷磩
			mr.setAudioSource(MediaRecorder.AudioSource.MIC);
			// ��2����������Ƶ�����ʽ��Ĭ�ϵ������ʽ��
			mr.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
			// ��3����������Ƶ���뷽ʽ��Ĭ�ϵı��뷽ʽ��
			mr.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
			// ����һ����ʱ����Ƶ����ļ�
			audioFile = getFile();
			
//			audioFile = File.createTempFile("record_", ".amr");
			// ��4����ָ����Ƶ����ļ�
			mr.setOutputFile(audioFile.getAbsolutePath());
			// ��5��������prepare����
			mr.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ��6��������start������ʼ¼��
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
