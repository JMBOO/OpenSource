package com.example.directory_admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Directory_Manager {
	public  File Directory1;
	public int Flag, Flag2 = 0;
	public void DirectoryFolder(String dir1){
		/*���丮�� ������ ������ ����*/
		
		Directory1 = new File(dir1);
	
		if( !Directory1.exists()) {
			Directory1.mkdirs();
			ScanDirectory();
			Flag = 1;//���� ���� ����
			}if(Directory1.exists()){
				Flag = 2;
			}else{
				Flag = 0;//���� ���� ����
			}
	}
	
	public void ScanDirectory()//���������� readme.txt ���ϻ���(���丮 �����۵� Ȯ�ο�)
	{
		String testStr = "���� ���� ���";
		File savefile = new File(Directory1+"/readme.txt");
		try{
		FileOutputStream fos = new FileOutputStream(savefile);
		fos.write(testStr.getBytes());
		fos.close();
		} catch(IOException e){}
	}
	
}
