/**
 * 
 * @author Administrator
 * udp client module
 * 
 */
package com.example.comminication;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.example.gcode_read.ReaderManager;

import android.os.StrictMode;
import android.util.Log;

public class UdpClient extends ReaderManager implements Runnable {
	int SERVERPORT=3306;//서버 포트주소
	public static String udpMsg="Not Input Data";//아무것도 입력안되있으면

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {   
            // Retrieve the ServerName   
            InetAddress serverAddr = InetAddress.getByName("Server Ip"); //서버 아이피 주소
                
            Log.d("UDP", "C: Connecting...");   
            /* Create new UDP-Socket */  
            DatagramSocket socket = new DatagramSocket();   
            

		      
            /* Prepare some data to be sent. */  
            byte[] buf = new byte[254];
                
            /* Create UDP-packet with  
             * data & destination(url+port) */  
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, SERVERPORT);   //바이트, 바이트길이, 아이피주소,서버포트 패킷으로 저장
            Log.d("UDP", "C: Sending: '" + new String(buf) + "'");   

            socket.send(packet);   //패킷 전송
            
            Log.d("UDP", "C: Sent.");   
            Log.d("UDP", "C: Done.");   
                
            socket.receive(packet);
            Log.d("UDP", "C: Received: '" + new String(packet.getData()) + "'");   

       } catch (Exception e) {   
            Log.e("UDP", "C: Error", e);   

       }

	}
}
