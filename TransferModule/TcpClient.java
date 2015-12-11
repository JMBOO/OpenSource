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
			String host = "아이피 입력";//<--Server Ip
			System.out.println("데이터를 송신 하였습니다."+"("+dt.toString()+")");
			Socket socket = new Socket(host, 23);
 
			// 입력 스트림
			// 서버에서 보낸 데이터를 받음
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
 
			// 출력 스트림
			// 서버에 데이터를 송신
			OutputStream out = socket.getOutputStream();
 
			// 서버에 데이터 송신
			while((Data = br.readLine())!=null){
			out.write(Data.getBytes());
			out.flush();	
			}		
			br.close();
			String line = in.readLine();
			//if(line != null){
			
			
			System.out.println("서버로 부터의 응답 : "+line+"("+dt.toString()+")");
			// 서버 접속 끊기
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