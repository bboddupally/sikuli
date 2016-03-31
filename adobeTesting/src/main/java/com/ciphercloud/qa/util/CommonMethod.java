package com.ciphercloud.qa.util;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ciphercloud.qa.dataprovider.Config;
import com.thoughtworks.selenium.SeleniumException;

import junit.framework.Assert;



/**
 * @author himadri.das
 * 
 */
public class CommonMethod {
	
	 Config conf;
	 final static Logger logback = LoggerFactory.getLogger(CommonMethod.class);
	// -------------------------------------------------------------------------
		/**
		 * creating constructor and loading properties file
		 * @param filePath
		 */
	 public CommonMethod(String filePath){
		 
		 conf=new Config(filePath);
	 }
public CommonMethod(){
		 
		
	 }
	 
	 public String getValue(String key){
		 
		 String value = conf.getValue(key);
		 return value;
		 
		 
	 }
		public String randomUserName(String rNumber, String name) {
			String accountName = name + rNumber;
			return accountName;

		}
		public int randomNumber() {
			Random random = new Random();

			// get the next random int
			int randomInt = random.nextInt(1000000);
			if (randomInt < 10000) {
				randomInt = randomInt * 100;
			} else if (randomInt < 100000) {
				randomInt = randomInt * 10;
			}
			return randomInt;
		}
	 
