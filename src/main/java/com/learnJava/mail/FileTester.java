package com.learnJava.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTester {

	public String fileConfirmer() throws IOException {
		// TODO Auto-generated method stub
		
        Properties prop=new Properties();
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\mail\\src\\main\\java\\config.properties");
		
		prop.load(fis);

		String fileName,output = null;
		
		int lineCount = 0,k=0;

		try {
			File path = new File("C:\\Users\\Admin\\eclipse-workspace\\mail");

			File[] files = path.listFiles();

			for (File file : files) {

				fileName = file.getName();

				String regex = "^RWA_MARKETRISK_\\d{4}(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01]).txt$";
				Pattern pattern = Pattern.compile(regex);
				Matcher match = pattern.matcher(fileName);

				if (match.find()) {

					BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Admin\\eclipse-workspace\\mail\\"+fileName));

					String line = null;

					while ((line = br.readLine()) != null) {

						lineCount++;
						
						line=line.replaceAll("[^\\d]", " ");
						line=line.trim();
						line=line.replaceAll(" + ", " ");
										
						String regex2="\\d";
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
					
					System.out.println("Completed");
					
					br.close();
					
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
