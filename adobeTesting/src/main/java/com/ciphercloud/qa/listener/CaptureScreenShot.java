package com.ciphercloud.qa.listener;
	
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.ciphercloud.qa.base.BasePage;

import com.ciphercloud.qa.dataprovider.Config;
import com.ciphercloud.qa.util.Constant;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CaptureScreenShot implements ITestListener,IInvokedMethodListener,ISuiteListener{
	
	final static Logger logback = LoggerFactory.getLogger(CaptureScreenShot.class);
	//Config conf=new Config(Constant.CONFIGPATH);
	private Document document = null;
	PdfPTable successTable = null, failTable = null, skippedTable=null, notRunTable=null,totalTestCase=null;
	private HashMap<Integer, Throwable> throwableMap = null;
	private int nbExceptions = 0;
	int totalRun=0;
	String browserName;
	int totalPassed=0,totalFailed=0,totalSkipped=0,totalNotRun=0;
	static String methodName;
	static BasePage b=new BasePage();
	List<String> notRunMethod=new ArrayList<String>();
	
	/**
	 * creating constructor
	 */
	public CaptureScreenShot(){
		this.document = new Document();
		this.throwableMap = new HashMap<Integer, Throwable>();
	}
	

	
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		logback.info("About to end executing Test " + arg0.getName(), true);
		Reporter.log("About to end executing Test " + arg0.getName(), true);
	}
	


	public  void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		logback.info("About to begin executing Test " + context.getName(), true);
		Reporter.log("About to begin executing Test " + context.getName(), true);
		
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	   public	void deleteScreenShotFolder(File file) {
   	    File[] contents = file.listFiles();
   	    if (contents != null) {
   	        for (File f : contents) {
   	            deleteScreenShotFolder(f);
   	        }
   	    }
   	    file.delete();
   	}
	
	public  void onTestFailure(ITestResult result) {
		
		 try {
	            Robot robot;
				robot = new Robot();
	            String format = "jpg";
	            String fileName = System.getProperty("user.dir")+"/AdobeResource/screenshot/"+result.getName()+"." + format;
	            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
	            ImageIO.write(screenFullImage, format, new File(fileName));
	             
	            System.out.println("A full screenshot saved!");
	        } catch (IOException  | AWTException e) {
	            System.err.println(e);
	        }
		
	}


	public  void onTestSkipped(ITestResult result) {}
	
	//@Test(timeOut = 240000, dataProvider = "getTestData", dataProviderClass=CaptureScreenShot.class)
	public  void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		totalRun++;
	
	}

	
	public  void onTestSuccess(ITestResult result) {}
	
	


	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());
		logback.info(textMsg, true);
		Reporter.log(textMsg, true);
	}


	public void beforeInvocation(IInvokedMethod method, ITestResult arg1) {}
	
	
	
	/**
	 * user defined methods
	 * @param result
	 */
	
	public String getCurrentTimeInstance(){
		Calendar calendar = Calendar.getInstance();
		String currentTimeInstance =  "-"                           
	        + calendar.get(Calendar.YEAR) + "-"
	        + calendar.get(Calendar.MONTH) + "-"
	        + calendar.get(Calendar.DAY_OF_MONTH) + "-"
	        + calendar.get(Calendar.HOUR_OF_DAY) + "-"
	        + calendar.get(Calendar.MINUTE) + "-"
	        + calendar.get(Calendar.SECOND) + "-"
	        + calendar.get(Calendar.MILLISECOND);
		return currentTimeInstance;
		
	}
	// This will provide the information on the test
	 
	private void printTestResults(ITestResult result) {}
	
	// This will return method names to the calling function
	 
	private String returnMethodName(ITestNGMethod method) {
 
		return method.getRealClass().getSimpleName() + "." + method.getMethodName();
 
	}
	
	public void onFinish(ISuite suite){}
	
	public void onStart(ISuite suite){}
	
	



	
	

}
