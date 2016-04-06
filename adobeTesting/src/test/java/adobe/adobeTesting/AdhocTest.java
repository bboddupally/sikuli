package adobe.adobeTesting;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;

import javax.swing.plaf.synth.Region;

import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import com.ciphercloud.qa.util.AdhocUtil;

public class AdhocTest {
	 Screen adhocScreen = new Screen();
	 AdhocUtil adUtil = new AdhocUtil();
@Test
public void verifyRecords() throws AWTException, InterruptedException, FindFailed, UnsupportedFlavorException, IOException, ClassNotFoundException{
	  

   	ImagePath.setBundlePath(System.getProperty("user.dir")+"/AdobeResource/imgsAdhoc");
    Screen scr=new Screen();

    
    scr.find("Reports.png").right(135).highlight(3);
 String txt=scr.find("Reports.png").right(135).text();
 System.out.println("Text :"+txt);
   	adUtil.clickOnImage(adhocScreen,"Reports.png");
   	adUtil.clickOnImage(adhocScreen,"customic traffic.png");
   	adUtil.clickOnImage(adhocScreen,"custom traffic 1-10.png");
   	adUtil.clickOnImage(adhocScreen,"Custom_insight1.png");
   	adUtil.clickOnImage(adhocScreen,"Total.png");
   	copytoClipboard();
    adUtil.clickOnImage(adhocScreen,"closeButton.png",87,-2);
   	Thread.sleep(200);
   	adUtil.pasteFromClpbrd();
    Thread.sleep(600);
   
}


/**
 * 
 */
private void copytoClipboard() {
	adhocScreen.type("a",Key.CTRL);
   	adhocScreen.type("c",Key.CTRL);
    try {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents  = clipboard.getContents(null);
	} catch (HeadlessException e) {
		adhocScreen.type("a",Key.CTRL);
	   	adhocScreen.type("c",Key.CTRL);
	}
}

	
}
