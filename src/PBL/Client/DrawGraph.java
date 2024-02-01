package PBL.Client;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.JFrame;
 
public class DrawGraph extends Canvas {
	
	//Class định nghĩa toạ độ mỗi điểm
	private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	class coordinate {
		int a;
		int b;
		public coordinate(int a,int b) {
			this.a = a;
			this.b = b;
		}
	}
	String fileGraph;
	String filePoint;
	Random rand = new Random();
	public DrawGraph(String fileGraph, String filePoint)
	{
		this.fileGraph = fileGraph;
		this.filePoint = filePoint;
	}
	
	
	public void paint(Graphics g) {
		int matrix[][] = null;
		try {
			matrix = new ReadFile().loadData(filePoint);
			for(int i=0; i < 6; i++) {
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int length1 = matrix.length;
		coordinate arr[] = new coordinate[length1];
		setBackground(Color.WHITE);
		int point =1;
		// hàm vẽ đỉnh
		for(int i=0; i < length1; i++ ) {
			int x = matrix[i][0]*80+100;
			int y = matrix[i][1]*80+20;
			arr[i] = new coordinate(x, y);
			g.setColor(new Color(rand.nextInt(200),rand.nextInt(200),rand.nextInt(200)));
			g.fillOval(x, y, 20, 20);
			g.drawString(String.valueOf(ALPHABET.charAt(i)), x, y);
			point++;
		}
		// hàm nối các đỉnh
		int matrix2[][] = null;
		try {
			matrix2 = new ReadFile().loadData(fileGraph);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		int length2 = matrix.length;
		int temp = 0;
		for(int i=0; i < length2; i++) {
			for(int j=0; j < length2 ; j++)
				if(matrix2[i][j] > 0 && matrix2[i][j] != 0) {
					g.drawLine(arr[i].a+10, arr[i].b+10, arr[j].a+10, arr[j].b+10);
					// điền độ dài đoạn thẳng
        			int mid_x = ((arr[i].a+20)+(arr[j].a+20))/2;
        			int mid_y = ((arr[i].b+20)+(arr[j].b+20))/2;
        			g.drawString(String.valueOf(matrix2[i][j]),mid_x,mid_y);
				}
		}
		
	}
} 