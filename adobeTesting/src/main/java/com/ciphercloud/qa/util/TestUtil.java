package com.ciphercloud.qa.util;

import java.util.ArrayList;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;

import com.ciphercloud.qa.dataprovider.Xls_Reader;



public class TestUtil {
	public static Logger APPLICATION_LOGS = LoggerFactory.getLogger(
			TestUtil.class.getName());
	// true - Y
	// false - N
	/**
	 * Function to check whether test case can be executed or not.
	 * @testCase - Name of the testCase
	 * @Xls_Reader - Xls_Reader object
	 */
	public static boolean isTestCaseExecutable(String testCase, Xls_Reader xls){
		boolean results=false;
		/* iterate through the rows of Test Cases sheet from 2nd row till testCase
		name is equal to the value in TCID column.*/
		for(int rNum=2; rNum<=xls.getRowCount("Test Cases"); rNum++){
			
			//Checks whether testCase(passes value) name is equals to the value in TCID
			if(testCase.equals(xls.getCellData("Test Cases", "TCID", rNum))){
				// check runmode is equals to Y/N. Returns true if Y else return false
				if(xls.getCellData("Test Cases", "Runmode", rNum).equals("Y"))
					results= true;
				else
					results= false;
			}				
		}
		
	for(int rNum=2; rNum<=xls.getRowCount("Filter Test Cases"); rNum++){
			
			//Checks whether testCase(passes value) name is equals to the value in TCID
			if(testCase.equals(xls.getCellData("Filter Test Cases", "TCID", rNum))){
				// check runmode is equals to Y/N. Returns true if Y else return false
				if(xls.getCellData("Filter Test Cases", "Runmode", rNum).equals("Y"))
					results= true;
				else
					results= false;
			}				
		}
		
		return results;		
	}
	
	/**
	 * Function to get data from xls sheet in 2 dimensional array
	 * @param testCase - testCase name
	 * @param xls - Xls_Reader Object
	 * @return 2 dimensional array
	 */
	public static Object[][] getData(String testCase, Xls_Reader xls, String sheetName){
	//	APPLICATION_LOGS.info("******getData*******: "+testCase);
		// find the test in xls
		// find number of cols in test
		// number of rows in test
		// put the data in hashtable and put hashtable in object array
		// return object array		
		int testCaseStartRowNum=0;
		//iterate through all rows from the sheet Test Data
		for(int rNum=1; rNum<=xls.getRowCount(sheetName); rNum++){
			//to identify testCase starting row number
			if(testCase.equals(xls.getCellData(sheetName, 0, rNum))){
				testCaseStartRowNum = rNum;
				break;
			}
		}
	//	APPLICATION_LOGS.info("Test Starts from row -> "+ testCaseStartRowNum);
	//	//System.out.println("Test Starts from row -> "+ testCaseStartRowNum);				
		// total cols
		int colStartRowNum=testCaseStartRowNum+1;
		int cols=0;
		//Get the total number of columns for which test data is present
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}
	//	APPLICATION_LOGS.info("Total cols in test -> "+ cols);
	//	//System.out.println("Total cols in test -> "+ cols);		
		// rows
		int rowStartRowNum=testCaseStartRowNum+2;
		int rows=0;
		//Get the total number of rows for which test data is present
		while(!xls.getCellData(sheetName, 0, (rowStartRowNum+rows)).equals("")){
			rows++;
		}
		//APPLICATION_LOGS.info("Total rows in test -> "+ rows);
	//	//System.out.println("Total rows in test -> "+ rows);
		Object[][] data = new Object[rows][1];
		Hashtable<String,String> table=null;		
		// print the test data
		for(int rNum=rowStartRowNum;rNum<(rows+rowStartRowNum);rNum++){
			table=new Hashtable<String,String>();
				for(int cNum=0;cNum<cols;cNum++){
					table.put(xls.getCellData(sheetName, cNum, colStartRowNum), xls.getCellData(sheetName, cNum, rNum));
				//	APPLICATION_LOGS.info(xls.getCellData(sheetName, cNum, rNum)+" - ");
				//	System.out.print(xls.getCellData(sheetName, cNum, rNum)+" - ");
				}
				data[rNum-rowStartRowNum][0]=table;
				//System.out.println();
		}
		return data;// dummy								
	}
	
	/**
	 * Function to get data from xls sheet in 2 dimensional array
	 * @param testCase - testCase name
	 * @param xls - Xls_Reader Object
	 * @return 2 dimensional array
	 */
	public static Object[][] getData(Xls_Reader xls, String sheetName,String testCase){
		
		String indexSheetName="Test Cases";
			return getdata(xls, sheetName, testCase, indexSheetName);// dummy								
		}

	
public static Object[][] getData(Xls_Reader xls, String sheetName,String testCase, String indexSheetName){
		
			return getdata(xls, sheetName, testCase, indexSheetName);// dummy								
		}
	/**
	 * @param xls
	 * @param sheetName
	 * @param testCase
	 * @param indexSheetName
	 * @return
	 */
