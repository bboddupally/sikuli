package com.ciphercloud.qa.driver;

public abstract class DriverTypeProvider {
	public static String browserName;
	public abstract IDriverProvider getBrowserType();
	public abstract  IBrowserProvider getDriverType();
	

}