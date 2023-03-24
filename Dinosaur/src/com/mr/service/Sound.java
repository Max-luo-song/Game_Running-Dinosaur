package com.mr.service;

import java.io.FileNotFoundException;

public class Sound {
	static final String DIR = "music/";		// 音乐文件夹
	static final String BACKGROUD = "background.wav";	// 背景音乐
	static final String JUMP = "jump.wav";		// 跳跃音效
	static final String HIT = "hit.wav";		// 撞击音效
	
	private static void play(String file, boolean circulate) {
		try {
			MusicPlayer player = new MusicPlayer(file, circulate);	// 创建播放器
			player.play();	// 开始播放
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 播放跳跃音效
	static public void jump() {
		play(DIR+JUMP, false);
	}
	
	// 播放撞击音效
	static public void hit() {
		play(DIR+HIT, false);
	}
	
	// 播放背景音乐
	static public void backgroud() {
		play(DIR+BACKGROUD, true);
	}
}
