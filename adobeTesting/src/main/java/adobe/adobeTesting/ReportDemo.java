package adobe.adobeTesting;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.GridType;
import com.relevantcodes.extentreports.LogStatus;



public class ReportDemo {
/*       static final ExtentReports extent=ExtentReports.get(ReportDemo.class);
       static WebDriver driver= (WebDriver) new FirefoxDriver();
       static String AfterLogin="http://www.guvi.in/PublicPlaylists";
       static String reportLocation = "E:\\snow2\\adobeTesting\\lib\\";
       static String Afterloginfail="http://www.guvi.in/" ;
       static String imageLocation = "images/";
       static String jobs="http://www.guvi.in/jobs";
       static String jobsfail="http://www.guvi.in/job";

       public static void main(String[] args) throws InterruptedException {
              // TODO Auto-generated method stub
              extent.init(reportLocation+"ScreenshotReport.html", true,DisplayOrder.BY_OLDEST_TO_LATEST,GridType.STANDARD);
              extent.config().displayTestHeaders(true);
              extent.config().documentTitle("Guvi test Reports");
              extent.config().reportHeadline("Automation Test Report for Guvi");
             
              //Start of TC1 with pass result
              extent.startTest("This Test to check the login crediential with Correct");
              extent.config().displayCallerClass(false);
              runWithCorrectCredientails();
              extent.attachScreenshot(createScreenshot(driver),"This is to attach screenshot for test");
              extent.log(LogStatus.PASS, "Link", "Check the Link After Login: <a href='http://www.guvi.in/PublicPlaylists'>link</a>.");
              extent.config().useExtentFooter(false);
         
              extent.endTest();

             
              //Fail
              extent.startTest("This Test to check is to verify the redirection link");
              extent.config().displayCallerClass(false);
             
              if(driver.getTitle().equals(Afterloginfail)) {
            extent.log(LogStatus.PASS, "Check page title", "Page title is " + AfterLogin);
        } else {
            extent.log(LogStatus.WARNING, "Check page title", "Incorrect login page title " + Afterloginfail);
        }
              extent.endTest();
             
              extent.startTest("This is used to check jobportal url ");
              extent.config().displayCallerClass(false);
              Thread.sleep(10000);
              driver.findElement(By.xpath("html/body/header/div[2]/div/div[1]/ul/li/button")).click();
              driver.findElement(By.cssSelector("a.li-boot")).click();
              if(driver.getTitle().equals(Afterloginfail)) {
            extent.log(LogStatus.PASS, "Click on job portal", "Page title is " + jobs);
        } else {
            extent.log(LogStatus.FAIL, "Check job portal url", "Incorrect login page title " + jobsfail);
        }
             
             
              extent.endTest();
             
              driver.quit();

       }
      
       public static void runWithCorrectCredientails() throws InterruptedException
       {
              driver.get("http://www.guvi.in/");
              driver.manage().window().maximize();
              driver.findElement(By.xpath("html/body/header/div[2]/div/div[3]/ul/li[1]/a")).click();
              Thread.sleep(5000);
              driver.findElement(By.id("login_email")).sendKeys("kk.prashanth65@gmail.com");
              Thread.sleep(5000);
              driver.findElement(By.id("login_password")).sendKeys("test123");
              Thread.sleep(5000);
              driver.findElement(By.id("login_button")).click();
              Thread.sleep(5000);
              String expgeturl=driver.getCurrentUrl();
              driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
              Thread.sleep(8000);
              if(driver.getCurrentUrl().equals(AfterLogin))
              {
                   extent.log(LogStatus.PASS, "Check page title", "Page title is " + expgeturl);
           }
              else {
                   extent.log(LogStatus.FAIL, "Check page title", "Incorrect login page title " + driver.getTitle());
               }    
       }
      
       public static String createScreenshot(WebDriver driver) {
               
           UUID uuid = UUID.randomUUID();
          
        
           // generate screenshot as a file object
           File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           try {
               // copy file object to designated location
             FileUtils.copyFile(scrFile, new File(reportLocation + imageLocation + uuid + ".png"));
              System.out.println(imageLocation + uuid + ".png");
           } catch (IOException e) {
               System.out.println("Error while generating screenshot:\n" + e.toString());
           }
           return reportLocation + imageLocation + uuid + ".png";
          
       }
*/

}
