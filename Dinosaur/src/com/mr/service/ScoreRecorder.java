package com.mr.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class ScoreRecorder {
	private static final String SCOREFILE = "data/source";	// 成绩记录文件
	private static int scores[] = new int[3];	// 用于储存前三名
	
	public static void init() {
		File f = new File(SCOREFILE);	// 创建记录文件
		if (!f.exists()) {
			try {
				f.createNewFile();		// 创建新文件
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;						// 停止方法
		}
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(f);	// 文件字节输入流
			isr = new InputStreamReader(fis);	// 字节流转字符流
			br = new BufferedReader(isr);	// 缓冲字符流
			String value = br.readLine();	// 读取一行
			if (!(value == null || "".equals(value))) {	// 非空就分割字符串
				String vs[] = value.split(",");
				if (vs.length < 3) {	// 小于3，填充0
					Arrays.fill(scores, 0);
				}
				else {
					for (int i=0; i<3; i++) {
						scores[i] = Integer.parseInt(vs[i]);	// 将记录文件中值赋给当前数组
					}
				}
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {		// 依次关闭流
			try {
				br.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			try {
				isr.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveScore() {
		String value = scores[0] + "," + scores[1] + "," + scores[2]; // 拼接得分数组
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream(SCOREFILE); // 文件字节输出流
			osw = new OutputStreamWriter(fos);		// 字节流转字符流
			bw = new BufferedWriter(osw);			// 缓冲字符流
			bw.write(value);					// 写入拼接后的字符串
			bw.flush();							// 字符流刷新
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {			// 依次关闭流
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static public void addNewScore(int score) {
		int tmp[] = Arrays.copyOf(scores, 4);	// 建立长度为4的临时数组，包括之前的3次，最近1次
		tmp[3] = score;		// 新分数给第四个
		Arrays.sort(tmp);	// 临时数组升序排列
		scores = Arrays.copyOfRange(tmp, 1, 4);	// 最后三个给scores
	}
	
	static public int[] getScores() {
		return scores;
	}
}
