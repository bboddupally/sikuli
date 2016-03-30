package com.ciphercloud.qa.driver;

import com.ciphercloud.qa.dataprovider.Config;
import com.ciphercloud.qa.util.Constant;

public class BrowserFactory extends DriverTypeProvider{
	
	Config conf=new Config(Constant.CONFIGPATH);
	
	
	 public IDriverProvider browser;
	
	/**
	 * It will return type of Browser. If it is a remote browser return remote browser otherwise local browser
	 * @return
	 */
	
	public IDriverProvider getBrowserType(){
		if(conf.getValue("driver.browsertype").equalsIgnoreCase("local")){
			browser=new BrowserProvider();
			
			return browser;
			
		}
		else if(conf.getValue("driver.browsertype").equalsIgnoreCase("remote")){
			browser= new RemoteBrowserProvider();
			
			 return browser;
			 
		}
		return null;
	}
	
	

	public IDriverProvider browserType(String type){
		if(type.equalsIgnoreCase("local")){
			browser=new BrowserProvider();
			
			return browser;
			
		}
		else if(type.equalsIgnoreCase("remote")){
			browser= new RemoteBrowserProvider();
			
			 return browser;
			 
		}
		return null;
	}
	
	
	
	
	
	
	
	/**
	 * to get driver type. It will return different types of driver as per the requirement
	 */
	
	public IBrowserProvider getDriverType() {
		// TODO Auto-generated method stub
		if(conf.getValue("driver.drivertype").equalsIgnoreCase("local")){
			//System.out.println("local driver called");
			return new LocalDriver(null);
		}
		else if(conf.getValue("driver.drivertype").equalsIgnoreCase("remote")){
			//System.out.println("remote driver called");
			return new RemoteDriver(null);
		}
		else if (conf.getValue("driver.drivertype").equalsIgnoreCase("thread")){
			//System.out.println("thread driver called");
			return new ThreadDriver(null);
		}
		
		return null;
	}
	

}
