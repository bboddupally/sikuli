package com.ciphercloud.qa.base;

public class ReportServerTest {
	
	public static void main(String[] args) {
		ReportServerTest reportServerTest = new ReportServerTest();
		reportServerTest.run();
	}
	
	public void run() {
		ReportServerListener reportServerListener = new ReportServerListener();
		reportServerListener.test();
	}

}
