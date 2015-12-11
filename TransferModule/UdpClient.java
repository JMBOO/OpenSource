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
	int SERVERPORT=3306;//���� ��Ʈ�ּ�
	public static String udpMsg="Not Input Data";//�ƹ��͵� �Է¾ȵ�������

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {   
            // Retrieve the ServerName   
            InetAddress serverAddr = InetAddress.getByName("Server Ip"); //���� ������ �ּ�
                
            Log.d("UDP", "C: Connecting...");   
            /* Create new UDP-Socket */  
            DatagramSocket socket = new DatagramSocket();   
            

		      
            /* Prepare some data to be sent. */  
            byte[] buf = new byte[254];
                
            /* Create UDP-packet with  
             * data & destination(url+port) */  
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, SERVERPORT);   //����Ʈ, ����Ʈ����, �������ּ�,������Ʈ ��Ŷ���� ����
            Log.d("UDP", "C: Sending: '" + new String(buf) + "'");   

            socket.send(packet);   //��Ŷ ����
            
            Log.d("UDP", "C: Sent.");   
            Log.d("UDP", "C: Done.");   
                
            socket.receive(packet);
            Log.d("UDP", "C: Received: '" + new String(packet.getData()) + "'");   

       } catch (Exception e) {   
            Log.e("UDP", "C: Error", e);   

       }

	}
}
