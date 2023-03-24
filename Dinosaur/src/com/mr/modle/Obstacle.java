package com.mr.modle;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import com.mr.view.BackgroundImage;
import java.awt.image.BufferedImage;

public class Obstacle {

	public int x, y; // 横纵坐标
	public BufferedImage image;
	private BufferedImage stone;    // 石头图片
	private BufferedImage cacti;	// 仙人掌图片
	private int speed;		// 移动速度
	public Obstacle() {
		try {
			stone = ImageIO.read(new File("image/石头.png"));
			cacti = ImageIO.read(new File("image/仙人掌.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		Random r = new Random();  // 创建随机对象
		if (r.nextInt(2) == 0) {  // 为0是仙人掌
			image = cacti;
		}
		else { 					  // 为1是石头
			image = stone; 
		}
		x = 800;          // 初始横坐标
		y = 200 - image.getHeight(); // 纵坐标
		speed = BackgroundImage.SPEED; // 移动速度与背景同步
		
	}
	
	// 移动
	public void move() {
		x -= speed;
	}
	
	// 消除
	public boolean isLive() {
		if (x <= -image.getWidth()) {		// 如果移出了游戏界面
			return false;					// 消亡
		}
		return true;						// 存活
	}
	
	// 边界对象
	public Rectangle getBounds() {
		if (image == cacti) {
			return new Rectangle(x+7, y, 15, image.getHeight()); // 返回仙人掌边界
		}
		return new Rectangle(x+5, y+4, 23, 21);   // 返回石头边界
	}

}
