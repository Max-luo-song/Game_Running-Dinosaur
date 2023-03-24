package com.mr.service;

import java.awt.Container;


import com.mr.view.GamePanel;
import com.mr.view.MainFrame;
import com.mr.view.ScoreDialog;

public class FreshThread extends Thread{
	
	public static final int FREASH = 20;	// 刷新时间
	GamePanel p;						// 游戏面板
		
	public FreshThread(GamePanel p) {
		this.p = p;
	}
	
	public void run() {
		while (!p.isFinish()) {		// 如果游戏未结束
			p.repaint();			// 重绘游戏面板
			try {
				Thread.sleep(FREASH);	// 按照刷新时间休眠
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Container c = p.getParent();		// 获取面板父容器
		while (!(c instanceof MainFrame)) {	// 不是主窗体就继续获取
			c = c.getParent();
		}
		MainFrame frame = (MainFrame)c;	// 将容器强制转换为主窗体
		new ScoreDialog(frame);		// 弹出成绩对话框
		frame.restart();		// 主窗体重载开始游戏
	}
}
