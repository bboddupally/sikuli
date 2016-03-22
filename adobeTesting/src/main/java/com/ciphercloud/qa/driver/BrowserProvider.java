package com.ciphercloud.qa.driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.ciphercloud.qa.dataprovider.Config;
import com.ciphercloud.qa.util.Constant;

public class BrowserProvider implements IDriverProvider{
	String nameBrowser;
	//public static String browserName;
	Config conf=new Config(Constant.CONFIGPATH);
	WebDriver driver;
	String downloadPath=Constant.DOWNLOADS;
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		if (driver==null){
			String browser=conf.getValue("driver.browsername");
			return getBrowser(browser);
		}
		return driver;
	}

	public WebDriver getDriver(String browserName) {
		// TODO Auto-generated method stub
		return getBrowser(browserName);
	}

	public WebDriver getBrowser(String browser){
		if(driver==null){
			if(browser.equalsIgnoreCase("firefox")){
		
				try {
					return firefox();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return firefox();
				}
			}
			else if(browser.equalsIgnoreCase("chrome")){
				
				try {
					return chrome();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return chrome();
				}
			}
			else if(browser.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver", conf.getValue("ie.path"));
				return new InternetExplorerDriver();
			}
			else if(browser.equalsIgnoreCase("safari")){
				return new SafariDriver();
			}
			else if(browser.equalsIgnoreCase("html")){
				return new HtmlUnitDriver();
			}
			else if(browser.equalsIgnoreCase("phantom")){
				Proxy proxy=new Proxy();
				DesiredCapabilities dCaps = new DesiredCapabilities();
				dCaps.setCapability("applicationCacheEnabled", true);
				dCaps.setJavascriptEnabled(true);
				dCaps.setCapability("takesScreenshot", true);
				dCaps.setCapability(CapabilityType.PROXY, proxy);
				if(conf.getValue("platform").equalsIgnoreCase("linux")){
					dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/local/bin/phantomjs");
				}
				else{
					dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:/phantomjs.exe");
				}
				dCaps.setCapability("handlesAlerts", true);
				dCaps.setCapability("cssSelectorsEnabled", true);
				dCaps.setCapability("webdriver_accept_untrusted_certs", true);
				dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] { "--web-security=false", "--ssl-protocol=any",
						"--ignore-ssl-errors=true", "--webdriver-loglevel=DEBUG", "--local-to-remote-url-access=yes", "--disk-cache=false" });
				driver = new PhantomJSDriver(dCaps);
			}
		}
		return driver;
	}

	private WebDriver chrome() {
		File file = new File(Constant.CHROMEPATH);
		if(file.exists()){
			System.setProperty("webdriver.chrome.driver", Constant.CHROMEPATH);

		}else{
			System.setProperty("webdriver.chrome.driver", "C:/AutoIt/chromedriver.exe");

		}

		
//	System.setProperty("webdriver.chrome.driver", Constant.CHROMEPATH);

		//Save Chrome Preferences in Hash Map
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		chromePrefs.put("safebrowsing.enabled", "true");
		//Save Chrome Opions
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");

		//Set desired capability
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		

		
		return new ChromeDriver(cap);
	}

	private WebDriver firefox() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.dir", downloadPath);
		profile.setPreference("browser.download.folderList", 2);
		profile.setAcceptUntrustedCertificates(true);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("download.prompt_for_download", false);
		profile.setPreference("plugin.disable_full_page_plugin_for_types","application/pdf,application/vnd.adobe.xfdf,application/vnd.fdf,application/vnd.adobe.xdp+xml,application/csv,application/xfdf,application/xml,application/octet-stream,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		profile.setPreference("browser.helperApps.neverAsk.openFile","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;application/vnd.oasis.opendocument.text,application/vnd.oasis.opendocument.spreadsheet"+
		"application/csv,text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/pdf,application/octet-stream,video/avi,video/mpeg,audio/x-aiff,audio/basic,audio/mid,audio/wav,audio/x-mpegurl,application/xfdf,application/vnd.adobe.xfdf,image/gif,image/bmp,image/tif,image/jpg,application/odt,text/xml,application/x-rtf,application/rtf,image/tiff,application/pkix-cert,application/oda,video/3gpp,audio/mpeg3,video/mp4,application/vnd.oasis.opendocument.presentation,application/vnd.oasis.opendocument.graphics,application/vnd.google-earth.kml+xml");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;application/vnd.oasis.opendocument.text,application/vnd.oasis.opendocument.spreadsheet"+
		"application/csv,text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/pdf,application/octet-stream,video/avi,video/mpeg,audio/x-aiff,audio/basic,audio/mid,audio/wav,audio/x-mpegurl,application/xfdf,application/vnd.adobe.xfdf,image/gif,image/bmp,image/tif,image/jpg,application/odt,text/xml,application/x-rtf,application/rtf,image/tiff,application/pkix-cert,application/oda,video/3gpp,audio/mpeg3,video/mp4,application/vnd.oasis.opendocument.presentation,application/vnd.oasis.opendocument.graphics,application/vnd.google-earth.kml+xml");
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.useDownloadDir", true);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone",true);
		profile.setPreference( "pdfjs.disabled", true );
		return new FirefoxDriver(profile);
	}


	public WebDriver getNewBrowser(){

		String browser=conf.getValue("driver.browsername");
		if(driver==null){
			if(browser.equalsIgnoreCase("firefox")){
				driver=new FirefoxDriver();
				return driver;
			}
			else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", Constant.CHROMEPATH);
				return new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver", conf.getValue("ie.path"));
				return new InternetExplorerDriver();
			}
			else if(browser.equalsIgnoreCase("safari")){
				return new SafariDriver();
			}
			else if(browser.equalsIgnoreCase("html")){
				return new HtmlUnitDriver();
			}
			else if(browser.equalsIgnoreCase("phantom")){
				Proxy proxy=new Proxy();
				DesiredCapabilities dCaps = new DesiredCapabilities();
				dCaps.setCapability("applicationCacheEnabled", true);
				dCaps.setJavascriptEnabled(true);
				dCaps.setCapability("takesScreenshot", true);
				dCaps.setCapability(CapabilityType.PROXY, proxy);
				if(conf.getValue("platform").equalsIgnoreCase("linux")){
					dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/local/bin/phantomjs");
				}
				else{
					dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:/phantomjs.exe");
				}
				dCaps.setCapability("handlesAlerts", true);
				dCaps.setCapability("cssSelectorsEnabled", true);
				dCaps.setCapability("webdriver_accept_untrusted_certs", true);
				dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] { "--web-security=false", "--ssl-protocol=any",
						"--ignore-ssl-errors=true", "--webdriver-loglevel=DEBUG", "--local-to-remote-url-access=yes", "--disk-cache=false" });
				driver = new PhantomJSDriver(dCaps);
			}
		}
		return driver;
	}


	public String getBrowserName() {
		// TODO Auto-generated method stub
		return nameBrowser;
	}

	public WebDriver getNewBrowser(String browser) {
		// TODO Auto-generated method stub
		return null;
	}



}
