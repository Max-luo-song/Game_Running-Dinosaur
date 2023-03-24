package com.mr.view;

import java.awt.Container;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.mr.service.ScoreRecorder;
import com.mr.service.Sound;

public class MainFrame extends JFrame{
	
	public MainFrame() {
			restart();	// 开始
			setBounds(340, 150, 820, 260);	// 设置横纵坐标和宽高
			setTitle("奔跑吧！小恐龙!");	// 设置标题
			Sound.backgroud();	// 设置背景音乐
			ScoreRecorder.init();	// 读取得分记录
			addListener();		// 添加监听
			setDefaultCloseOperation(EXIT_ON_CLOSE);	// 关闭窗体停止程序
	}

	
	public void restart() {
		Container c = getContentPane(); 	// 获取主容器对象
		c.removeAll();				// 删除容器中所有组件
		GamePanel panel = new GamePanel();	// 创建新的游戏面板
		c.add(panel);
		addKeyListener(panel);		// 添加键盘事件
		c.validate();		// 容器重新验证所有组件
	}
	
	private void addListener() {
		addWindowListener(new WindowAdapter() {	// 增加窗体监听
			public void windowClosing(WindowEvent e) {	// 窗体关闭前保存得分记录
				ScoreRecorder.saveScore();
			}
		});
	}
}
