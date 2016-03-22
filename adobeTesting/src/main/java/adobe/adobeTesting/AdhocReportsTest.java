package adobe.adobeTesting;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import org.sikuli.script.*;

public class AdhocReportsTest {

        public static void main(String[] args) throws InterruptedException, UnsupportedFlavorException, IOException, AWTException, FindFailed {
                Screen adhocScreen = new Screen();
           
                	ImagePath.setBundlePath(System.getProperty("user.dir")+"/imgsAdhoc");
                	clickOnImage(adhocScreen,"Reports.png");
                	clickOnImage(adhocScreen,"customic traffic.png");
                	clickOnImage(adhocScreen,"custom traffic 1-10.png");
                	clickOnImage(adhocScreen,"Custom_insight1.png");
                	clickOnImage(adhocScreen,"Total.png");
                	
                
                	copyPaste(); 
                	
                	Thread.sleep(200);
                	Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                	 Transferable t = clpbrd.getContents( null );
                     if ( t.isDataFlavorSupported(DataFlavor.stringFlavor) )
                     {
                         Object o = t.getTransferData( DataFlavor.stringFlavor );
                         String data = (String)t.getTransferData( DataFlavor.stringFlavor );
                         
                         //You need to Assert here all the data 
                         
                         System.out.println( "Clipboard contents: " + data );
                     }
                     Thread.sleep(600);
                     
                 	clickOnImage(adhocScreen,"closeButton.png",87,-2);
            
        }

		/**
		 * @param adhocScreen
		 * @throws FindFailed
		 */
		private static void clickOnImage(Screen adhocScreen, String filePath,int xaxis, int yaxis) throws FindFailed {
			 adhocScreen.click(adhocScreen.exists(new Pattern(filePath).targetOffset(xaxis,yaxis), 1), 0);
		}

		/**
		 * @param adhocScreen
		 * @throws InterruptedException
		 * @throws FindFailed
		 */
		private static void clickOnImage(Screen adhocScreen,String picPath) throws InterruptedException, FindFailed {
			try {
				adhocScreen.click(picPath);
				Thread.sleep(100);
			} catch (Exception e) {
				Thread.sleep(400);
				adhocScreen.click(picPath);
			}
		}

		/**
		 * @throws AWTException
		 * @throws InterruptedException
		 */
		private static void copyPaste() throws AWTException, InterruptedException {
			new Robot().keyPress(KeyEvent.VK_CONTROL);
			Thread.sleep(200); 
			new Robot().keyPress(KeyEvent.VK_A); 
			Thread.sleep(200); 
			new Robot().keyRelease(KeyEvent.VK_A); 
			Thread.sleep(200); 
			Thread.sleep(200); 
			new Robot().keyPress(KeyEvent.VK_C); 
			Thread.sleep(200); 
			new Robot().keyRelease(KeyEvent.VK_C); 
			Thread.sleep(200); 
			new Robot().keyRelease(KeyEvent.VK_CONTROL);
		}

}