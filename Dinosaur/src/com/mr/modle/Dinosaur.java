package com.mr.modle;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.mr.service.FreshThread;
import com.mr.service.Sound;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Dinosaur {
	public BufferedImage image;  //��ͼƬ
	private BufferedImage image1, image2, image3; //�ܲ�ͼƬ
	public int x, y;   // ����
	private int jumpValue = 0; // ��Ծ������
	private boolean jumpState = false; // ��Ծ״̬
	private int stepTimer = 0; //̤����ʱ��
	private final int JUMP_HIGHT = 100; //��������߶�
	private final int LOWEST_Y = 120; // ����������
	private final int FREASH = FreshThread.FREASH; // ˢ��ʱ��
	
	public Dinosaur() {
		x = 50;
		y = LOWEST_Y;
		try {
			image1 = ImageIO.read(new File("image/����1.png"));
			image2 = ImageIO.read(new File("image/����2.png"));
			image3 = ImageIO.read(new File("image/����3.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// ̤��
	private void step() {
		// ÿ��250ms����һ��ͼƬ
		int tmp = stepTimer / 250 % 3;
		switch(tmp) {
			case 1:
				image = image1;
				break;
			case 2:
				image = image2;
				break;
			default:
				image = image3;
		}
		stepTimer += FREASH;
	}
	
	// ��Ծ
	public void jump() {
		if (!this.jumpState) {  // ���û������Ծ״̬
			Sound.jump();  // ������Ծ��Ч
		}
		this.jumpState = true;  // ������Ծ״̬
	}
	
	// �ƶ�
	public void move() {
		step();     // ����̤��
		if (this.jumpState) {   // ���������Ծ
			if (y >= LOWEST_Y) {  // ��������ڵ�����͵�
				jumpValue = -4; // �������为ֵ
			}
			if (y <= LOWEST_Y-JUMP_HIGHT) { // ���������ߵ�
				jumpValue = 4;  // ��������Ϊ��ֵ
			}
			y += jumpValue;
			if (y >= LOWEST_Y) {  // ����ٴ����
				jumpState = false;  // ֹͣ��Ծ
			}
		}
	}
	
	// �߽����-�Ų�
	public Rectangle getFootBounds() {
		return new Rectangle(x+30, y+59, 29, 18);
	}
	
	// 	�߽����-ͷ��
	public Rectangle getHeadBounds() {
		return new Rectangle(x+66, y+25, 32, 22);
	}

}