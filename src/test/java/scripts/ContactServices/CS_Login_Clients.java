package scripts.ContactServices;

import libs.clients.AndroidClientKeywords;
import libs.clients.IOSClientKeywords;
import libs.clients.SMGR8Keywords;
import libs.clients.SMGRKeywords;
import libs.clients.WindowsClientKeywords;
import libs.common.DriverManagement;
import libs.common.Selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.WiniumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.windows.WindowsDriver;

import static org.junit.Assert.*;
import testData.aadsData;

public class CS_Login_Clients {

	static IOSDriver iOSClientDriver;
	static WiniumDriver winDriver;
	static WindowsDriver<?> windowsDriver;
	static WindowsDriver<?> windowsDriverRoot;
	static AndroidDriver<?> androidClientDriver;
	static WebDriver webDriver;
	

	IOSClientKeywords iosClient = new IOSClientKeywords();
	WindowsClientKeywords winClient = new WindowsClientKeywords();
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	final static Logger logger = LogManager.getLogger("AutoconfigLogin");

	@Before
	public void beforeTest_AutoconfigLogin() throws Exception {
		logger.info("beforeTest_AutoconfigLogin starting...\n");
		//androidClientDriver = driverMgnt.createAndroidClientDriver();
		//winDriver = driverMgnt.createWindowsClientDriver("http://10.255.249.9:9999");
		//windowsDriverRoot = driverMgnt.createWinAppDriver("http://10.255.249.9:4724");
		//winClient.confirmOpenApp(windowsDriverRoot);
		iOSClientDriver = driverMgnt.createIOSClientDriver();
		logger.info("beforeTest_AutoconfigLogin completed...\n");
	}

	@Test
	public void testAutoconfigLogin() throws Exception {
		logger.info("testAutoconfigLogin - Starting\n");
		try {
			//Reset Application
			
			iosClient.resetApp(iOSClientDriver);
			Thread.sleep(5000);
			iOSClientDriver = driverMgnt.createIOSClientDriver();
			iosClient.autoConfigLogin(iOSClientDriver, aadsData.AADS_SERVER_ADDRESS_AUTOCONFIG, aadsData.AADS_USER_1_NAME, aadsData.AADS_USER_PWD);
			/*
			androidClientDriver.resetApp();
			androidClient.autoConfigLogin(androidClientDriver, aadsData.AADS_SERVER_ADDRESS_AUTOCONFIG, aadsData.AADS_USER_1_NAME, aadsData.AADS_USER_PWD);*/

			
		//	winClient.resetApplication(windowsDriverRoot);
		//	winClient.autoConfigLogin(windowsDriverRoot,aadsData.AADS_SERVER_ADDRESS_AUTOCONFIG, aadsData.AADS_USER_1_NAME, aadsData.AADS_USER_PWD);
			
			
		
		} catch (Exception exception) {
			logger.error("testAutoconfigLogin - Failed with Exception:" + exception
					+ "...\n");
			fail("testAutoconfigLogin - Failed - Exception occurs: "
					+ exception.toString());
		} 
		
		logger.info("testAutoconfigLogin - test completed\n");
	}

//	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		androidClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
