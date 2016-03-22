package com.ciphercloud.qa.driver;

import org.openqa.selenium.WebDriver;

public class DriverFactory implements IDriverProvider{
	BrowserFactory bFactory=new BrowserFactory(); 
	//Creating Driver objects
	private IDriverProvider driverProvider;
	//private IBrowserProvider browserProvider;
	private WebDriver driver;
	public static String nameBrowser;
	
	
	
	public IDriverProvider getBrowser(){
		if(driverProvider==null){
			driverProvider=bFactory.getBrowserType();
		}
		return driverProvider;
	}
	public IDriverProvider getBrowser(String browser,String type){
		if(driverProvider==null){
			driverProvider=bFactory.browserType(type);
		}
		return driverProvider;
	}
	
	public IDriverProvider getBrowser(String browser){
		if(driverProvider==null){
			driverProvider=bFactory.getBrowserType();
		}
		return driverProvider;
	}
	
	public IBrowserProvider getDriverFactory(){
		
		return bFactory.getDriverType();
	}
	
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		//if(driver==null){
			IBrowserProvider driverFact=getDriverFactory();
			IDriverProvider browser=getBrowser();
			driver=driverFact.getCurrentDriver(browser);
			nameBrowser=browser.getBrowserName();
			//System.out.println("browser"+nameBrowser);
		//}
		return driver;
	}

	public WebDriver getDriver(String browserName) {
		// TODO Auto-generated method stub
		if(driver==null){
		
			IBrowserProvider driverFact=getDriverFactory();
			IDriverProvider browser=getBrowser();
			driver=driverFact.getCurrentDriver(browser,browserName);
			nameBrowser=browser.getBrowserName();
			//System.out.println("browser"+nameBrowser);
		}else{
			
		}
		return driver;
	}
	
	
	
	public WebDriver getDriver(String browserName, String type) {
		// TODO Auto-generated method stub
		if(driver==null){
		
			IBrowserProvider driverFact=getDriverFactory();
			IDriverProvider browser=getBrowser(browserName,type);
			driver=driverFact.getCurrentDriver(browser,browserName);
			nameBrowser=browser.getBrowserName();
			//System.out.println("browser"+nameBrowser);
		}else{
			
		}
		return driver;
	}

	public String getBrowserName() {
		// TODO Auto-generated method stub
		return nameBrowser;
	}


    public WebDriver getNewBrowser() {
            // TODO Auto-generated method stub
            IBrowserProvider driverFact=getDriverFactory();
            IDriverProvider browser=getBrowser();
            driver=driverFact.getNewDriver(browser);
            return driver;
    }

    public WebDriver getNewBrowser(String browser) {
            // TODO Auto-generated method stub
            IBrowserProvider driverFact=getDriverFactory();
          IDriverProvider browser1=getBrowser(browser);
            driver=driverFact.getNewDriver(browser1);
            return driver;
    }
}
