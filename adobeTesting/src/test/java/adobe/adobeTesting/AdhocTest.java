package adobe.adobeTesting;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

public class AdhocTest {
	 Screen adhocScreen = new Screen();
	 AdhocUtil adUtil = new AdhocUtil();
@Test
public void verifyRecords() throws AWTException, InterruptedException, FindFailed, UnsupportedFlavorException, IOException, ClassNotFoundException{
	  
       
   	ImagePath.setBundlePath(System.getProperty("user.dir")+"/imgsAdhoc");
   	adUtil.clickOnImage(adhocScreen,"Reports.png");
   	adUtil.clickOnImage(adhocScreen,"customic traffic.png");
   	adUtil.clickOnImage(adhocScreen,"custom traffic 1-10.png");
   	adUtil.clickOnImage(adhocScreen,"Custom_insight1.png");
   	adUtil.clickOnImage(adhocScreen,"Total.png");
   	adUtil.copyToclpbrd(); 
   	Thread.sleep(200);
   	adUtil.pasteFromClpbrd();
    Thread.sleep(600);
    adUtil.clickOnImage(adhocScreen,"closeButton.png",87,-2);
}

	
}
