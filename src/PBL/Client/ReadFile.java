package PBL.Client;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


//Class đọc file txt ,trả về ma trận trọng số 
public class ReadFile {
	public int[][] loadData(String filepath) throws FileNotFoundException {
		int arr[][] = null;
		File file = new File(filepath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try{
			String line = null;
			int count=0;
			while((line=reader.readLine())!=null) {
				String k[] = line.split(" ");
				if(k.length == 1) {
					arr = new int[Integer.parseInt(k[0])][Integer.parseInt(k[0])];
				} else {
					for(int i=0; i < k.length; i++) {
						arr[count][i]=Integer.parseInt(k[i]);						
					}
					count++;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return arr;
	}
}
