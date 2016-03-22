package com.ciphercloud.qa.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.WebDriverException;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class TestNGNotRunListener implements ITestListener,ISuiteListener,IInvokedMethodListener{
	
	//------------------------------------Declaration of variables-----------------------------
	List<String> testName=new ArrayList<String>();;
	String suiteName;
	static String testTagName;
	ListMultimap<String, String> notRun = ArrayListMultimap.create();
	
	
	ArrayList<String> notRUnMethod=new ArrayList<String>();
	Set<String> testNGClassName=new HashSet<String>();
	List<String> testClassName=new ArrayList<String>();
	static String mapClass;
	DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
    int count=0;
   public static String testNGFilePath=System.getProperty("user.dir")+"/testng_not_run.xml";
     
	
	
	//------------------------------------Test Methods-----------------------------------------

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}
	

	public void onStart(ITestContext testContext) {
		// TODO Auto-generated method stub
	//	testName
		//System.out.println("size before adding"+testName.size());
		////System.out.println("test name"+testContext.getName());
		
		//testName.add(testContext.getName());
		count++;
		//System.out.println("print count"+count);
		//System.out.println("size"+testName.size());
		
	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}


	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		Throwable thr = result.getThrowable();
		
        if(thr instanceof WebDriverException){
        	String className=result.getTestClass().getName();
        	
        		
        		testNGClassName.add(result.getTestClass().getName());
        		mapClass=result.getTestClass().getName();
        	
        		testTagName=result.getTestContext().getName();
        		////System.out.println("*******print test name********"+result.getTestContext().getName()+"***"+result.getTestContext().toString());
        		testName.add(result.getTestContext().getName());
        		notRun.put(mapClass, result.getName());
        		
       
        
        }
      }


	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}


	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}


	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}


	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		//Creating testng_not_run.xml 
		//System.out.println("on finish invoked");
		testClassName.addAll(testNGClassName);
		docFactory=DocumentBuilderFactory.newInstance();
		////System.out.println("print size of not run"+notRun.size());
		//System.out.println("********print name of the mthods  class*******"+notRun.get("com.ciphercloud.qa.test.Sample"));
		
		try{
			if(notRun!=null){
			docBuilder=docFactory.newDocumentBuilder();
			Document doc=docBuilder.newDocument();
			Element rootElement = doc.createElement("suite");
			doc.appendChild(rootElement);
			rootElement.setAttribute("name", suiteName+" Not Run");
			rootElement.setAttribute("verbose", "1");
			rootElement.setAttribute("parallel", "tests");
			rootElement.setAttribute("thread-count", "2");
			rootElement.setAttribute("preserve-order", "true");
			for(int k=0;k<testClassName.size();k++){
			//if(testName.get(k).equals(testTagName)){
				//System.out.println("invoked test suite"+testName.size());
				//creating test
				Element test=doc.createElement("test");
				rootElement.appendChild(test);
				test.setAttribute("name", testName.get(k));
				test.setAttribute("preserve-order", "true");
			
				//creating classes
				Element classes=doc.createElement("classes");
				test.appendChild(classes);
			
				//creating class
				
				
					Element className=doc.createElement("class");
					classes.appendChild(className);
					
				
					className.setAttribute("name", testClassName.get(k));
					
				
				//creating methods
				Element methods=doc.createElement("methods");
				className.appendChild(methods);
			if(notRun.get(testClassName.get(k))!=null){
				//testMethodName
				List<String> testMethodName=new ArrayList<String>();
				//System.out.println("*******print method under class"+testClassName.get(k)+"******"+notRun.get(testClassName.get(k)));
				testMethodName.addAll(notRun.get(testClassName.get(k)));
				//System.out.println("Before adding to method test name--->"+notRun.get(testClassName.get(k))+"test class------->"+testClassName.get(k));
				
				//creating method
				for(int i=0;i<testMethodName.size();i++){
					Element method=doc.createElement("include");
					methods.appendChild(method);
					method.setAttribute("name", testMethodName.get(i));
					//System.out.println("list of methods ------->"+testMethodName.get(i));
			
				}
				
			}
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(testNGFilePath));
			transformer.transform(source, result);
	 
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			
	 
			//System.out.println("File saved!");
		
			}
			//}
			//System.out.println("test size is-------------->"+testName.size());
		}
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		 } catch (TransformerException tfe) {
			tfe.printStackTrace();
		 }
		
	}


	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		suiteName=suite.getName();
		File file = new File(testNGFilePath);
		if(file.exists()){
			if(file.delete()){
				//System.out.println(file.getName() + " is deleted!");
			}else{
				//System.out.println("Delete operation is failed.");
			}
		}
	}



	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}



	public void beforeInvocation(IInvokedMethod method, ITestResult arg1) {
		// TODO Auto-generated method stub
		//testNGClassName=new HashSet<String>();
		if (method.isTestMethod()){
			//testNGClassName.add(method.getTestMethod().getConstructorOrMethod().getDeclaringClass().getCanonicalName());
			
			}
		
		
	}

}
