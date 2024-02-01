package PBL.Server;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


//Class đọc file txt ,trả về ma trận trọng số 
public class ReadFile {
	public int[][] loadData(String filepath) 
	{
		int arr[][] = null;
		Path path = Paths.get(filepath);
		Charset charset = Charset.forName("US-ASCII");
		try(BufferedReader reader = Files.newBufferedReader(path,charset)){
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
	public void writeDataG(String data) throws IOException 
	{
		try {
			File file = new File("Graph.txt");
			FileOutputStream fos = new FileOutputStream(file);
			byte[] bytesarray = data.getBytes();
			fos.write(bytesarray);
			fos.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void writeDataP(String data) throws IOException 
	{
		try {
			File file = new File("input.txt");
			FileOutputStream fos = new FileOutputStream(file);
			byte[] bytesarray = data.getBytes();
			fos.write(bytesarray);
			fos.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
