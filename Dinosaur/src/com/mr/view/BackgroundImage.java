package com.mr.view;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BackgroundImage {
	public BufferedImage image;		// 背景图片
	private BufferedImage image1, image2;	// 滚动的两个图片
	private Graphics2D g;		// 背景图片的绘图对象
	public int x1, x2;			// 两个滚动图片的坐标
	public static final int SPEED = 4;	// 滚动速度

	public BackgroundImage() {
		try {
			image1 = ImageIO.read(new File("image/背景.png"));
			image2 = ImageIO.read(new File("image/背景.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		// 主图片800x300
		image = new BufferedImage(800, 300, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();	// 获取主图片绘图对象
		x1 = 0;				// 第一幅图初始横坐标为0
		x2 = 800;			// 第二幅图初始横坐标为800
		g.drawImage(image1, x1, 0, null);
	}

	public void roll() {
		x1 -= SPEED;	// 第一幅左移
		x2 -= SPEED;	// 第二幅左移
		if (x1 <= -800) {	// 已出屏幕回到右侧
			x1 = 800;
		}
		if (x2 <= -800) {
			x2 = 800;
		}
		g.drawImage(image1, x1, 0, null);		// 主图片中绘制两幅图片
		g.drawImage(image2, x2, 0, null);
	}
}



