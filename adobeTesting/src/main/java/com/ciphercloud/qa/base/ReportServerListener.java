package com.ciphercloud.qa.base;

import java.util.Calendar;
import java.util.TimeZone;


public class ReportServerListener{

	public void test(){

		try{
			
			//String executionId = getUniqueExecutionId();
			String executionId = "20151012092406972";

			//String serverUrl = "http://192.168.224.193:8080/ReportServer/HandleRequestServlet";
			//String serverUrl = "http://localhost:8080/ReportServer/HandleRequestServlet";
			
			String serverUrl = "http://192.168.224.193:8080/ReportServerDemo/HandleRequestServlet";
			String editServerUrl = "http://192.168.224.193:8080/ReportServerDemo/HandleGatewayLogRequestServlet";
			//String serverUrl = "http://172.16.13.112:8080/TestReports2/HandleRequestServlet";
			//String serverUrl = "http://192.168.224.193:8080/ReportServer/HandleRequestServlet";
 		NonFormFileUploader resultDataTransfer = new NonFormFileUploader(serverUrl,editServerUrl);
				int methodTestCaseCount =1;
				
				
//				NonFormFileUploader resultDataEdit = new NonFormFileUploader(serverUrl,null);
//				resultDataTransfer.sendData(filename, appName, buildName,userName,
//						mName, csName, timeOut, paramCount, exTime, params, exception,
//						tResult, methodTestCaseCount, cycleName,orgName , instanceName);

				// PASSED
				
				resultDataTransfer.sendData(null, "IgnoreTestProject20", "0999_99_999","kumar5",
						"method-6", "amisc-module-03", "1020", "1", "1060", "Sample Parameters 12345-2", "java.lang.TestingExceptionNONE",
						"START", methodTestCaseCount, "Beta1","qatest3-qa9%40ciphercloud.com" , "192.168.101.111", "2017012092406974","gatewaylog","1");

//				Thread.sleep(10000);
//				resultDataEdit.editGatewayLog("IgnoreTestProject20","0999_99_999","method-01" ,"amisc-module-01" ,"Beta1", executionId, "wrong work","1");
//				
//				resultDataTransfer.sendData(null, "SFDC_Stabilization", "0916_02_292","kumar2",
//				"dummymethod-02", "MC_Test1", "1020", "1", "1060", "Sample Parameters 12345", "java.lang.TestingException",
//				"FAILED", methodTestCaseCount, "Beta1","qatest2-qa9%40ciphercloud.com" , "192.168.101.138");
				
				// More scenarios:
				// SCENARIO 1 : Check fileName / imageName - later
				//

			}catch(Exception e){
				e.printStackTrace();
			}

	}
	
	private static String getUniqueExecutionId() {
		Calendar cal = Calendar.getInstance();
		TimeZone tz = TimeZone.getTimeZone("GMT+05:30");
		cal.setTimeZone(tz);
		
		StringBuffer strBuff = new StringBuffer();
		strBuff.append(cal.get(Calendar.YEAR));
		strBuff.append(getTwoDigitFormattedValue((cal.get(Calendar.MONTH) + 1)));
		strBuff.append(getTwoDigitFormattedValue(cal.get(Calendar.DAY_OF_MONTH)));
		strBuff.append(getTwoDigitFormattedValue(cal.get(Calendar.HOUR_OF_DAY)));
		strBuff.append(getTwoDigitFormattedValue(cal.get(Calendar.MINUTE)));
		strBuff.append(getTwoDigitFormattedValue(cal.get(Calendar.SECOND)));
		strBuff.append(getThreeDigitFormattedValue(cal.get(Calendar.MILLISECOND)));
		
		return strBuff.toString();
	}	
	
	private static String getTwoDigitFormattedValue(int val) {
		if (val<=0) {
			return "00";
		} else if (val <10) {
			return ("0" + val);
		} else {
			return ("" + val);
		}
	}
	
	private static String getThreeDigitFormattedValue(int val) {
		if (val<=0) {
			return "000";
		} else if (val <10) {
			return ("00" + val);
		} else if (val >=10 && val <100) {
			return ("0" + val);
		} else {
			return ("" + val);
		}
	}
}


