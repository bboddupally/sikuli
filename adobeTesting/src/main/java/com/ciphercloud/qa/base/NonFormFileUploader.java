package com.ciphercloud.qa.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

public class NonFormFileUploader {
	static final int BUFFER_SIZE = 4096;
	static String reportServerUrl = null;
	private String editGatewayLogReportServerUrl;

	
	

	public NonFormFileUploader(String serverUrl,String editGatewayLogReportServerUrl) throws FileNotFoundException,IOException {
		this.reportServerUrl = serverUrl;
		this.editGatewayLogReportServerUrl = editGatewayLogReportServerUrl;
		System.out.println("connecting to report server.................");
		
	}
	
	
	public static void editGatewayLog(String appName,String buildName,String MethodName,
			String className,String cycleName,String executionId, String gatewayLog,String iterationCount) throws IOException{
		
		
		
		
		URL url = new URL(reportServerUrl);

		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		
	
		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true);
		httpConn.setRequestMethod("POST");
		
		
		
		httpConn.setRequestProperty("appName", appName);
		
		httpConn.setRequestProperty("SheetName", className);
		httpConn.setRequestProperty("MethodName", MethodName);
		//httpConn.setRequestProperty("ClassName", "SFDC_"+className);
		httpConn.setRequestProperty("ClassName", className);
		httpConn.setRequestProperty("cycleName", cycleName);
		httpConn.setRequestProperty("buildName", buildName);
		
		//httpConn.setRequestProperty("iterationCount",iterationCount);
		// NEW - INCLUDE THIS
		httpConn.setRequestProperty("executionId", executionId);
		httpConn.setRequestProperty("gatewayLog", gatewayLog);
		httpConn.setRequestProperty("iterationCount", iterationCount);
		
		
		// opens output stream of the HTTP connection for writing data
		OutputStream outputStream = httpConn.getOutputStream();

		// Opens input stream of the file for reading data
		
			outputStream.write("string=".getBytes());
			outputStream.flush();
			outputStream.close();
		