public static Object[][] getdata(Xls_Reader xls, String sheetName, String testCase, String indexSheetName) {
	//CipherCloudBase.APPLICATION_LOGS.debug("******getData*******: "+testCase);
	////System.out.println("******getData*******: "+testCase);
	// find the test in xls
	// find number of cols in test
	// number of rows in test
	// put the data in hashtable and put hashtable in object array
	// return object array		

	int testCaseStartRowNum=0;
	int testCaseEndRowNum=0;
	////System.out.println(">>>>Row count in Test Cases: "+xls.getRowCount(indexSheetName));
	////System.out.println(">>>>Row count in Test Cases: "+xls.getRowCount(sheetName));
	//iterate through all rows from the sheet Test Data

	
	
	for(int rNum=1; rNum<=xls.getRowCount(indexSheetName); rNum++){
	//	//System.out.println("test case>>>>>"+xls.getCellData(indexSheetName, 0, rNum));
		//to identify testCase starting row number
		if(testCase.trim().equals(xls.getCellData(indexSheetName, 0, rNum).trim())){
		//	//System.out.println("Inside if test case equals:>>"+rNum);
		//	//System.out.println("Runmode is <Y>"+xls.getCellData(indexSheetName, "Runmode", rNum).trim());
			if(xls.getCellData(indexSheetName, "Runmode", rNum).trim().equals("Y")){
				//System.out.println("If Runmode is <Y>");
				testCaseStartRowNum = (int) Double.parseDouble(xls.getCellData(indexSheetName, "StartRowNumber", rNum).trim());
				testCaseEndRowNum = (int) Double.parseDouble(xls.getCellData(indexSheetName, "EndRowNumber", rNum).trim());
			//	//System.out.println("<<<Start row num>>>: "+testCaseStartRowNum 	+", <<<End Row Num>>>: "+testCaseEndRowNum);
				break;
			}else if(xls.getCellData(indexSheetName, "Runmode", rNum).trim().equals("N")){
				//System.out.println("If Runmode is <N>");
				testCaseStartRowNum = (int) Double.parseDouble(xls.getCellData(indexSheetName, "StartRowNumber", rNum).trim());
				testCaseEndRowNum = (int) Double.parseDouble(xls.getCellData(indexSheetName, "StartRowNumber", rNum).trim());
			//	//System.out.println("<<<Start row num>>>: "+testCaseStartRowNum 	+", <<<End Row Num>>>: "+testCaseEndRowNum);
				break;
			}
			break;
		}
	}
	
	
	int totalRows = testCaseEndRowNum-testCaseStartRowNum+1;
	//CipherCloudBase.APPLICATION_LOGS.debug("Total rows in test -> <<<"+testCase+">>>"+ totalRows);
	////System.out.println("Total rows in test -> <<<"+testCase+">>>"+ totalRows);
	//CipherCloudBase.APPLICATION_LOGS.debug("Test Starts from row -> "+ testCaseStartRowNum);
	////System.out.println("Test Starts from row -> "+ testCaseStartRowNum);				
	// total cols
	int colStartRowNum=testCaseStartRowNum;
	int cols=0;
	
	while(!xls.getheadercolumsieze(sheetName, cols, colStartRowNum).equals("")){
		cols++;
	}
	//Get the total number of columns for which test data is present
/*	while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
		cols++;
	}*/
	//CipherCloudBase.APPLICATION_LOGS.debug("Total cols in test -> "+ cols);
	//System.out.println("Total cols in test -> "+ cols);		
	
	Object[][] data = new Object[totalRows][1];
	Hashtable<String,String> table=null;		
	// print the test data
	//for(int rNum=rowStartRowNum;rNum<(rows+rowStartRowNum);rNum++){
	for(int rNum=0;rNum<totalRows;rNum++,testCaseStartRowNum++){
		table=new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
			
				table.put(xls.getCellData(sheetName, cNum, 1), xls.getCellData(sheetName, cNum, testCaseStartRowNum));
			
				System.out.print(xls.getCellData(sheetName, cNum, testCaseStartRowNum)+" - ");
			}
			data[rNum][0]=table;
			
			//System.out.println();
	}
	return data;
}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void checkRunModeAndSkipTestCase(String methodName,Xls_Reader snow_xls, Hashtable<String,String> data){
		
		
		checkRun(methodName, snow_xls, data);
		
	}

	
@SuppressWarnings("unchecked")
public static void checkRunModeAndSkipTestCase(String methodName,Xls_Reader snow_xls,Object[][] exceldata){
//	//System.out.println((Hashtable<String,String>)exceldata[0][0]);
		
		checkRun(methodName, snow_xls, (Hashtable<String,String>)exceldata[0][0]);
		
	}
	public static void checkRun(String methodName, Xls_Reader snow_xls, Hashtable<String, String> data) {
		//System.out.println("MethodName: " + methodName);
////System.out.println();
		APPLICATION_LOGS.info(">>>Starting execution of:'" + methodName
				+ "'<<<");
		// Skips the test case if runmode is set to N
		if (!TestUtil.isTestCaseExecutable(methodName, snow_xls)) {
			APPLICATION_LOGS.info("Skipping the test '" + methodName
					+ "' as testcase Runmode is set to: NO");
			throw new SkipException("Skipping the test '" + methodName
					+ "' as testcase Runmode is set to: NO");
		}
		// Skips the testcase based on the runmode in Test Data sheet
		if (!data.get("RunMode").equals("Y")) {
			APPLICATION_LOGS.info("Skipping the execution of '" + methodName
					+ "' as Runmode of test data is set to: NO");
			throw new SkipException("Skipping the execution of '" + methodName
					+ "' as Runmode of test data is set to: NO");
		}
	}
	
	
}
