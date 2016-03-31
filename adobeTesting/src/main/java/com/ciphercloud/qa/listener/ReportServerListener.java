package com.ciphercloud.qa.listener;
/*
 * @author : Karuna Sunkara
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.xmlbeans.impl.tool.XSTCTester.TestCase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.ciphercloud.qa.base.TestBase;

public class ReportServerListener implements ITestListener {

	Properties ReportServer_prop, version_props = null;
	FileInputStream fis, fis1 = null;

	int totalRun = 0;
	String browserName;
	int totalPassed = 0, totalFailed = 0, totalSkipped = 0, totalNotRun = 0;
	static String methodName;
	List<String> notRunMethod = new ArrayList<String>();
	long startTime = 0;

	String status = "";
	String buildName = "";
	String cycleName = "";
	String serverUrl = "";
	String appName = "";
	String userName = "";
	String orgName = "";
	String instanceName = "";
	String executionID = "";
	String csName = "";
	private boolean isStartRecorded = false;

	/**
	 * creating constructor
	 * 
	 * @throws IOException
	 */
	public ReportServerListener() throws IOException {
		// TODO Auto-generated constructor stub
		System.out.println("come to report server listener.................");
		ReportServer_prop = new Properties();
		version_props = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "/SnowResource/Configurations/config.properties");
		fis1 = new FileInputStream(System.getProperty("user.dir") + "/version-snow.properties");
		ReportServer_prop.load(fis);
		version_props.load(fis1);
		buildName = version_props.getProperty("snowbuild");
		cycleName = URLEncoder.encode(ReportServer_prop.getProperty("cycleName"), "UTF-8");
		serverUrl = URLEncoder.encode(ReportServer_prop.getProperty("serverUrl"), "UTF-8");

		appName = ReportServer_prop.getProperty("appName");
		userName = ReportServer_prop.getProperty("userName");
		orgName = ReportServer_prop.getProperty("defaultUserName");
		instanceName = ReportServer_prop.getProperty("instanceName");
		executionID = getUniqueExecutionId();
		isStartRecorded = false;
		fis.close();
		fis1.close();
		System.out.println("build name...." + buildName);

	}

	@Override
	public void onTestFailure(ITestResult testResult) {
		// TODO Auto-generated method stub
		System.out.println("came to report server listener failed.....");
		recordResultsIntoReportServer(testResult);
	}

	@Override
	public void onTestSkipped(ITestResult testResult) {

		recordResultsIntoReportServer(testResult);

	}

	@Override
	public void onTestSuccess(ITestResult testResult) {
		// TODO Auto-generated method stub
		System.out.println("came into reportserver test passed...............");
		recordResultsIntoReportServer(testResult);

	}

	// This will provide the information on the test

	// This will return method names to the calling function

	private String returnMethodName(ITestNGMethod method) {

		return method.getRealClass().getSimpleName() + "." + method.getMethodName();

	}

	private String getTestStatus(ITestResult result) {

		String status = null;

		switch (result.getStatus()) {

		case ITestResult.SUCCESS:

			status = "PASSED";

			break;

		case ITestResult.FAILURE:

			status = "FAILED";

			break;

		case ITestResult.SKIP:

			status = "SKIPPED";

		}

		return status;

	}

	@Override
	public void onFinish(ITestContext arg0) {

		try {
			NonFormFileUploader resultDataTransfer = new NonFormFileUploader(serverUrl);
			resultDataTransfer.sendData(null, appName, buildName, userName, "EndMethod", csName, "0", "0", "0", "0","0", "END", 1, cycleName, orgName, instanceName, executionID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
public String testName=null;
	@Override
	public void onStart(ITestContext arg0) {
		testName=arg0.getName();
		System.out.println(arg0.getName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult testResult) {
	
		if (isStartRecorded) {
			return;
		}
		System.out.println(moduleName(testResult, "Module"));
		try {
			String[] className = testResult.getMethod().toString().split("\\.");
			System.out.println();
			String	ModuleName=moduleName(testResult, "Module");
			csName = moduleName(testResult, "Module");
			NonFormFileUploader resultDataTransfer = new NonFormFileUploader(serverUrl);
			resultDataTransfer.sendData(null, appName, buildName, userName, "StartMethod", csName, "0", "0", "0", "0","0", "START", 1, cycleName, orgName, instanceName, executionID);
			isStartRecorded = true;
		} catch (Exception e) {
			e.printStackTrace();
			isStartRecorded = true;
		}
	}

	public static int getMethodTestCaseCount(String classname, String methodName) {
		String className = classname.substring(0, classname.lastIndexOf("Test"));
		String xlsFilePath = System.getProperty("user.dir") + "\\testdata\\" + className + ".xls";
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(xlsFilePath)));
			HSSFSheet sheet = workbook.getSheet("Test Cases");
			Iterator<Row> rowIterator = sheet.rowIterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell cell = row.getCell(5);
				String method = row.getCell(0).toString();
				if (!method.isEmpty() && methodName.equalsIgnoreCase(method)) {
					String testCaseCount = cell.toString();
					double d = Double.parseDouble(testCaseCount);
					int i = (int) d;
					return i;
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public void recordResultsIntoReportServer(ITestResult testResult) {
System.out.println(moduleName(testResult, "Module"));
		try {
			long time = (testResult.getEndMillis() - testResult.getStartMillis());
			ITestNGMethod method = testResult.getMethod();
			String[] className = testResult.getMethod().toString().split("\\.");
			Object[] obj = testResult.getParameters();
			testResult.getInstance();

			WebDriver driver = ((TestBase) testResult.getInstance()).getDriver();

			String tResult = getTestStatus(testResult);

			String filename = null;
			if (driver != null) {

				if (tResult.equalsIgnoreCase("Failed")) {
					Calendar calendar = Calendar.getInstance();

					// Get the users home path and append the screen shots
					// folder
					// destination
					// String userHome = System.getProperty("user.home");
					String screenShotsFolder = System.getProperty("user.dir") + "\\TestOutput\\Screenshots\\";

					// The file includes the the test method and the test class
					String testMethodAndTestClass = testResult.getMethod().getMethodName() + "("
							+ testResult.getTestClass().getName() + ")";

					// Create the filename for the screen shots
					filename = screenShotsFolder + browserName + "-" + testMethodAndTestClass + "-"
							+ calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
							+ calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.HOUR_OF_DAY) + "-"
							+ calendar.get(Calendar.MINUTE) + "-" + calendar.get(Calendar.SECOND) + "-"
							+ calendar.get(Calendar.MILLISECOND) + ".jpg";

					// Take the screen shot and then copy the file to the screen
					// shot
					// folder
					File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

					FileUtils.copyFile(scrFile, new File(filename));
				}
			}
			csName = moduleName(testResult, "Module");
			String mName = URLEncoder.encode(method.getMethodName(), "UTF-8");
			String timeOut = URLEncoder.encode(String.valueOf(method.getTimeOut()), "UTF-8");
			String paramCount = URLEncoder.encode(String.valueOf(testResult.getParameters().length), "UTF-8");
			String exTime = URLEncoder.encode(String.valueOf(time), "UTF-8");
			if (testResult.getParameters().length <= 0) {
				obj = new Object[] { "no input" };
			}
			String params = URLEncoder.encode(String.valueOf(obj[0]), "UTF-8");

			String exception = "";
			if (tResult.equalsIgnoreCase("Failed")) {
				exception = URLEncoder.encode(testResult.getThrowable().toString(), "UTF-8");
				// exception = exception.replaceAll("\n", "");
				if (!exception.contains("AssertionError")) {
					tResult = "NOTRUN";
				}

			}

			System.out.println("class Name....." + csName);
			System.out.println("cycle name..." + cycleName + "..." + orgName + "...." + instanceName);

			System.out.println("exception......." + exception + "....." + tResult);

			// int methodTestCaseCount =
			// getMethodTestCaseCount(className[0],method.getMethodName());
			int methodTestCaseCount = 1;

			// int methodTestCaseCount = 5;
			NonFormFileUploader resultDataTransfer = new NonFormFileUploader(serverUrl);

			resultDataTransfer.sendData(filename, appName, buildName, userName, mName, csName, timeOut, paramCount,
					exTime, params, exception, tResult, methodTestCaseCount, cycleName, orgName, instanceName,
					executionID);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String moduleName(ITestResult result, String string) {
		String testDiscription;
		Hashtable<String, String> testName;
		try {
			Object[] objects = result.getParameters();
			testName = (Hashtable<String, String>) objects[0];
			testDiscription = testName.get(string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			testDiscription = "empty";
		}
		return testDiscription;
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

}