		int responseCode = httpConn.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			// reads server's response
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream()));
			String response = reader.readLine();
			System.out.println("Server's response: " + response);
		} else {
			System.out.println("Server returned non-OK code: " + responseCode);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	public static void sendData(String fileName, String appName,
			String buildName, String userName, String MethodName,
			String className, String MethodTimeout,
			String MethodParameterCount, String MethodExecutionTime,
			String Parameters, String ExceptionThrown, String MethodResult,
			int methodTestCaseCount, String cycleName, String orgName, String instanceName, String executionId, String gatewayLog,String iterationCount) throws IOException {

		// creates a HTTP connection
		//URL url = new URL("http://192.168.224.193:8080/ReportServer/HandleRequestServlet");
		URL url = new URL(reportServerUrl);

		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		
	
		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true);
		httpConn.setRequestMethod("POST");
		String temp = fileName;
		
		// sets file name as a HTTP header
		if (fileName != null) {
			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
		}
		
		httpConn.setRequestProperty("fileName", fileName);
		httpConn.setRequestProperty("appName", appName);
		httpConn.setRequestProperty("buildName", buildName);
		httpConn.setRequestProperty("userName", userName);
		httpConn.setRequestProperty("SheetName", className);
		httpConn.setRequestProperty("MethodName", MethodName);
		//httpConn.setRequestProperty("ClassName", "SFDC_"+className);
		httpConn.setRequestProperty("ClassName", className);
		httpConn.setRequestProperty("MethodTimeout", MethodTimeout);
		httpConn.setRequestProperty("MethodParameterCount",	MethodParameterCount);
		httpConn.setRequestProperty("MethodExecutionTime", MethodExecutionTime);
		httpConn.setRequestProperty("Parameters", Parameters);
		httpConn.setRequestProperty("ExceptionThrown", ExceptionThrown);
		httpConn.setRequestProperty("MethodResult", MethodResult);
		httpConn.setRequestProperty("methodTestCaseCount", methodTestCaseCount+"");
		httpConn.setRequestProperty("cycleName", cycleName);
		httpConn.setRequestProperty("orgName", orgName);
		httpConn.setRequestProperty("instanceName", instanceName);
		//httpConn.setRequestProperty("iterationCount",iterationCount);
		// NEW - INCLUDE THIS
		httpConn.setRequestProperty("executionId", executionId);
		httpConn.setRequestProperty("gatewayLog", gatewayLog);
		httpConn.setRequestProperty("iterationCount", iterationCount);
		
		
		
		// opens output stream of the HTTP connection for writing data
		OutputStream outputStream = httpConn.getOutputStream();

		// Opens input stream of the file for reading data
		if (fileName != null) {
			File uploadFile = new File(temp);
			if(uploadFile.exists()) {
				FileInputStream inputStream = new FileInputStream(uploadFile);
	
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
	
				System.out.println("Start writing data...");
	
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
	
				System.out.println("Data was written.");
				outputStream.close();
				inputStream.close();
			}else {
				outputStream.write("string=".getBytes());
				outputStream.flush();
				outputStream.close();
			}
		} else {
			outputStream.write("string=".getBytes());
			outputStream.flush();
			outputStream.close();
		}
		
		int responseCode = httpConn.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			// reads server's response
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream()));
			String response = reader.readLine();
			System.out.println("Server's response: " + response);
		} else {
			System.out.println("Server returned non-OK code: " + responseCode);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
		if (val <= 0) {
			return "00";
		} else if (val < 10) {
			return ("0" + val);
		} else {
			return ("" + val);
		}
	}

	private static String getThreeDigitFormattedValue(int val) {
		if (val <= 0) {
			return "000";
		} else if (val < 10) {
			return ("00" + val);
		} else if (val >= 10 && val < 100) {
			return ("0" + val);
		} else {
			return ("" + val);
		}
	}
	public static void main(String args[]) {
		
		String fScreen = null;
		Properties ReportServer_prop, version_props = null;
		FileInputStream fis, fis1 = null;
		 String buildName = "";
		 String cycleName="";
		 String appName = "";
		 String userName = "";
		 String orgName ="";
		 String instanceName = "";
		 String executionID ="";
		 String serverUrl = "";
		 String editServerUrl = "";
		try {
				
			ReportServer_prop = new Properties();
			version_props = new Properties();
			fis = new FileInputStream(System.getProperty("user.dir") + "/config/config.properties");
			fis1 = new FileInputStream(System.getProperty("user.dir") + "/version-snow.properties");
			ReportServer_prop.load(fis);
			version_props.load(fis1);
			buildName = version_props.getProperty("snowbuild");
			cycleName = ReportServer_prop.getProperty("cycleName");
			//serverUrl = URLEncoder.encode(ReportServer_prop.getProperty("serverUrl"), "UTF-8");
			serverUrl = "http://192.168.224.193:8080//ReportServer//HandleRequestServlet";
			appName = ReportServer_prop.getProperty("appName");
			userName = ReportServer_prop.getProperty("userName");
			orgName = ReportServer_prop.getProperty("defaultUserName");
			instanceName = ReportServer_prop.getProperty("instanceName");
			executionID = getUniqueExecutionId();
			fis.close();
			fis1.close();
			String exception1 = "java.lang.IllegalArgumentException: Illegal character(s) in message header value: org.openqa.selenium.TimeoutException: Timed out after 60 seconds waiting for presence of element located by: By.linkText: CasesBuild info: version: '2.46.0', revision: '61506a4624b13675f24581e453592342b7485d71', time: '2015-06-04 10:22:50'System info: host: 'TigerTeamUnit3', ip: '192.168.102.159', os.name: 'Windows 7', os.arch: 'amd64', os.version: '6.1', java.version: '1.8.0_51'Driver info: org.openqa.selenium.firefox.FirefoxDriverCapabilities [{applicationCacheEnabled=true, rotatable=false, handlesAlerts=true, databaseEnabled=true, version=40.0.2, platform=WINDOWS, nativeEvents=false, acceptSslCerts=true, webStorageEnabled=true, locationContextEnabled=true, browserName=firefox, takesScreenshot=true, javascriptEnabled=true, cssSelectorsEnabled=true}]Session ID: 2470cb01-c07a-45ff-9fc5-aa41c4307cfb";
		
			String filename = "C:\\Users\\CipherCloud\\Documents\\screenshots.zip" ;
			
			NonFormFileUploader resultDataTransfer = new NonFormFileUploader(serverUrl,editServerUrl);
			int methodTestCaseCount =1;
			/*resultDataTransfer.sendData(filename, "ksTestProject8", "0999_99_999","karunaTest2",
					"CreateAccount", "AccountsModule", "1020", "1", "1060", "Sample Parameters 12345-2", "java.lang.TestingExceptionNONE",
					"FAILED", methodTestCaseCount, "BetaDemo","qatest3-qa9%40ciphercloud.com" , "192.168.101.111", executionId,"test gatewaylog....");*/
			
			resultDataTransfer.sendData(filename, appName, buildName,userName,
					"CreateAccount", "AccountsModule", "1020", "1", "1060", "Sample Parameters 12345-2", "java.lang.TestingExceptionNONE",
					"FAILED", methodTestCaseCount, cycleName,orgName , instanceName, executionID,"test gatewaylog....","1");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}