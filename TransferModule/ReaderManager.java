package com.example.gcode_read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReaderManager {
	public static BufferedReader br;
	public static File inFile;
	public static String Data;
	public static void ReaderTxt(String fileName, String filePath){
		
		inFile = new File(filePath+"/"+fileName);
		if(inFile.exists())
		{
			try{
				br = new BufferedReader(new FileReader(inFile));
				
			}catch(IOException e){
				System.out.println(e);
			}
		}if(!inFile.exists())
		{
		}
	}
}
