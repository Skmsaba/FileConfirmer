package com.learnJava.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTester {

	public String fileConfirmer() throws IOException {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf=new SimpleDateFormat();
		
		Date date=new Date();
		
		String currentDate=sdf.format(date);
		
        Properties prop=new Properties();
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\mail\\src\\main\\java\\config.properties");
		
		prop.load(fis);
		
		String directory = prop.getProperty("directory");
		String filePath = prop.getProperty("filePath");
		String regex = prop.getProperty("regex");
		String regex2 = prop.getProperty("regex2");
		final String fileNameOrig = prop.getProperty("fileNameOrig");


		String fileName,output = null;
		
		int lineCount = 0,k=0;

		try {
			File path = new File(directory);

			File[] files = path.listFiles();

			for (File file : files) {

				fileName = file.getName();

				Pattern pattern = Pattern.compile(regex);
				Matcher match = pattern.matcher(fileName);

				if (match.find()) {
					
					System.out.println(fileName);
					
					System.out.println(fileNameOrig);
					
					System.out.println(fileNameOrig == fileName);
					
					if(fileName.matches(fileNameOrig)) {
						
						System.out.println("Filename and FileOrig Match");
						
						BufferedReader br = new BufferedReader(new FileReader(filePath));

						String line = null;

						while ((line = br.readLine()) != null) {

							lineCount++;
							
							line=line.replaceAll("[^\\d]", " ");
							line=line.trim();
							line=line.replaceAll(" + ", " ");
											
							Pattern pattern2=Pattern.compile(regex2);
							Matcher match2=pattern2.matcher(line);
							
							if(match2.find()) {
								
								k=Integer.parseInt(line);
								
								System.out.println(k);
							}

						}
						
						System.out.println("lineCount is"+ lineCount);
						
						if(k==lineCount) {
							
							output = "Linecount Verified in given file\nCount found OK";
							
						}else {
							
							output = "Linecount Verified in given file\nCount found Not OK";
							
						}
						
						System.out.println(output);
						
						br.close();
						
					}

					System.out.println("Completed");
					
					return output;
				
				}
								
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;

	}

}
