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
import com.ciphercloud.qa.driver.BrowserProvider;
import com.ciphercloud.qa.driver.DriverFactory;
import com.ciphercloud.qa.driver.RemoteBrowserProvider;
import com.ciphercloud.qa.util.Constant;

public class TestBase {
	protected	WebDriver  driver_direct =null;
	protected WebDriver driver_proxy=null;
	protected DriverFactory driverFactory=new DriverFactory();
	protected Config conf=new Config(Constant.CONFIGPATH);
	
	public TestBase(){
		
	}
	public  String instances;
    public WebDriver getDriver() {
    	return driver_proxy;
    }

    public String proxy=null;
    public String actualurl=null;
	public Xls_Reader snow_testdata_xlsBase= null;
    
    @Parameters({"browser", "instance" ,"multiDrive","execute"})
    @BeforeSuite
    public void beforesuite(@Optional()String browserName, @Optional()String instance, @Optional()String multiDrive , @Optional()String execute) throws IOException{
    	try {
			//clean();
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	createNewdriver(browserName, instance, multiDrive, execute);}
    
  

    @Parameters({"browser", "instance" ,"multiDrive","execute"})
    @BeforeClass
	
	public void setUp(@Optional()String browserName, @Optional()String instance,@Optional()String multiDrive, @Optional()String execute ) throws IOException {
    	try {
			createNewdriver(browserName, instance, multiDrive, execute);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			createNewdriver(browserName, instance, multiDrive, execute);
		}}



	/**
	 * @param browserName
	 * @param instance
	 * @param multiDrive
	 * @param execute
	 * @throws IOException
	 */
	public void createNewdriver(String browserName, String instance, String multiDrive, String execute)
			throws IOException {
		FileUtils.deleteDirectory(new File(Constant.SCREENSHOT));
		FileUtils.deleteDirectory(new File(Constant.DOWNLOADS));
		FileUtils.deleteDirectory(new File(Constant.TMPEUREK));
		FileUtils.deleteDirectory(new File(Constant.TMPFUJI));
		FileUtils.deleteDirectory(new File(Constant.TMPGENEVA));
File tmp=null;
    	if(browserName!=null){
    	//BrowserProvider bp = new BrowserProvider();
    		if(instance.toLowerCase().equals("fujji")){
    			tmp = new File(Constant.TMPFUJI);
    			FileUtils.forceMkdir(tmp);
    			instances="fujji";
    			snow_testdata_xlsBase=new Xls_Reader(Constant.SNOWDATASHEET_FUJJI);
    	    	proxy=conf.getValue("proxy_url_Fuji");
    	    	actualurl=conf.getValue("direct_url_Fuji");
    		}else if(instance.toLowerCase().equals("euraka")){
    			instances="euraka";
    			snow_testdata_xlsBase=new Xls_Reader(Constant.SNOWDATASHEET_EURAKA);
    			tmp = new File(Constant.TMPEUREK);
    			FileUtils.forceMkdir(tmp);
    			proxy=conf.getValue("proxy_url_Eureka");
    	    	actualurl=conf.getValue("direct_url_Eureka");
    		}else if(instance.toLowerCase().equals("geneva")){
    			instances="geneva";
    			snow_testdata_xlsBase=new Xls_Reader(Constant.SNOWDATASHEET_GENEVA);
    			tmp = new File(Constant.TMPGENEVA);
    			FileUtils.forceMkdir(tmp);
    			proxy=conf.getValue("proxy_url_Geneva");
    	    	actualurl=conf.getValue("direct_url_Geneva");
    		
    		}
    	if(driver_proxy==null && driver_direct==null){
		// TODO Auto-generated method stub
    	createDriver(browserName, multiDrive,execute);
    	
    	
    	//thread.sleep
    	try {
    		driver_proxy.manage().deleteAllCookies();
			driver_proxy.manage().window().maximize();
			driver_proxy.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			if(driver_proxy==null){
	    	createDriver(browserName, multiDrive,execute);
			}else{
				
			}
	    	try {
	    		driver_proxy.manage().deleteAllCookies();
				driver_proxy.manage().window().maximize();
				driver_proxy.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}}
	}


	
	

	/**
	 * @param browserName
	 * @param multiDrive
	 */
	public void createDriver(String browserName, String multiDrive, String execute) {
		if(conf.getValue("tesng.parameter").equalsIgnoreCase("YES")){
			
			if(execute!=null && execute.equals("remote")){
	    		driver_proxy=driverFactory.getDriver(browserName,execute);
	    		if(driver_proxy==null)
	            	driver_proxy=driverFactory.getDriver(browserName,execute);
	    		driver_proxy.manage().deleteAllCookies();

			}else{
	    		driver_proxy=driverFactory.getDriver(browserName);
	    		driver_proxy.manage().deleteAllCookies();
			}
    		String type="remote";
    		

    		//driver_direct_url=driverFactory.getDriver(browserName);
    		if(multiDrive==null || multiDrive.equals("enable"))
    			if(execute!=null && execute.equals("remote")){
    				driver_direct=  new RemoteBrowserProvider().getBrowser(browserName);
   	    		 if(driver_direct==null)
       	    	 driver_direct=  new RemoteBrowserProvider().getBrowser(browserName);
   	    		driver_direct.manage().deleteAllCookies();
    		
    			}else{
    				 driver_direct=  new BrowserProvider().getBrowser(browserName);
    				 driver_direct.manage().deleteAllCookies();
    	    			//	new BrowserProvider().getBrowser(browserName);
    			}
    	}
    	else{
    		driver_proxy=driverFactory.getDriver();
    		if(multiDrive==null || multiDrive.equals("enable"))
    		 driver_direct= driverFactory.getNewBrowser();
    		driver_direct.manage().deleteAllCookies();
    	}
	}

   
	
	@AfterClass
	public void tearDown() {
		// TODO Auto-generated method stub
		if (driver_proxy != null)
	    {
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
		
	}
	  @AfterSuite
	    public void aftersuite() throws IOException{
			// TODO Auto-generated method stub
			if (driver_proxy != null)
		    {
		            try
		            {
		                driver_proxy.quit();
                        if(driver_direct!=null)
		                driver_direct.quit();
		            }
		            catch (WebDriverException e) {
		                //System.out.println("***** CAUGHT EXCEPTION IN DRIVER TEARDOWN *****");
		                //System.out.println(e);
		            }finally{
		            	
		            	clean();
		            	FileUtils.deleteDirectory(new File(Constant.TMPEUREK));
		            	FileUtils.deleteDirectory(new File(Constant.TMPFUJI));
		        		//clean();

		            }

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
