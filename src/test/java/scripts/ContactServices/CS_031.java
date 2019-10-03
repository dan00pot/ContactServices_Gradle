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

public class CS_031 {
	
/*############################################################################
 * CS-031 - Private contact sync
	"Procedures:
	1. Adding a private contact ""Patch4 No4"" for ""huythai1"" user on client
	2. Checking contact on SMGR.
	3. Adding a private contact ""Patch444 Build3345"" for ""huythai1"" user on SMGR, also add the same contact to Associated Contacts for ""huythai1""
	4. Checking the above contact on client.
	
	
	Expected result:
	2. Make sure the contact displays in Private Contacts of user on SMGR
	4. Make sure this private contact displays on client."
##############################################################################*/

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
	
	final static Logger logger = LogManager.getLogger("CS_031");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "Users";
	String SMGRversion = aadsData.SMGR_VERSION;
	ExcelData excel = new ExcelData();
	
	@Before
	public void beforeTestContactService_031() throws Exception {
		logger.info("beforeTest ContactService_031 starting...\n");
		
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		webDriver = driverMgnt.createChromeDriver();
		
		logger.info("beforeTest ContactService_031 completed...\n");
	}
	
	@Test
	public void testContactService_031() throws Exception {
		logger.info("testContactService_031 - Starting\n");
		try {
			webDriver.get(aadsData.SMGR_WEB_ADDR);
			switch(SMGRversion) {
			case "7":
				SMGRWebDriver.loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.navigatetoUserManagement(webDriver);
				SMGRWebDriver.manageUserSearchUserBySipPhone(webDriver, aadsData.AADS_USER_1_NAME_SIP_PHONE);
				SMGRWebDriver.manageUserPageActionUser(webDriver, "Edit");
//				SMGRWebDriver.editUserAddNewContact(webDriver, aadsData.AADS_ADD_CONTACT_SEARCH_BY_LAST_NAME2);
				SMGRWebDriver.editUserAddNewPrivateContact(webDriver, aadsData.LASTNAME_PRIVATE_CONTACT, aadsData.FIRSTNAME_PRIVATE_CONTACT);
				Thread.sleep(150000);
			case "8":
				SMGRWebDriver.smgr8_loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.smgr8_navigatetoUserManagement(webDriver);
				SMGRWebDriver.smgr8_manageUserSearchUserBySipPhone(webDriver, aadsData.AADS_USER_1_NAME_SIP_PHONE);
				SMGRWebDriver.smgr8_manageUserPageActionUser(webDriver, "Edit");
//				SMGRWebDriver.smgr8_editUserAddNewContact(webDriver, aadsData.AADS_ADD_CONTACT_SEARCH_BY_LAST_NAME2);
				SMGRWebDriver.smgr8_editUserAddNewPrivateContact(webDriver, aadsData.LASTNAME_PRIVATE_CONTACT, aadsData.FIRSTNAME_PRIVATE_CONTACT);
				Thread.sleep(150000);
				
			}

			
			boolean s = androidClient.verifyContactIsExistedInContactList(androidClientDriver,aadsData.AADS_ADD_CONTACT_USER_2_FULL_NAME);
			if (s == true)
			{
				logger.info("testAADS017 -User is existed in contact list.....\n");

			}
			else throw new Exception("testAADS017- Failed - Exception occurs:  User is not existed in contact list..Incorrect .....\n");
			
			switch(SMGRversion) {
			case "7":
				SMGRWebDriver.manageUserPageActionUser(webDriver, "Edit");
				SMGRWebDriver.editUserRemoveContact(webDriver, aadsData.AADS_ADD_CONTACT_SEARCH_BY_LAST_NAME2);
				Thread.sleep(150000);
			case "8":
				SMGRWebDriver.smgr8_manageUserPageActionUser(webDriver, "Edit");
				SMGRWebDriver.smgr8_editUserRemoveContact(webDriver, aadsData.AADS_ADD_CONTACT_SEARCH_BY_LAST_NAME2);
				Thread.sleep(150000);
			}

			s = androidClient.verifyContactIsExistedInContactList(androidClientDriver,aadsData.AADS_ADD_CONTACT_USER_2_FULL_NAME);
			if (s == false)
			{
				logger.info("testAADS017 -User is not existed in contact list.....\n");
			}
			else throw new Exception("testAADS017- Failed - Exception occurs:  User is existed in contact list..Incorrect .....\n");
			
		} catch (Exception exception) {
			fail("testAddContact - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		androidClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
