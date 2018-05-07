package com.accenture.skill.utils;

import java.io.File;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class CaptureScreenshot {
	
	 public static void takeSnapShot(WebDriver driver,String fileWithPath,ITestResult result) {
		 try {
			 TakesScreenshot scrShot =((TakesScreenshot)driver);
			 File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			 File DestFile= new File(fileWithPath+result.getName()+".png");
			 FileUtils.copyFile(SrcFile, DestFile);
		 } catch (Exception e){
			 e.printStackTrace();
			 System.out.println("takeSnapShot exception = "+e);
		 }
	 }
		
}