	 public HashMap<String, String> parseInputData(Hashtable<String, String> data) {
			String parameters =data.get("parameters");
			String para[]=	parameters.split(";");
			HashMap<String, String>  paraMap= new HashMap<String, String>();
			for (String string : para) {
				String array[]=string.split("=");
				if(!array[0].startsWith("#"))
				paraMap.put(array[0], array[1].replace("=", ""));
			}
			return paraMap;
		}
	 
	 
	 public LinkedHashMap<String, String> parseInputData(String parameters) {
try {
	System.out.println(parameters);
	String para[]=	parameters.trim().split(";");
				LinkedHashMap<String, String>  paraMap= new LinkedHashMap<String, String>();
				for (String string : para) {
					System.out.println(string);
					String array[]=string.split("=");
					if(!array[0].startsWith("#")){
						if((array[0]).startsWith("$")){
							paraMap.put(array[0].replace("$", ""), randomUserName(randomNumber() + "", array[1].replace("=", "")));
						}else{
							System.out.println(array[0]+","+array[1].replaceAll("=", ""));
							paraMap.put(array[0], array[1].replaceAll("=", ""));

						}
					}
				}
				
				return paraMap;
} catch (Exception e) {
	//System.out.println("please verify your data in Excel");	e.printStackTrace();
	
	
}
return null;
		}
	 //		StringTokenizer tokenizer = new StringTokenizer(parameters2.trim(),"\n");

	 
	 public LinkedHashMap<String, String> parseMultiInputData(String parameters) {
			try {
				//System.out.println();
				//System.out.println(parameters);
				//String parameters =;
				String para[]=	parameters.trim().split(",");
				LinkedHashMap<String, String>  paraMap= new LinkedHashMap<String, String>();
				for (String string : para) {
					String array[]=string.split("~");
					if(!array[0].startsWith("#"))
					paraMap.put(array[0], array[1].replace("~", ""));
				}
				return paraMap;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	 
	 public LinkedHashMap<String, String> parseMultiInput(String parameters) {
			try {
				//System.out.println(parameters);
				//String parameters =;
				parameters=	parameters.trim().replaceAll("\n", "");
				String para[]=	parameters.trim().split(",");
				LinkedHashMap<String, String>  paraMap= new LinkedHashMap<String, String>();
				for (String string : para) {
					String array[]=string.split(":");
					if(!array[0].startsWith("#"))
					paraMap.put(array[0], array[1].replace(":", ""));
				}
				return paraMap;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	 
	 
	 public LinkedHashMap<String, String> parseMultiData(String parameters) {
			try {
			//	System.out.println(parameters);
				StringTokenizer tokenizer = new StringTokenizer(parameters.trim(),"\n");
				int count = tokenizer.countTokens();
				
				//String para[]=	parameters.trim().split(",");
				LinkedHashMap<String, String>  paraMap= new LinkedHashMap<String, String>();
				
				for (int i = 0; i < count; ++i) {
					String x = tokenizer.nextToken();
					if (x.contains("=")) {
						String test = x.substring(0, x.indexOf(":")).trim();
						String test2 = x.substring(x.indexOf(":"), x.length()).replace(":", "").trim();
						if(!test.startsWith("#"))
							System.out.println(test);
						System.out.println(test2);
						paraMap.put(test, test2);
					}
				}
	
				
			
				return paraMap;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	 public ArrayList<String> parseData(String parameters) {
		 ArrayList<String> arrayList = new ArrayList<String>();
if(!parameters.isEmpty()){
				//System.out.println(parameters);
				StringTokenizer tokenizer = new StringTokenizer(parameters.trim(),"\n");
				int count = tokenizer.countTokens();
				for (int i = 0; i < count; ++i) {
					String token=tokenizer.nextToken().trim();
					if(!token.startsWith("#"))
					arrayList.add(token);
				}
				return arrayList;
}else{
	return arrayList;

}
		}
	 
	 
	 public CopyOnWriteArrayList<String> parseDataWithComa(String parameters) {
		 CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<String>();
if(!parameters.isEmpty()){
				//System.out.println(parameters);
				StringTokenizer tokenizer = new StringTokenizer(parameters.trim(),",");
				int count = tokenizer.countTokens();
				for (int i = 0; i < count; ++i) {
					String token=tokenizer.nextToken().trim();
					if(!token.startsWith("#"))
					arrayList.add(token);
				}
				return arrayList;
}else{
	return arrayList;

}
		}
	 
	// -------------------------------------------------------------------------
	/**
	 * Retrieve popup text message.
	 * 
	 * @param WebDriver
	 *            driver
	 * @return
	 */
	public static String getPopupMessage(final WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.accept();
		} catch (Exception e) {
			// Sometimes the text exist, but not the accept button.
			// this means the popup wasn't displayed and therefore
			// really never existed.
			//
			message = null;
		}
		//System.out.println("message" + message);
		return message;
	}

	// -------------------------------------------------------------------------
	/**
	 * Canceling popup
	 * 
	 * @param driver
	 * @return
	 */
	public static String cancelPopupMessageBox(final WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.dismiss();
		} catch (Exception e) {
			// Sometimes the text exist, but not the accept button.
			// this means the popup wasn't displayed and therefore
			// really never existed.
			//
			message = null;
		}

		return message;
	}

	// -------------------------------------------------------------------------
	/**
	 * Check hover message text
	 * 
	 * @param driver
	 * @param by
	 * 
	 * @return string
	 * @throws IOException
	 */
	public  String checkHoverMessage(WebDriver driver,
			String objectLocater) {
		String tooltip = findElement(driver, objectLocater).getAttribute("title");
		//System.out.println(tooltip);
		return tooltip;
	}

	// -------------------------------------------------------------------------
	/**
	 * Select radio button
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException
	 * 
	 */
	public void selectRadioButton(WebDriver driver,
			String objectLocater, String value)  {
		List<WebElement> select = findElements(driver, objectLocater);

		for (WebElement radio : select) {
			if (radio.getAttribute("value").equalsIgnoreCase(value)) {
				radio.click();

			}
		}
	}

	// -------------------------------------------------------------------------
	/**
	 * Select multiple check boxes
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException
	 * 
	 */
	public void selectCheckboxes(WebDriver driver, String objectLocater,
			String value)  {

		List<WebElement> abc = findElements(driver, objectLocater);
		List<String> list = new ArrayList<String>(Arrays.asList(value
				.split(",")));

		for (String check : list) {
			for (WebElement chk : abc) {
				if (chk.getAttribute("value").equalsIgnoreCase(check)) {
					chk.click();
				}
			}
		}
	}

	// -------------------------------------------------------------------------

	/**
	 * Select drop down
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException
	 * 
	 */
	public void selectDropdown(WebDriver driver, String objectLocater,
			String value) {
		new Select(findElement(driver, objectLocater)).selectByVisibleText(value);
	}

	// -------------------------------------------------------------------------

	/**
	 * Select auto-suggest search drop down
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException
	 * 
	 */
	public void selectSearchDropdown(WebDriver driver,
			String objectLocater, String value) {
		findElement(driver, objectLocater).click();
		findElement(driver, objectLocater).sendKeys(value);
		findElement(driver, objectLocater).sendKeys(Keys.TAB);
	}

	// -------------------------------------------------------------------------

	/**
	 * Upload file
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException
	 * 
	 */
	public void uploadFile(WebDriver driver, String objectLocater,String value)  {
		genricfindElement(driver, objectLocater).sendKeys(value);
	}

	// -------------------------------------------------------------------------

	/**
	 * Takes controls on new tab
	 * 
	 * @param driver
	 * 
	 */
	public  void handleNewTab(WebDriver driver) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		// String window0 = (String) allWindowHandles.toArray()[1];
		Iterator<String> iter = allWindowHandles.iterator();
		int size = allWindowHandles.size();
		String window0 = null;
		for (int i = 0; i < size; i++) {
			window0 = iter.next();
		}

		driver.switchTo().window(window0);
	}

	// -------------------------------------------------------------------------

	/**
	 * Takes control on parent window
	 * 
	 * @param driver
	 */
	public  void handleParentTab(WebDriver driver) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		String window0 = (String) allWindowHandles.toArray()[0];
		driver.switchTo().window(window0);
	}

	public  void handleChildParentTab(WebDriver driver) 
	{
		Set<String> allWindowHandles = driver.getWindowHandles();
		String window0 = (String) allWindowHandles.toArray()[1];
		driver.switchTo().window(window0);
	}

	
	// -------------------------------------------------------------------------
	/**
	 * Helper method: looks through a list of WebElements, to find the first
	 * WebElement with matching text
	 * 
	 * @param elements
	 * @param text
	 * 
	 * @return WebElement or null
	 */
	public static WebElement findElementByText(List<WebElement> elements,
			String text) {
		WebElement result = null;
		for (WebElement element : elements) {
			element.getText().trim();
			if (text.equalsIgnoreCase(element.getText().trim())) {
				result = element;
				break;
			}
		}
		return result;
	}

	// -------------------------------------------------------------------------
	/**
	 * Compact way to verify if an element is on the page
	 * 
	 * @param driver
	 * @param by
	 * @return
	 * @throws IOException
	 */
	public boolean isElementPresent(final WebDriver driver,
			String objectLocater)  {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (findElements(driver, objectLocater).size() != 0) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;

		} else {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}
	
	
	public boolean isElementPresent(String xpath,final WebDriver driver
			)  {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath(xpath)).size() != 0) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;

		} else {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}
	

	
	public  void explicitWaitElementToBeVisible(WebDriver driver, int timeOutInSeconds,WebElement wb)  {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(wb));
	}

	
	public boolean isElementPresent(final WebDriver driver,
			WebElement wb)  {
		try{
			wb.isDisplayed();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	

	// -------------------------------------------------------------------------
	/**
	 * Compact way to verify if an element is on the page. we can pass WebElemnt
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */

	public static boolean isElementPresent(final WebDriver driver,
			List<WebElement> objectLocater)  {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (objectLocater.size() != 0) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;

		} else {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}

	// -------------------------------------------------------------------------
	/**
	 * Downloads a file from the defined url, and saves it into the
	 * OutputDatafolder, using the filename defined
	 * 
	 * @param href
	 * @param fileName
	 */
	public static void downloadFile(String href, String fileName)
			 {

		URL url = null;
		URLConnection con = null;
		int i;

		try {
			url = new URL(href);

			con = url.openConnection();
			File file = new File(".//OutputData//" + fileName);
			BufferedInputStream bis = new BufferedInputStream(con.getInputStream());

			BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file));
			while ((i = bis.read()) != -1) {
				bos.write(i);
			}
			bos.flush();
			bis.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------
	/**
	 * To click on the element
	 * 
	 * @param driver
	 * @param objectLocater
	 * @throws IOException
	 */
	public void click(final WebDriver driver, String objectLocater){
		try{
		explicitWait(driver, 15, objectLocater);
		findElement(driver, objectLocater).click();
		}catch(SeleniumException se){
			throw new RuntimeException(se.getMessage());
		}
	}
	

	public void genricClick(final WebDriver driver, String xpath){
		try{
			genricExplicitwait(driver, 15, xpath);
			try {
				genricfindElement(driver, xpath).click();
			} catch (Exception e) {

			}
		}catch(SeleniumException se){
			throw new RuntimeException(se.getMessage());
		}
	}
	
	

	// -------------------------------------------------------------------------
	/**
	 * to click on the element
	 * 
	 * @param driver
	 * @param we
	 */
	public static void click(WebDriver driver, WebElement we) {
		we.click();
	}
	
	/**
	 * click using java script function
	 * @param driver
	 * @param objectLocater
	 */
	 public void clickByJavaScript(final WebDriver driver,String objectLocater){
         explicitWait(driver, 20, objectLocater);
 
         ((JavascriptExecutor) driver).executeScript("arguments[0].click()",findElement(driver,objectLocater));
	 }
	 
	 public void clickByJavaScript(final WebDriver driver,WebElement wb){
        
 
         ((JavascriptExecutor) driver).executeScript("arguments[0].click()", wb);
	 }
	 
	 public String getAttribute(WebDriver driver,String objectLocator, String value){
		 String attrValue =  findElement(driver, objectLocator).getAttribute(value);
		return attrValue;
		 
		 
	 }
	 
	 
	 public String getAttribute2(WebDriver driver,String objectLocator, String value){
		 String attrValue =  genricfindElement(driver, objectLocator).getAttribute(value);
		return attrValue;
		 
		 
	 }
	// -------------------------------------------------------------------------
	/**
	 * Click on element
	 * 
	 * @param driver
	 * @param objectLocater
	 * @param value
	 * @throws IOException
	 */

	public void sendKeys(final WebDriver driver, String objectLocater,String value) {
		findElement(driver, objectLocater).sendKeys(value);
	}

	
	
	
	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @throws IOException
	 */
	public void getAllLink(final WebDriver driver, String objectLocater)
			 {
		List<WebElement> list = findElements(driver, objectLocater);
		Iterator<WebElement> itr = list.iterator();
		while (itr.hasNext()) {
			//System.out.println("links list--->" + itr.next());
		}

	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @throws IOException
	 */
	public void clear(WebDriver driver, String objectLocater)
	{
		findElement(driver, objectLocater).clear();
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public  WebElement findElement(final WebDriver driver,
			String objectLocater) {

		String objecttypeandvalues = conf.getValue(objectLocater);
		if(objecttypeandvalues.contains("~")){

		String[] splits = objecttypeandvalues.split("~");

		// String[] splits = objectLocater.split(":");
		String objecttype = splits[0];
		logback.info("obj type: " + objecttype);
		String objectvalue = splits[1];
		logback.info("obj val: " + objectvalue);
		if (objecttype.equalsIgnoreCase("name")) {
			return driver.findElement(By.name(objectvalue));
		} else if (objecttype.equalsIgnoreCase("class")) {
			return driver.findElement(By.className(objectvalue));
		} else if (objecttype.equalsIgnoreCase("tagname")) {
			return driver.findElement(By.tagName(objectvalue));
		} else if (objecttype.equalsIgnoreCase("link")) {
			return driver.findElement(By.linkText(objectvalue));
		} else if (objecttype.equalsIgnoreCase("css")) {
			return driver.findElement(By.cssSelector(objectvalue));
		}
		}else{
			 return genricfindElement(driver, objecttypeandvalues);
		}
		/*if (objecttype.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(objectvalue));
		} else if (objecttype.equalsIgnoreCase("xpath")) {
			return driver.findElement(By.xpath(objectvalue));

		} else*/
		return null; 
		

	}
	public WebElement genricfindElement( WebDriver driver, String objecttypeandvalues) {
		
		
		if (objecttypeandvalues.startsWith("//") || objecttypeandvalues.startsWith("(//") ) {
				return driver.findElement(By.xpath(objecttypeandvalues));
			}else {
				return driver.findElement(By.id(objecttypeandvalues));
			}
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public  List<WebElement> findElements(final WebDriver driver,
			String objectLocater) {

		String objecttypeandvalues = conf.getValue(objectLocater);
		if(objecttypeandvalues!=null){
		if(objecttypeandvalues.contains("~")){

		String[] splits = objecttypeandvalues.split("~");

		// String[] splits = objectLocater.split(":");
		String objecttype = splits[0];
		logback.info("obj type: " + objecttype);
		String objectvalue = splits[1];
		logback.info("obj val: " + objectvalue);
		if (objecttype.equalsIgnoreCase("name")) {
			return driver.findElements(By.name(objectvalue));
		} else if (objecttype.equalsIgnoreCase("class")) {
			return driver.findElements(By.className(objectvalue));
		} else if (objecttype.equalsIgnoreCase("tagname")) {
			return driver.findElements(By.tagName(objectvalue));
		} else if (objecttype.equalsIgnoreCase("link")) {
			return driver.findElements(By.linkText(objectvalue));
		} else if (objecttype.equalsIgnoreCase("css")) {
			return driver.findElements(By.cssSelector(objectvalue));
		}
		}else{
			 if (objecttypeandvalues.startsWith("//")) {
					return driver.findElements(By.xpath(objecttypeandvalues));
				}else {
					return driver.findElements(By.id(objecttypeandvalues));
				}
		}
		/*if (objecttype.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(objectvalue));
		} else if (objecttype.equalsIgnoreCase("xpath")) {
			return driver.findElement(By.xpath(objectvalue));

		} else*/
		return null; 
		
		}else{
			Assert.assertEquals(objectLocater, "null"," please provide valid properties in property file" );

		}
		return null;
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @return
	 */
	public static String getTitle(WebDriver driver) {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public  String getText(WebDriver driver, String objectLocater)
			 {
		// TODO Auto-generated method stub
		explicitWait(driver, 15, objectLocater);

		return findElement(driver, objectLocater).getText();

	}
	
	public  String getText2(WebDriver driver, String objectLocater)
	 {
// TODO Auto-generated method stub
		genricExplicitwait(driver, 15, objectLocater);

return genricfindElement(driver, objectLocater).getText();

}
	
	public  String getText(WebDriver driver,WebElement wb){
		return wb.getText();
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public  String getValue(WebDriver driver, String objectLocater)
			 {
		return findElement(driver, objectLocater).getAttribute("value");
	}

	// -------------------------------------------------------------------------
	/**
	 * waiting for elements explicitly
	 * 
	 * @param driver
	 * @param timeOutInSeconds
	 * @param objectLocater
	 * @throws IOException
	 */
	public void explicitWait(WebDriver driver, int timeOutInSeconds,
			String objectLocater) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		String objecttypeandvalues = conf.getValue(objectLocater).trim();
		if(objecttypeandvalues!=null){
		if(objecttypeandvalues.contains("~")){
		String[] splits = objecttypeandvalues.split("~");
		// String[] splits = objectLocater.split(":");
		String objecttype = splits[0];
		//System.out.println("obj type: " + objecttype);
		String objectvalue = splits[1];
		//System.out.println("obj val: " + objectvalue);

		 if (objecttype.equalsIgnoreCase("name")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.name(objectvalue)));
		} else if (objecttype.equalsIgnoreCase("class")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.className(objectvalue)));
		} else if (objecttype.equalsIgnoreCase("tagname")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.tagName(objectvalue)));
		} else if (objecttype.equalsIgnoreCase("css")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(objectvalue)));
		}
		}
		else{
			genricExplicitwait( driver,  timeOutInSeconds, objecttypeandvalues);}

		}else{
			Assert.assertEquals(objectLocater, "null"," please provide valid properties in property file" );
		}
		
		
		
	}
	public void genricExplicitwait(WebDriver driver, int timeOutInSeconds, String objecttypeandvalues) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		if (objecttypeandvalues.startsWith("//") || objecttypeandvalues.startsWith("(//")) {

			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(objecttypeandvalues)));

		} else  {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id(objecttypeandvalues)));

		
}
	}
	
	/**
	 * waiting for elements explicitly
	 * 
	 * @param driver
	 * @param timeOutInSeconds
	 * @param objectLocater
	 * @throws IOException
	 */
	public  void explicitWaitElementToBeVisible(WebDriver driver, int timeOutInSeconds,String objectLocater)  {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(findElement(driver, objectLocater)));
	}


	// -------------------------------------------------------------------------
	/**
	 * removing space between strings
	 * 
	 * @param str
	 * @return
	 */
	public static String removeBetweenSpaceFromString(String str) {

		String st = str.replaceAll("\\s+", "");
		return st;
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param groupOfObjectLocators
	 * @return
	 * @throws IOException
	 */
	public  int verifyingTextFieldsGUI(final WebDriver driver,
			String[] groupOfObjectLocators)  {
		int i = 0;
		int value = 0;

		for (; i < groupOfObjectLocators.length; i++) {

			value = displayedElements(driver, groupOfObjectLocators[i]) + value;

			// value++;

		}

		//System.out.println("Count Of TextFields From CommonMethod Class is : "		+ value);

		// return displayedElements(driver, groupOfObjectLocators[i]);

		return value;

	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public  int displayedElements(final WebDriver driver,
			String objectLocater)  {
		int count = 0;

		WebElement element = findElement(driver, objectLocater);

		if (element.isDisplayed()) {

			count++;

		}

		System.out
				.println("Count for displayed Elements In CommonMethod Class is : "
						+ count);

		return count;

	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public  int listOfItems(final WebDriver driver, String objectLocater)
			 {
		int count = 0;

		List<WebElement> listOfItems = findElements(driver, objectLocater);

		for (int i = 0; i < listOfItems.size(); i++) {

			// //System.out.println(dropdown_items.get(i).getText());
			if (listOfItems.get(i).isDisplayed()) {
				count++;

			}
		}

		//System.out.println("Count from ListOfItems In CommonMethod Class is : "	+ count);

		return count;

	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public  String[] gettingTextFromListItems(final WebDriver driver,String objectLocater)  {

		List<WebElement> elements = findElements(driver, objectLocater);

		String[] listOfItems = new String[elements.size()];

		int i = 0;

		for (WebElement e : elements) {
			listOfItems[i] = e.getText();

			i++;
		}

		//System.out.println("Size of the  String Array From Common Method is : "+ listOfItems.length);

		return listOfItems;
	}

	
	// -------------------------------------------------------------------------
	/**
	 * This block is used to switching to a new frame
	 *  
	 * @param driver
	 * @param frame
	 */
	public  void switchToFrame(WebDriver driver, WebElement frame){
		driver.switchTo().frame(frame);
	}
	
	public  void switchToFrame(WebDriver driver, String frame){
		try {
		WebElement wb=findElement(driver, frame);
			driver.switchTo().frame(wb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// -------------------------------------------------------------------------
	/**
	 * This block is used to switching back to the old frame
	 *  
	 * @param driver
	 * @param frame
	 */
	public  void switchBackToDefault(WebDriver driver){
		driver.switchTo().defaultContent();
	}
	 /* Switch to Window from one window page another window page */
    public void switchToWindow(WebDriver driver, String windowId)     {
        Set<String> windows = driver.getWindowHandles();

        for(String window : windows)
        {
            
            String title = driver.getTitle();
            //System.out.println("Window Title : "+title);
            if (title.equals(windowId))
            {
                driver.switchTo().window(window);
            }
        }

    }
    
    
    public  void switchToMainFrame(WebDriver driver){
    	 driver.switchTo().frame(conf.getValue("mainFrame"));
	}
   
    
    public void doubleClick(WebDriver driver, String objectLocator)
    {
        try {
            Actions action = new Actions(driver);
            WebElement doubleclickAction = findElement(driver, objectLocator);
            action.doubleClick(doubleclickAction).perform();

        }catch(Exception ex){
            //System.out.println("double click action failed to perform");

        }
    }    
    
    public void doubleClick(WebDriver driver, WebElement doubleclickAction)
    {
        try {
            Actions action = new Actions(driver);
            action.doubleClick(doubleclickAction).perform();

        }catch(Exception ex){
            //System.out.println("double click action failed to perform");

        }
    }  
    public void mouseOver( WebDriver driver, String objectLocator) throws Exception
    {

        //  driver.findElement(By.xpath(locatorValue));

        Actions action = new Actions(driver);
         WebElement we = findElement(driver, objectLocator);
        action.moveToElement(we).build().perform();
        Thread.sleep(4000);
    }
    
    public void switchToActiveElement(WebDriver driver){
        WebElement element = driver.switchTo().activeElement();
    }
    
    /* Run Java script code on a Webpage */
    public Boolean RunJavaScript(String script)   throws Exception {
    	 
    	JavascriptExecutor Js = null;
        script = String.format("setTimeout(\"%1$s\", 100);", script);
        /*try
        {*/
        Js.executeAsyncScript(script);

        //  }
        /*catch (Exception ex)
        {
            return false;
        }*/
        return true;
    }
    

    
}
