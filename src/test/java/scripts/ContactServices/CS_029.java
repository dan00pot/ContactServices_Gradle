package scripts.ContactServices;


import libs.clients.AndroidClientKeywords;
import libs.clients.IOSClientKeywords;
import libs.clients.WindowsClientKeywords;
import libs.common.DriverManagement;
import libs.common.Selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.winium.WiniumDriver;

import excel.ExcelData;
import excel.ExcelUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.windows.WindowsDriver;

import static org.junit.Assert.*;
import testData.aadsData;

public class CS_029 {
	
/*########################################################################################
 * "Objective: To verify that contact are updated on MDA devices
	 
	Configuration:
	On SMGR have thousands of users
	Add 20-30 contacts for some users A, B and C
	MDA user A user, as well as AADS, logs in some devices ACW, ACW, ACI and ACA
	User A
	 
	Procedures:
	MDA User A on ACW and on ACM add some more contacts simultaneously. MDA users A checks contact updates on all devices.
	MDA User A on ACW and on ACI delete some contacts simultaneously. MDA users A checks deleted contact updated on all devices.
	MDA User A on ACW and on ACA update some more contacts simultaneously. MDA users A checks contact updates on all devices.
	MDA user A on ACM and on ACI search and add a contact to favorite simultaneously. MDA users A checks favorite contact updates on all devices
	MDA user A on ACM and on ACA search and unfavorite some contact simultaneously. MDA users A checks favorite contact updates on all devices
	 
	Expected results:
	Contact updates show properly on all devices of MDA user A
	Contact updates show properly on all devices of MDA user A
	Contact updates show properly on all devices of MDA user A
	Contact updates show properly on all devices of MDA user A
	Contact updates show properly on all devices of MDA user A
	 
	Notes: Reference jira ACS-3916"
######################################################################################*/

	static AndroidDriver<?> androidClientDriver;
	static IOSDriver iOSClientDriver;
	static WiniumDriver winClientDriver;
	static WindowsDriver<?> windowsDriver;
	static WindowsDriver<?> windowsDriverRoot;
	
	WindowsClientKeywords winClient = new WindowsClientKeywords();

	IOSClientKeywords iosClient = new IOSClientKeywords();
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	
	
	final static Logger logger = LogManager.getLogger("CS_029");
	String configurationName = "configuration1";
	int numberOfContact = 1;
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	
	String Winium_URL = aadsData.WiniumURL(1);
	String WinApp_URL = aadsData.WinAppURL(1) ;
	
	@Before
	public void beforeTest_ContactService_029() throws Exception {
		logger.info("beforeTest ContactService_029 starting...\n");
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		iOSClientDriver = driverMgnt.createIOSClientDriver();
		winClientDriver = driverMgnt.createWindowsClientDriver(Winium_URL);
		windowsDriverRoot = driverMgnt.createWinAppDriver(WinApp_URL);
		winClient.confirmOpenApp(windowsDriverRoot);
		logger.info("beforeTest ContactService_029 completed...\n");
	}
	
	@Test
	public void testContactService_029() throws Exception {
		logger.info("testContactService_029 - Starting\n");
		try {
			String contact_name = excel.User_Switch_DN(1, sheet);
			String contact_mail =  excel.User_Switch_MAIL(1, sheet);
		
			winClient.mainWindowsNavigateToContactTab(windowsDriverRoot);
			winClient.addPrivateContact(windowsDriverRoot, "abc", "xyz", "123456789",contact_mail);
			androidClient.addPrivateContact(androidClientDriver, "Equinox Contact","7018","7018","CS018@aam1.com");
			iosClient.addPrivateContact(iOSClientDriver,"Equinox","7017","7017","CS017@aam1.com");
			
			Thread.sleep(300000);
			
			//androidClient.verifyContactIsExistedContactList(androidClientDriver, "HuyD AMM");
			androidClient.verifyContactIsExistedInContactList(androidClientDriver, contact_name);
			winClient.verifyContactIsExistedInContactList(winClientDriver, "Equinox 7017");
			iosClient.verifyContactIsExistedContactList(iOSClientDriver, "Equinox Contact 7018");
			
			//winClient.deleteContact(winClientDriver, "HuyD AMM");
			winClient.removeContact(windowsDriverRoot, contact_name);
			iosClient.removeContact(iOSClientDriver, "Equinox Contact 7018");
			
			androidClient.editExistedContactInfo(androidClientDriver, "Equinox 7017", "first_name", "Editted", "7017");
			Thread.sleep(300000);
			boolean n1 = winClient.verifyContactIsExistedInContactList(windowsDriverRoot, "Editted 7100");
			boolean n2 = iosClient.verifyContactIsExistedContactList(iOSClientDriver, "Editted 7100");
			
			assertTrue(n1 & n2);
			
			androidClient.removeFavoriteContact(androidClientDriver, "Editted 7100");
			androidClient.verifyUserIsFavoriteContact(androidClientDriver, "Editted 7100");
			iosClient.makeSureContactIsFavorite(iOSClientDriver, "Editted 7100");
			winClient.contactsIsContactFavorite(windowsDriverRoot, "Editted 7100");
					
			androidClient.removeFavoriteContact(androidClientDriver, "Editted 7100");

		} catch (Exception exception) {
			fail("ContactService_029 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		androidClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
