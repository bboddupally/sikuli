package adobe.adobeTesting;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class AdhocUtil {

	/**
	 * @param adhocScreen
	 * @throws FindFailed
	 */
	public  void clickOnImage(Screen adhocScreen, String filePath,int xaxis, int yaxis) throws FindFailed {
		 adhocScreen.click(adhocScreen.exists(new Pattern(filePath).targetOffset(xaxis,yaxis), 1), 0);
	}

	/**
	 * @param adhocScreen
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public  void clickOnImage(Screen adhocScreen,String picPath) throws InterruptedException, FindFailed {
		try {
			adhocScreen.click(picPath);
			Thread.sleep(200);
		} catch (Exception e) {
			Thread.sleep(1000);
			try {
				adhocScreen.click(picPath);
			} catch (Exception e1) {
				Thread.sleep(1000);
				adhocScreen.click(picPath);	
				
			}
		}
	}
	
	public  void waitclickOnImage(Screen adhocScreen,String picPath) throws InterruptedException, FindFailed {
		try {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(1000);
				if(adhocScreen.exists(picPath) == null){
					Thread.sleep(1000);
				}else{
					break;
				}
			}	
			
			Thread.sleep(200);
			adhocScreen.click(picPath);
		} catch (Exception e) {
			Thread.sleep(1000);
			try {
				adhocScreen.click(picPath);
			} catch (Exception e1) {
				Thread.sleep(1000);
				adhocScreen.click(picPath);	
				
			}
		}
	}
	/**
	 * @param adhocScreen
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public  void doubleclickOnImage(Screen adhocScreen,String picPath) throws InterruptedException, FindFailed {
		try {
			adhocScreen.doubleClick(picPath);
			Thread.sleep(200);
		} catch (Exception e) {
			Thread.sleep(1000);
			adhocScreen.doubleClick(picPath);
		}
	}


	public void copyToclpbrd() throws AWTException, InterruptedException {
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
	/**
	 * @throws UnsupportedFlavorException
	 * @throws IOException
	 * @throws InterruptedException 
	 * @throws AWTException 
	 */
	public	void pasteFromClpbrd() throws UnsupportedFlavorException, IOException, InterruptedException, AWTException {
	
		

		
		try {
			String clipboardContents = getClipboardContents();
			byte[] bytes = clipboardContents.getBytes(StandardCharsets.UTF_16);
			String str = new String (bytes,StandardCharsets.UTF_16);
			FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir")+"/AdobeResource/temp/tmp.txt"));
			fileOutputStream.write(bytes);
			fileOutputStream.close();
			//System.out.println(str);
			Path path = Paths.get(System.getProperty("user.dir")+"/AdobeResource/temp/tmp.txt");
			byte[] data = Files.readAllBytes(path);
			String s = new String(data);
			//System.out.println(s);
			BufferedReader in = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/AdobeResource/temp/tmp.txt")));
			String line;
			while((line = in.readLine()) != null)
			{
			    System.out.println(line);
			}
			in.close();
			
			
			
			//System.out.println(c.getData((DataFlavor.javaFileListFlavor)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
	/*	
		Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents( null);
	        if ( t.isDataFlavorSupported(DataFlavor.getTextPlainUnicodeFlavor()) )
	        {
	            Object o = t.getTransferData( DataFlavor.getTextPlainUnicodeFlavor() );

	            System.out.println( DataFlavor.getTextPlainUnicodeFlavor().stringFlavor);
	            String data = (String)t.getTransferData(  DataFlavor.getTextPlainUnicodeFlavor().stringFlavor);
	            
	            //You need to Assert here all the data 
	            
	        	File file =new File("javaio-appendfile.txt");
	    		
	    		//if file doesnt exists, then create it
	    		if(!file.exists()){
	    			file.createNewFile();
	    		}
	    		FileWriter fileWritter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(data);
    	        bufferWritter.close();
    	        
 
    	        
    	    
	        System.out.println("Done");
	            System.out.println( "Clipboard contents: " + data );
	        }*/
	}
	public static void main(String s[]){
		AdhocUtil adhoc = new AdhocUtil();
		adhoc.testing();
	}
	
	
	

	    public  void testing() {
	        //String message = "Ð•Ð·Ð¸ÐºÐ¾Ð²Ð°";
	        String message = "LiuYan åˆ˜ç ”";
	        System.out.println("Original Message : " + message);
	        StringSelection ss = new StringSelection(message);
	        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	        cb.setContents(ss, null);

	        String result = getClipboardContents2();
	        System.out.println("Result : " + result);
	    }

	    public   String getClipboardContents2() {
	    	
	    	
	        String result = "";
	        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        //odd: the Object param of getContents is not currently used
	        Transferable contents = clipboard.getContents(null);
	        boolean hasTransferableText =
	                (contents != null)
	                && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
	        if (hasTransferableText) {
	            try {
	                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
	            } catch (UnsupportedFlavorException ex) {
	                //highly unlikely since we are using a standard DataFlavor
	                System.out.println(ex);
	                ex.printStackTrace();
	            } catch (IOException ex) {
	                System.out.println(ex);
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }
	public String getClipboardContents() {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //odd: the Object param of getContents is not currently used
        Transferable contents = null;
		try {
			
			contents = clipboard.getContents(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
        boolean hasTransferableText =
                (contents != null)
                && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                //highly unlikely since we are using a standard DataFlavor
                System.out.println(ex);
                ex.printStackTrace();
            } catch (IOException ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return result;
    }
	
	
	public void  openFile(){
		try{

	        if ((new File(System.getProperty("user.dir")+"/AdobeResource/temp/TempFile.txt")).exists()) {

	            Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler"+ System.getProperty("user.dir")+"/AdobeResource/temp/TempFile.txt");
	            p.waitFor();

	        } else {

	            System.out.println("File does not exist");

	        }

	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }
	}
	
}
