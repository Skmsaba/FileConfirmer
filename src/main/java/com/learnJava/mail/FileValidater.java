package com.learnJava.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileValidater {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
        Properties prop=new Properties();
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\mail\\src\\main\\java\\config.properties");
		
		prop.load(fis);
		
		String fileNameNew = prop.getProperty("fileNameNew");
		String directory = prop.getProperty("directory");
		String regex3 = prop.getProperty("regex3");
		String headerOriginal = prop.getProperty("headerOriginal");
		String output3 = prop.getProperty("output3");
		String output4 = prop.getProperty("output4");
		
		String fileName=null,filePath=null,line=null,header=null;
		String trailer=null;
		int lineCount=0,overallLength=0,trailerSum=0;
		List<String> lineList=new ArrayList<String>();
		
		try {
			File path=new File(directory);
			
			File[] files=path.listFiles();
			
			for(File file:files) {
				fileName=file.getName();
//				System.out.println(fileName);
				filePath=file.getAbsolutePath();
			}
			
			Pattern pattern=Pattern.compile(regex3);
			Matcher match=pattern.matcher(fileName);
			
			if(match.find()) {
				
				if(fileName.equals(fileNameNew)) {
					
					BufferedReader br=new BufferedReader(new FileReader(filePath));
					while((line=br.readLine())!=null) {
						 lineList.add(line);
						 lineCount++;
					}
//					System.out.println(lineCount);
					
					String [] lineArray = lineList.toArray(new String[0]);
					header=lineArray[0];
					trailer=lineArray[lineCount-1];
					
					header=header.replaceAll(" + "," ");
					trailer = trailer.replaceAll(" + "," ");
					String[] trailerArr = trailer.split(" ");
					int [] trailerArray = new int[trailerArr.length];
					
					for(int n=0;n<trailerArray.length;n++) {
						
						trailerArray[n] = Integer.valueOf(trailerArr[n]);						
					}

					for(int i=0;i<trailerArray.length;i++) {
						
						trailerSum=trailerSum + trailerArray[i];
						
					}
					
//					System.out.println(trailerSum);
//					
//					System.out.println(header);
//					System.out.println(trailer);
					
					if(header.equals(headerOriginal)) {
						
						for(int j=1;j<lineCount-1;j++) {
							
							String [] words=lineArray[j].replaceAll(" + "," ").split(" ");
							
							overallLength=overallLength+words.length;
							
						}
//						System.out.println(overallLength);
					}

					br.close();
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(overallLength == trailerSum) {
			
			System.out.println(output3);
			
		}else {
			
			System.out.println(output4);
			
		}

	}

}
