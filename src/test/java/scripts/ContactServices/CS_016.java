package scripts.ContactServices;


import libs.clients.AADSWebKeywords;
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

import excel.ExcelData;
import excel.ExcelUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.windows.WindowsDriver;

import static org.junit.Assert.*;
import testData.aadsData;

public class CS_016 {

/*###########################################################################################################
	CS-016 - Verify that communicator contacts added or deleted from SMGR are getting reflected in AADS

	"Objective:
	Verify that communicator contacts added or deleted from SMGR are getting reflected in AADS.
	 
	Load Lineup:
	1. AC-Android 3.0 build 278
	2. AC-iOS 3.0 build 223
	3. AC-Mac 3.0 build 82
	4. AC-Windows 3.0 build 113
	5. AADS build 7.0.1.0.3064
	6. SV Pune lab environment (refer https://confluence.forge.avaya.com/display/UCASV/Aura+7.0+Lab )
	 
	User Details:
	1. User A logged-into AC-Android
	2. User B logged-into AC-iOS
	3. User C logged-into AC-Mac
	4. User D logged-into AC-Windows
	 
	Steps to Reproduce:
	================
	1. Login users A, B, C, D into device services have make sure each user has multiple communicator contacts
	2. Login into SMGR and add new contact for all users and delete couple of existing communicators
	3. Wait and verify if updates done from SMGR in contacts of user A, B, C, D are reflected in AADS. AC-Clients should display contact updates
	 
	Expected Results
	==============
	Step 3, Communicator Contacts added or deleted from SMGR should get reflected in AADS"
#################################################################################################################*/ 
	
	static IOSDriver iOSClientDriver;
	static WiniumDriver winDriver;
	static WindowsDriver<?> windowsDriver;
	static WindowsDriver<?> windowsDriverRoot;
	static AndroidDriver<?> androidClientDriver;
	static WebDriver webDriver;
	
	SMGRKeywords SMGRWebDriver = new SMGRKeywords();
	SMGR8Keywords SMGR8WebDriver = new SMGR8Keywords();
	IOSClientKeywords iosClient = new IOSClientKeywords();
	WindowsClientKeywords winClient = new WindowsClientKeywords();
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	final static Logger logger = LogManager.getLogger("CS_016");
	String configurationName = "configuration1";
	int numberOfContact = 1;
	String sheet = "Users";
	String SMGRversion = aadsData.SMGR_VERSION;
	ExcelData excel = new ExcelData();
	String Winium_URL = aadsData.WiniumURL(1);
	String WinApp_URL = aadsData.WinAppURL(1) ;
	
	@Before
	public void beforeTest_CreateConfigurationandPublish() throws Exception {
		logger.info("beforeTest ContactService_016 starting...\n");
		
		webDriver = driverMgnt.createFFDriver();
		webDriver.manage().window().maximize();
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		winDriver = driverMgnt.createWindowsClientDriver(Winium_URL);
		windowsDriverRoot = driverMgnt.createWinAppDriver(WinApp_URL);
		winClient.confirmOpenApp(windowsDriverRoot);
		iOSClientDriver = driverMgnt.createIOSClientDriver();
		
		logger.info("beforeTest ContactService_016 completed...\n");
	}
	
	@Test
	public void testContactService_016() throws Exception {
		logger.info("test ContactService_016 - Starting\n");
		try {
			webDriver.get(aadsData.SMGR_WEB_ADDR);
			switch (SMGRversion) {
			case "7":
				SMGRWebDriver.loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.navigatetoUserManagement(webDriver);
				SMGRWebDriver.manageUserSearchUserBySipPhone(webDriver, aadsData.AADS_USER_1_NAME_SIP_PHONE);
				SMGRWebDriver.manageUserPageActionUser(webDriver, "Edit");
				SMGRWebDriver.editUserAddNewContact(webDriver, aadsData.AADS_ADD_CONTACT_SEARCH_BY_FIRST_NAME1);
				
			case "8":
				SMGRWebDriver.smgr8_loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.smgr8_navigatetoUserManagement(webDriver);
				SMGRWebDriver.smgr8_manageUserSearchUserBySipPhone(webDriver, aadsData.AADS_USER_1_NAME_SIP_PHONE);
				SMGRWebDriver.smgr8_manageUserPageActionUser(webDriver, "Edit");
				SMGRWebDriver.smgr8_editUserAddNewContact(webDriver, aadsData.AADS_ADD_CONTACT_SEARCH_BY_FIRST_NAME1);
			
			}
			//SMGR8WebDriver.editUserAddNewContact(webDriver, aadsData.AADS_ADD_CONTACT_SEARCH_BY_LAST_NAME1);
			//ACA verify contact is existed
			Thread.sleep(150000);
			
			boolean s = androidClient.verifyContactIsExistedInContactList(androidClientDriver, aadsData.AADS_ADD_CONTACT_DISPLAY_IN_CONTACT_LIST_1);
			if (s == true)
			{
				logger.info("CS016 -User is existed in contact list.....\n");
			}
			else throw new Exception("CS016- Failed - Exception occurs:  User is not existed in contact list..Incorrect .....\n");
			//ACI verify contact is existed
			iosClient.verifyContactIsExistedContactList(iOSClientDriver, aadsData.AADS_ADD_CONTACT_DISPLAY_IN_CONTACT_LIST_1); 
			//ACW verify contact is existed in contact list
			winClient.mainWindowsNavigateToContactTab(windowsDriverRoot);
			winClient.verifyContactIsExistedInContactList(windowsDriverRoot, aadsData.AADS_ADD_CONTACT_DISPLAY_IN_CONTACT_LIST_1);
			
			switch(SMGRversion) {
			case "7":
				SMGRWebDriver.manageUserPageActionUser(webDriver, "Edit");
				SMGRWebDriver.editUserRemoveContact(webDriver, aadsData.AADS_ADD_CONTACT_SEARCH_BY_FIRST_NAME1);
				
			case "8":
				SMGRWebDriver.smgr8_manageUserPageActionUser(webDriver, "Edit");
				SMGRWebDriver.smgr8_editUserRemoveContact(webDriver, aadsData.AADS_ADD_CONTACT_SEARCH_BY_FIRST_NAME1);
			}
			
			Thread.sleep(150000);
			s = androidClient.verifyContactIsExistedInContactList(androidClientDriver, aadsData.AADS_ADD_CONTACT_DISPLAY_IN_CONTACT_LIST_1); 
			if (s == false)
			{
				logger.info("CS016 -User is not existed in contact list.....\n");
			}
			else throw new Exception("CS016- Failed - Exception occurs:  User is existed in contact list..Incorrect .....\n");
			
		} catch (Exception exception) {
			fail("test ContactService_016 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		androidClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
