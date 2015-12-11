package com.example.comminication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.gcode_read.ReaderManager;

import android.annotation.SuppressLint;
import android.os.StrictMode;
 
 
public class TcpClient extends ReaderManager implements Runnable{
	public int Flag = 0;
	Date dt = new Date();
	/**
	 * @param args
	 */
	@SuppressLint("NewApi")
	public  void run() {
		try {
			/*if(android.os.Build.VERSION.SDK_INT > 9) {               
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();               
				StrictMode.setThreadPolicy(policy);  
				}*/  
			String host = "������ �Է�";//<--Server Ip
			System.out.println("�����͸� �۽� �Ͽ����ϴ�."+"("+dt.toString()+")");
			Socket socket = new Socket(host, 23);
 
			// �Է� ��Ʈ��
			// �������� ���� �����͸� ����
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
 
			// ��� ��Ʈ��
			// ������ �����͸� �۽�
			OutputStream out = socket.getOutputStream();
 
			// ������ ������ �۽�
			while((Data = br.readLine())!=null){
			out.write(Data.getBytes());
			out.flush();	
			}		
			br.close();
			String line = in.readLine();
			//if(line != null){
			
			
			System.out.println("������ ������ ���� : "+line+"("+dt.toString()+")");
			// ���� ���� ����
			in.close();
			out.close();
			socket.close();
			
			Flag = 1;
			//}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
 
}