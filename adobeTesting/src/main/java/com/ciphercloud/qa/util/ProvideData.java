package com.ciphercloud.qa.util;

import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

import com.ciphercloud.qa.dataprovider.Xls_Reader;

public class ProvideData {

	
	public static Xls_Reader snow_testdata_xls = null;
	
/*	
	@DataProvider
	public static Object[][] problemSearchDataprovider(Method method) {
	String methodName=method.getName();
		if(TestBase.instances .toLowerCase().equals("fujji")){
			snow_testdata_xls=new Xls_Reader(Constant.SNOWDATASHEET_FUJJI);
		}else if(TestBase.instances .toLowerCase().equals("euraka")){
			snow_testdata_xls=new Xls_Reader(Constant.SNOWDATASHEET_EURAKA);
		}
		return TestUtil.getdata(snow_testdata_xls, methodName.trim().split("_")[1].trim(), methodName,"Filter Test Cases");
	}
	
	*/

	
}
