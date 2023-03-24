package com.mr.service;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat;


public class MusicPlayer implements Runnable{
		
		File soundFile; 	// 音乐文件
		Thread thread;		// 父线程
		boolean circulate;  // 是否循环播放

		public MusicPlayer(String filepath, boolean circulate) 
					throws FileNotFoundException {
				this.circulate = circulate;
				soundFile = new File(filepath);
				if (!soundFile.exists()) {
					throw new FileNotFoundException(filepath + "未找到");
				}
			}
	
		// 播放效果
	    @Override
		public void run() {
			byte[] auBuffer = new byte[1024*128]; // 创建缓冲区
			do {
				AudioInputStream audioInputStream = null;
				SourceDataLine auline = null;
				try {
					// 从音乐文件中获取音频输入
					audioInputStream = AudioSystem.getAudioInputStream(soundFile);
					AudioFormat format = audioInputStream.getFormat(); // 获取音频格式
					// 创建行对象
					DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
					// 音频系统类获得指定对象描述匹配的行对象
					auline = (SourceDataLine)AudioSystem.getLine(info);
					auline.open(format); // 按照格式打开源数据行
					auline.start();		// 源数据行开始读写活动
					int byteCount = 0;		// 用来记录音频输入流流出的字数
					while (byteCount != -1) { // 如果字节数不为1
						byteCount = audioInputStream.read(auBuffer, 0, auBuffer.length); // 从音频数据流读出128KB
						if (byteCount >= 0) { // 如果数据有效
							auline.write(auBuffer, 0, byteCount); // 有效数据放入数据行
						}
					}
				}catch(IOException e) {
					e.printStackTrace();
				}catch(UnsupportedAudioFileException e) {
					e.printStackTrace();
				}catch(LineUnavailableException e) {
					e.printStackTrace();
				}finally {
					auline.drain();			// 清空数据行
					auline.close();			// 关闭数据行
				}	
			}while(circulate);		//根据循环判断是否循环播放
		}
	
		public void play() {
			thread = new Thread(this);	//创建线程对象
			thread.start();		// 开启线程
		}
		
		public void stop() {
			thread.stop();		// 强制关闭线程
		}
}
