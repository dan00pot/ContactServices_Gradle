package scripts.ContactServices;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import excel.ExcelData;
import io.appium.java_client.android.AndroidDriver;
import libs.clients.AndroidClientKeywords;
import libs.common.DriverManagement;
import libs.common.Selenium;
import libs.listener.ExtentManager;
import testData.aadsData;

public class test {
	static AndroidDriver<?> androidClientDriver;
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	
	@Before
	public void beforeTest_ContactService_010() throws Exception {
		
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		
	}
	
	@Test
	public void testContactService_010() throws Exception {
		
		try {
			selenium.clickElement(androidClientDriver, By.xpath(""));
			selenium.clickElement(androidClientDriver, By.xpath(""));
			selenium.clickElement(androidClientDriver, By.xpath(""));
			selenium.clickElement(androidClientDriver, By.xpath(""));
			selenium.clickElement(androidClientDriver, By.xpath(""));
		
		} catch (Exception exception) {
			fail("ContactService_010 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		
		androidClientDriver.quit();
	}
}
