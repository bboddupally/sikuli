package com.ciphercloud.qa.driver;

import org.openqa.selenium.WebDriver;

public interface IBrowserProvider {
	
	public WebDriver getCurrentDriver(IDriverProvider driverProvider);
	
	public WebDriver getCurrentDriver(IDriverProvider driverProvider,String browserName);

	public WebDriver getNewDriver(IDriverProvider browser);

}
