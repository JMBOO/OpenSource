package com.example.directory_admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Directory_Manager {
	public  File Directory1;
	public int Flag, Flag2 = 0;
	public void DirectoryFolder(String dir1){
		/*디렉토리내 폴더가 없을시 생성*/
		
		Directory1 = new File(dir1);
	
		if( !Directory1.exists()) {
			Directory1.mkdirs();
			ScanDirectory();
			Flag = 1;//폴더 생성 성공
			}if(Directory1.exists()){
				Flag = 2;
			}else{
				Flag = 0;//폴더 생성 실패
			}
	}
	
	public void ScanDirectory()//폴더생성후 readme.txt 파일생성(디렉토리 정상작동 확인용)
	{
		String testStr = "파일 저장 경로";
		File savefile = new File(Directory1+"/readme.txt");
		try{
		FileOutputStream fos = new FileOutputStream(savefile);
		fos.write(testStr.getBytes());
		fos.close();
		} catch(IOException e){}
	}
	
}
