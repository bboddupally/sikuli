package com.ciphercloud.qa.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

//import com.ciphercloud.qa.listener.EventListener;

public class RemoteDriver implements IBrowserProvider{
	private WebDriver driver;
	private ThreadLocal<WebDriver> threadDriver=new ThreadLocal<WebDriver>();
	
	public RemoteDriver(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	public WebDriver getCurrentDriver(IDriverProvider driverProvider) {
		// TODO Auto-generated method stub
		if(driver==null){
			driver=driverProvider.getDriver();
		}
		return driver;
	}

	public WebDriver getCurrentDriver(IDriverProvider driverProvider,String browserName) {
		// TODO Auto-generated method stub
		if(driver==null){
			driver=driverProvider.getDriver(browserName);
			setThreadDriver();
		//	WebDriverEventListener eventListener = new EventListener();
			driver = new EventFiringWebDriver(driver);
	      // ((EventFiringWebDriver) driver).register(eventListener);
		}
		return driver;
	}
	
	public void setThreadDriver(){
		threadDriver.set(driver);
	}
	@Override
    public WebDriver getNewDriver(IDriverProvider driverProvider) {
            // TODO Auto-generated method stub
            driver=driverProvider.getNewBrowser();
            return driver;
    }

}