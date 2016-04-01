package com.ciphercloud.qa.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

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
			click(adhocScreen, picPath);
		} catch (Exception e) {
			click(adhocScreen,System.getProperty("user.dir")+"/AdobeResource/imgsAdhoc/imgsAdhoc/"+picPath);
		}
	}

	/**
	 * @param adhocScreen
	 * @param picPath
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	private void click(Screen adhocScreen, String picPath) throws InterruptedException, FindFailed {
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
			new String (bytes,StandardCharsets.UTF_16);
			FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir")+"/AdobeResource/temp/tmp.txt"));
			fileOutputStream.write(bytes);
			fileOutputStream.close();
			//System.out.println(str);
			Path path = Paths.get(System.getProperty("user.dir")+"/AdobeResource/temp/tmp.txt");
			byte[] data = Files.readAllBytes(path);
			new String(data);
			//System.out.println(s);
		
			StringTokenizer tokenizer = new StringTokenizer(readText(new File(System.getProperty("user.dir")+"/AdobeResource/temp/tmp.txt")),"\n");
			int count = tokenizer.countTokens();
			ArrayList<String> notencrypted = new ArrayList<String>();
			for (int i = 0; i < count; ++i) {
				String x = tokenizer.nextToken();
				//System.out.println(EncryptionChecks.verifyEncryptedText(x));
				
				if(!EncryptionChecks.verifyEncryptedText(x)){
					notencrypted.add(x);
					notencrypted.add("\n");
				}
			}
			String clipboardContents2 = notencrypted.toString();
			byte[] bytes2 = clipboardContents2.getBytes(StandardCharsets.UTF_16);
			
			FileOutputStream fileOutputStream2 = new FileOutputStream(new File(System.getProperty("user.dir")+"/AdobeResource/temp/tmp2.txt"));
			fileOutputStream2.write(bytes2);
			fileOutputStream2.close();
			if(!notencrypted.isEmpty()){
				Assert.assertEquals(false, true,"please find the values that are not encrypted file location: "+System.getProperty("user.dir")+"/AdobeResource/temp/tmp2.txt");

			}
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
	public static void main(String s[]) throws IOException{
	/*	BufferedReader in = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/AdobeResource/temp/tmp.txt")));
		String line;
		while((line = in.readLine()) != null)
		{
			//EncryptionChecks.verifyEncryptedText(line);
			System.out.println("======================");
			System.out.println(EncryptionChecks.verifyEncryptedText(line));
		    System.out.println("======================");
		    System.out.println(line);
		}
		in.close();*/
		
	    System.out.println(readText(new File(System.getProperty("user.dir")+"/AdobeResource/temp/tmp.txt")));

	}
	
	
	

	    public  void testing() {
	        //String message = "Ã�â€¢Ã�Â·Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²Ã�Â°";
	        String message = "LiuYan Ã¥Ë†ËœÃ§ â€�";
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
	
	 private static final Charset[] UTF_ENCODINGS = { Charset.forName("UTF-8"),
		      Charset.forName("UTF-16LE"), Charset.forName("UTF-16BE") };

		  private static Charset getEncoding(InputStream in) throws IOException {
		    charsetLoop: for (Charset encodings : UTF_ENCODINGS) {
		      byte[] bom = "\uFEFF".getBytes(encodings);
		      in.mark(bom.length);
		      for (byte b : bom) {
		        if ((0xFF & b) != in.read()) {
		          in.reset();
		          continue charsetLoop;
		        }
		      }
		      return encodings;
		    }
		    return Charset.defaultCharset();
		  }

		  private static String readText(File file) throws IOException {
		    Closer res = new Closer();
		    try {
		      InputStream in = res.using(new FileInputStream(file));
		      InputStream bin = res.using(new BufferedInputStream(in));
		      Reader reader = res.using(new InputStreamReader(bin, getEncoding(bin)));
		      StringBuilder out = new StringBuilder();
		      for (int ch = reader.read(); ch != -1; ch = reader.read()){
		    	  out.append((char) ch);
		      }
		      return out.toString();
		    } finally {
		      res.close();
		    }
		  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
