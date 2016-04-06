package com.ciphercloud.qa.base;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ciphercloud.qa.dataprovider.Config;
import com.ciphercloud.qa.dataprovider.Xls_Reader;

import com.ciphercloud.qa.util.Constant;

public class TestBase {
	protected	WebDriver  driver_direct =null;
	protected WebDriver driver_proxy=null;
	
	public TestBase(){
		
	}
	public  String instances;
    public WebDriver getDriver() {
    	return driver_proxy;
    }

    public String proxy=null;
    public String actualurl=null;
	public Xls_Reader snow_testdata_xlsBase= null;
    
  
    @BeforeSuite
    public void beforesuite(@Optional()String browserName, @Optional()String instance, @Optional()String multiDrive , @Optional()String execute) throws IOException{
    	try {
			//clean();
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		}
    	//createNewdriver(browserName, instance, multiDrive, execute);}
    
  

  
    @BeforeClass
	
	public void setUp(@Optional()String browserName, @Optional()String instance,@Optional()String multiDrive, @Optional()String execute ) throws IOException {
    	try {
			//createNewdriver(browserName, instance, multiDrive, execute);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//	createNewdriver(browserName, instance, multiDrive, execute);
		}}



   
	
	@AfterClass
	public void tearDown() {
		// TODO Auto-generated method stub
		
	            try
	            {
	                driver_proxy.quit();
                    if(driver_direct!=null)
	                driver_direct.quit();
	            }
	            catch (WebDriverException e) {
	                //System.out.println("***** CAUGHT EXCEPTION IN DRIVER TEARDOWN *****");
	                //System.out.println(e);
	            }finally{/*
	        		try {
						try {
							Runtime.getRuntime().exec("taskkill /f /im " + "firefox" + ".exe");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						File file = new File(System.getProperty("java.io.tmpdir"));
						FileUtils.cleanDirectory(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}

	            */}

	   
		
	}
	  @AfterSuite
	    public void aftersuite() throws IOException{
			// TODO Auto-generated method stub
		
		            try
		            {
		               // driver_proxy.quit();
                     //   if(driver_direct!=null)
		               // driver_direct.quit();
		            }
		            catch (WebDriverException e) {
		                //System.out.println("***** CAUGHT EXCEPTION IN DRIVER TEARDOWN *****");
		                //System.out.println(e);
		            }finally{
		            	
		            	clean();
		            	FileUtils.deleteDirectory(new File(System.getProperty("user.dir")+"/AdobeResource/screenshot"));
		            	
		        		//clean();

		            }

		   
		
	    	
	    }



	/**
	 * 
	 */
	public void clean() {
		File file ;
		try {
			file = new File(System.getProperty("java.io.tmpdir"));
			try {
				FileUtils.cleanDirectory(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			Thread.sleep(1000);
			Runtime.getRuntime().exec("taskkill /f /im " + "firefox" + ".exe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
try {

			Runtime.getRuntime().exec("taskkill /f /im " + "chromedriver" + ".exe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
