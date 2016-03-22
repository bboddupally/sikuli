package com.ciphercloud.qa.listener;

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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;


public class NonFormFileUploader {
	static final int BUFFER_SIZE = 4096;
	static String reportServerUrl = null;

	public NonFormFileUploader(String serverUrl) {
		reportServerUrl = serverUrl;
	}

	public static void sendData(String fileName, String appName,
			String buildName, String userName, String MethodName,
			String className, String MethodTimeout,
			String MethodParameterCount, String MethodExecutionTime,
			String Parameters, String ExceptionThrown, String MethodResult,
			int methodTestCaseCount, String cycleName, String orgName, String instanceName, String executionId) throws IOException {

		// creates a HTTP connection
		
		URL url = new URL("http://192.168.224.193:8080/ReportServerDemo/HandleRequestServlet");
		
		//URL url = new URL(reportServerUrl + "/HandleRequestServlet");
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
		httpConn.setRequestProperty("executionId", executionId);
		
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

	public static void main(String args[]) {
		
		String fScreen = null;
		try {
			/*FileInputStream file2 = null;
			HSSFWorkbook workBook2 = null;
			try {
				file2 = new FileInputStream(new File(System.getProperty("user.dir")	+ "\\TestOutput\\Results\\Results.xls"));
				workBook2 = new HSSFWorkbook(file2);

			} catch (FileNotFoundException e) {
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int numberOfSheets = workBook2.getNumberOfSheets();
			for (int i = 1; i < numberOfSheets; i++) {
				HSSFSheet sheet = workBook2.getSheetAt(i);
				System.out.println("sheet name = "+ sheet.getSheetName());
				int totalRows = sheet.getLastRowNum();
				for (int j = 1; j <= totalRows; j++) {
					Row row = sheet.getRow(j);
					
					String MethodName = row.getCell(0).getStringCellValue();
					//String TestCaseID = row.getCell(1).getStringCellValue()!=null?row.getCell(1).getStringCellValue():"";
					String className = row.getCell(2).getStringCellValue();
					String MethodResult = row.getCell(3).getStringCellValue();
					String MethodTimeout = row.getCell(4).getStringCellValue();
					String MethodParameterCount = URLEncoder.encode(row.getCell(5).getStringCellValue() , "UTF-8");
					String MethodExecutionTime = URLEncoder.encode(row.getCell(6).getStringCellValue() , "UTF-8");
					String Parameters = "{editaccountType=0, accountWebsite=&Cipher#$.com, billingstate=British, billingcity=Providence, description=o-Daro, Harappa, and Lothal, and the coming of the Aryans. These two phases are usually , accountName=Altavista, billingzip=T3D 4V0, editshippingcountry=0, editbillingcity=0, accountFax=:9052478223, editdescription=0, editaccountName=Altavista, EventSubject=Call, industry=Entertainment, EditNote=Altavista, opportunityClosedDate=5/5/2016, employees=1234, editaccountPhone=0, editopportunityClosedDate=6/6/2016, editbillingstreet=0, Note=Altavista, annualRevenue=2222, caseOrigin=Email , contactLastName=Altavista, Title=Altavista, shippingstreet=P.O. Box 246, 6449 Magna. Avenue, TaskSubject=Send Letter, editbillingaddress=0, casesStatus=On Hold , editcasesStatus=Escalated, ClosecasesStatus=Closed, editshippingcity=0, filePath=files/accounts-10.xlsm, EditopportunityName=jojjo technologies, billingcountry=Nauru, editbillingzip=0, editaccountFax=0, Operator=equals, billingstreet=P.O. Box 246, 6449 Magna. Avenue, accountPhone=(234) 844-6946, ViewName=ViewAccountCustom, EditTitle=Completed, opportunityStage=Qualification, editaccountWebsite=0, FieldName=Account Name, editlookupaccountName=vijetha super market, editcaseOrigin=Phone, opportunityName=&Cipher#$, contactFirstName=Altavista, lookupaccountName=krithika, editcontactLastName=rajan, shippingcountry=WV, RunMode=Y, editindustry=0, editshippingstate=0, editemployees=0, billingaddress=222-2873 Convallis Rd., taskPriority=High, shippingcity=Providence, editbillingstate=0, editopportunityStage=Qualification, view=My Accounts, taskStatus=Completed, editannualRevenue=0, shippingzip=55044, editshippingzip=0, edilookupaccountName=0, editshippingstreet=0, accountType=Press, editbillingcountry=0, casesReason=Existing problem, shippingstate=KY}";
					
					
					if(row.getCell(7) != null){
						Parameters = URLEncoder.encode(row.getCell(7).getStringCellValue() , "UTF-8");
					} 
					
					String ExceptionThrown = "";
					if(row.getCell(8) != null){
						ExceptionThrown = URLEncoder.encode(row.getCell(8).getStringCellValue() , "UTF-8");
					} 					

					ExceptionThrown = ExceptionThrown.replaceAll("\n", "");
					fScreen = null;
					if(MethodResult.equalsIgnoreCase("FAILED")){						
						String temp = System.getProperty("user.dir") + File.separator + "screenshot" + File.separator + MethodName +".png";
						fScreen = new File(temp).getAbsolutePath();
					}
					Parameters = "{editaccountType=0, accountWebsite=&Cipher#$.com, billingstate=British, billingcity=Providence, description=o-Daro, Harappa, and Lothal, and the coming of the Aryans. These two phases are usually , accountName=Altavista, billingzip=T3D 4V0, editshippingcountry=0, editbillingcity=0, accountFax=:9052478223, editdescription=0, editaccountName=Altavista, EventSubject=Call, industry=Entertainment, EditNote=Altavista, opportunityClosedDate=5/5/2016, employees=1234, editaccountPhone=0, editopportunityClosedDate=6/6/2016, editbillingstreet=0, Note=Altavista, annualRevenue=2222, caseOrigin=Email , contactLastName=Altavista, Title=Altavista, shippingstreet=P.O. Box 246, 6449 Magna. Avenue, TaskSubject=Send Letter, editbillingaddress=0, casesStatus=On Hold , editcasesStatus=Escalated, ClosecasesStatus=Closed, editshippingcity=0, filePath=files/accounts-10.xlsm, EditopportunityName=jojjo technologies, billingcountry=Nauru, editbillingzip=0, editaccountFax=0, Operator=equals, billingstreet=P.O. Box 246, 6449 Magna. Avenue, accountPhone=(234) 844-6946, ViewName=ViewAccountCustom, EditTitle=Completed, opportunityStage=Qualification, editaccountWebsite=0, FieldName=Account Name, editlookupaccountName=vijetha super market, editcaseOrigin=Phone, opportunityName=&Cipher#$, contactFirstName=Altavista, lookupaccountName=krithika, editcontactLastName=rajan, shippingcountry=WV, RunMode=Y, editindustry=0, editshippingstate=0, editemployees=0, billingaddress=222-2873 Convallis Rd., taskPriority=High, shippingcity=Providence, editbillingstate=0, editopportunityStage=Qualification, view=My Accounts, taskStatus=Completed, editannualRevenue=0, shippingzip=55044, editshippingzip=0, edilookupaccountName=0, editshippingstreet=0, accountType=Press, editbillingcountry=0, casesReason=Existing problem, shippingstate=KY}";
					ExceptionThrown = "Selenium Exception...";
					NonFormFileUploader nf = new NonFormFileUploader("http:\\localhost:8080\\TestReports");
					nf.sendData(fScreen, URLEncoder.encode("ks-Salesforce","UTF-8"),
							URLEncoder.encode("build","UTF-8"),
							URLEncoder.encode("karuna","UTF-8"),
							URLEncoder.encode("TestMethod","UTF-8"),
							URLEncoder.encode("TestClassName","UTF-8"),
							URLEncoder.encode("18678","UTF-8"),
							"2",
							"18678", Parameters , ExceptionThrown, "Failed", 5, "testcycle2", "org@demo", "localinstance");
					System.out.println("======================Send data for record: "+ j +"  ==============================");
				}
			}
			*/
			
			String exception ="";
			System.out.println("length...." + exception.length());
			
			String exception1 = "java.lang.IllegalArgumentException: Illegal character(s) in message header value: org.openqa.selenium.TimeoutException: Timed out after 60 seconds waiting for presence of element located by: By.linkText: CasesBuild info: version: '2.46.0', revision: '61506a4624b13675f24581e453592342b7485d71', time: '2015-06-04 10:22:50'System info: host: 'TigerTeamUnit3', ip: '192.168.102.159', os.name: 'Windows 7', os.arch: 'amd64', os.version: '6.1', java.version: '1.8.0_51'Driver info: org.openqa.selenium.firefox.FirefoxDriverCapabilities [{applicationCacheEnabled=true, rotatable=false, handlesAlerts=true, databaseEnabled=true, version=40.0.2, platform=WINDOWS, nativeEvents=false, acceptSslCerts=true, webStorageEnabled=true, locationContextEnabled=true, browserName=firefox, takesScreenshot=true, javascriptEnabled=true, cssSelectorsEnabled=true}]Session ID: 2470cb01-c07a-45ff-9fc5-aa41c4307cfb";
		
			/*NonFormFileUploader nf = new NonFormFileUploader("http:\\localhost:8080\\TestReports");
			nf.sendData(fScreen, URLEncoder.encode("ks-Salesforce","UTF-8"),
					URLEncoder.encode("build","UTF-8"),
					URLEncoder.encode("karuna","UTF-8"),
					URLEncoder.encode("TestMethod1","UTF-8"),
					URLEncoder.encode("TestClassName1","UTF-8"),
					URLEncoder.encode("186278","UTF-8"),
					"2",
					"1811678", "Param2" , exception, "PASSED", 2, "testcycle2", "org@demo", "localinstance");*/
			
			
			//sendPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}