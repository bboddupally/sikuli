package adobe.adobeTesting;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.sikuli.script.App;
import org.sikuli.script.Env;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import com.sun.jna.platform.win32.Wdm.KEY_BASIC_INFORMATION;

public class ReportBuilderTest {
	 Screen adhocScreen = new Screen();
	 AdhocUtil adUtil = new AdhocUtil();
@SuppressWarnings("deprecation")
@Test
public void verifyRecords() throws AWTException, InterruptedException, FindFailed, UnsupportedFlavorException, IOException, ClassNotFoundException{
	ImagePath.setBundlePath(System.getProperty("user.dir")+"/ImgReportBuilder");
       
   	
   	adUtil.doubleclickOnImage(adhocScreen,"create.png");
   	adUtil.waitclickOnImage(adhocScreen,"next.png");
   	adUtil.clickOnImage(adhocScreen,"finish.png");
	//adUtil.clickOnImage(adhocScreen,"select.png");
   	Thread.sleep(100);
	adUtil.clickOnImage(adhocScreen,"insert.png");
	adhocScreen.type("a",Key.CTRL);
	adhocScreen.type(Key.BACKSPACE);
	adhocScreen.type("$A$1");
	try {
		adUtil.clickOnImage(adhocScreen,"select3.png");
	} catch (Exception e) {
	}
	
	//adUtil.clickOnImage(adhocScreen,"select2.png");
	//adhocScreen.waitVanish("refreshing report.png");
	for (int i = 0; i < 30; i++) {
		Thread.sleep(1000);
		if(adhocScreen.exists("refreshing report.png") != null){
			Thread.sleep(1000);
		}else{
			break;
		}
	}
	adUtil.clickOnImage(adhocScreen,"selectA.png");

	 
	
	adhocScreen.type("c",Key.CTRL);
	
	

	adUtil.pasteFromClpbrd();
	

}

	
}
