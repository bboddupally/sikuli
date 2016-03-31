package adobe.adobeTesting;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import com.ciphercloud.qa.util.AdhocUtil;

public class ReportBuilderTest {
	 Screen adhocScreen = new Screen();
	 AdhocUtil adUtil = new AdhocUtil();
@Test
public void verifyRecords() throws AWTException, InterruptedException, FindFailed, UnsupportedFlavorException, IOException, ClassNotFoundException{
	ImagePath.setBundlePath(System.getProperty("user.dir")+"/AdobeResource/ImgReportBuilder");
   	adUtil.clickOnImage(adhocScreen,"create.png");
   	adUtil.waitclickOnImage(adhocScreen,"next.png");
   	adUtil.clickOnImage(adhocScreen,"finish.png");
   	Thread.sleep(100);
	adUtil.clickOnImage(adhocScreen,"insert.png");
	adhocScreen.type("a",Key.CTRL);
	adhocScreen.type(Key.BACKSPACE);
	adhocScreen.type("$A$1");
	try {
		adUtil.clickOnImage(adhocScreen,"select3.png");
	} catch (Exception e) {
	}

	
	for (int i = 0; i < 60; i++) {
		Thread.sleep(1000);
		if(adhocScreen.exists("refreshing report.png") != null){
			Thread.sleep(1000);
		}else{
			break;
		}
	}
	Thread.sleep(10000);
	
	adUtil.clickOnImage(adhocScreen,"selectA.png");

	 
	
	adhocScreen.type("c",Key.CTRL);
	
	

	adUtil.pasteFromClpbrd();
	

}

	
}
