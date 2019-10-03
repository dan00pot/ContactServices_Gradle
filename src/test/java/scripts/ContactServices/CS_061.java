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

public class CS_061 {
	
/*################################################################
 * CS-061 - Verify that user can search and add SMGR user with no phone number to contact list
	"Verify that user can search and add contact with no phone number
	*Procedure
	1. Access to Equinox client by autoconfig login
	2. Search and add contact of SMGR user which haven't phonenumber
	3. Log out then relogin Equinox client
	4. Navigate to contact tab and verify contact list of client
	5. From SMGR hard delete user which is added to contact list
	6. Log out then relogin Equinox client
	7. Navigate to contact tab and verify contact list of client
	*Expected Result:
	1. Access to Equinox client successfully without error
	2. Able to search and add contact which haven't phone number
	3. Logout and relogin Equinox client successfully
	4. All contacts of user are retrived and displayed in contact list completely
	5. User is deleted successfully
	6. Logout and relogin Equinox client successfully
	7. All contacts of user are retrived and displayed in contact list, the contact of deleted user still displayed in contact list"

 * ###############################################################*/

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

	final static Logger logger = LogManager.getLogger("CS_061");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "Users";
	String SMGRversion = aadsData.SMGR_VERSION;
	ExcelData excel = new ExcelData();
	
	@Before
	public void beforeTest_ContactService_061() throws Exception {
		logger.info("beforeTestContactService_061 starting...\n");
		
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		webDriver = driverMgnt.createChromeDriver();
		
		logger.info("beforeTestContactService_061 completed...\n");
	}
	
	@Test
	public void testContactService_061() throws Exception {
		logger.info("testContactService_061 - Starting\n");
		try {

			// AutoConfig login
			androidClientDriver.resetApp();
			androidClient.autoConfigLogin(androidClientDriver, aadsData.AADS_SERVER_ADDRESS_AUTOCONFIG, aadsData.AADS_USER_1_NAME, aadsData.AADS_USER_PWD);
			
			// Add SMGR user to contact list by Enterprise search
			for (int i = 1; i<= numberOfContact;i++){
				
				String smgrname = "test0" + i + " smgr";
				androidClient.addContactBySearch(androidClientDriver, smgrname);
//				androidClient.backFromContactDetailToContactTab(androidClientDriver);
			}
			
			//Login SMGR WebUI then Navigate to contact tab of client use verify new added contact
			webDriver.get(aadsData.SMGR_WEB_ADDR);
			switch(SMGRversion) {
			case "7":
				SMGRWebDriver.loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.navigatetoUserManagement(webDriver);
				SMGRWebDriver.manageUserSearchUserBySipPhone(webDriver, aadsData.AADS_USER_1_NAME_SIP_PHONE);
				SMGRWebDriver.manageUserPageActionUser(webDriver, "Edit");
				//SMGRWebDriver.navigatetoContactTabAndSearchContacts(webDriver, aadsData.AADS_VERIFY_SMGR_CONTACT_LIST_ON_SMGR_2);
				
				//Verify new added contact
				for (int i = 1; i<= numberOfContact;i++){
					String lastname = "test0" + i;
					if(SMGRWebDriver.verifyIfContactisExsitedInContactList(webDriver, lastname)){
						logger.info("PASSED");
					} else{
						logger.error("Contact is not exist: " + lastname);
						throw new Exception("Contact is not exist: " + lastname);
					}
				}
				Thread.sleep(2000);
				SMGRWebDriver.logofSMGRMainPage(webDriver);
				Thread.sleep(2000);
				//Hard delete SMGR user which have been addded to contact list
				webDriver.get(aadsData.SMGR_WEB_ADDR);
				Thread.sleep(5000);
				SMGRWebDriver.loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.navigatetoUserManagement(webDriver);
				SMGRWebDriver.searchUserByFirstName(webDriver, "test01");
				SMGRWebDriver.manageUserPageActionUser(webDriver, "Delete");
				SMGRWebDriver.hardDeleteSMGRUser(webDriver, "test01");
				SMGRWebDriver.logofSMGRMainPage(webDriver);
			case "8":
				SMGRWebDriver.smgr8_loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.smgr8_navigatetoUserManagement(webDriver);
				SMGRWebDriver.smgr8_manageUserSearchUserBySipPhone(webDriver, aadsData.AADS_USER_1_NAME_SIP_PHONE);
				SMGRWebDriver.smgr8_manageUserPageActionUser(webDriver, "Edit");
				//SMGRWebDriver.navigatetoContactTabAndSearchContacts(webDriver, aadsData.AADS_VERIFY_SMGR_CONTACT_LIST_ON_SMGR_2);
				
				//Verify new added contact
				for (int i = 1; i<= numberOfContact;i++){
					String lastname = "test0" + i;
					if(SMGRWebDriver.smgr8_verifyIfContactisExsitedInContactList(webDriver, lastname)){
						logger.info("PASSED");
					} else{
						logger.error("Contact is not exist: " + lastname);
						throw new Exception("Contact is not exist: " + lastname);
					}
				}
				Thread.sleep(2000);
				SMGRWebDriver.smgr8_logofSMGRMainPage(webDriver);
				Thread.sleep(2000);
				//Hard delete SMGR user which have been addded to contact list
				webDriver.get(aadsData.SMGR_WEB_ADDR);
				Thread.sleep(5000);
				SMGRWebDriver.smgr8_loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.smgr8_navigatetoUserManagement(webDriver);
				SMGRWebDriver.smgr8_searchUserByFirstName(webDriver, "test01");
				SMGRWebDriver.smgr8_manageUserPageActionUser(webDriver, "Delete");
				SMGRWebDriver.smgr8_hardDeleteSMGRUser(webDriver, "test01");
				SMGRWebDriver.smgr8_logofSMGRMainPage(webDriver);	
			}
			
			androidClientDriver.resetApp();
			androidClient.autoConfigLogin(androidClientDriver, aadsData.AADS_SERVER_ADDRESS_AUTOCONFIG, aadsData.AADS_USER_1_NAME, aadsData.AADS_USER_PWD);
			Thread.sleep(150000);
			boolean s = androidClient.verifyContactIsExistedInContactList(androidClientDriver, 
					aadsData.AADS_ADD_CONTACT_DISPLAY_IN_CONTACT_LIST_2);
			if (s == true)
			{
				logger.info("CS016 -User is existed in contact list.....\n");
			}
			else throw new Exception("CS016- Failed - Exception occurs:  User is not existed in contact list..Incorrect .....\n");
			
			webDriver.get(aadsData.SMGR_WEB_ADDR);
			Thread.sleep(5000);
			switch(SMGRversion) {
			case "7":
				SMGRWebDriver.loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.navigatetoUserManagement(webDriver);
				SMGRWebDriver.manageUserSearchUserBySipPhone(webDriver, aadsData.AADS_USER_1_NAME_SIP_PHONE);
				SMGRWebDriver.manageUserPageActionUser(webDriver, "Edit");
				//SMGRWebDriver.navigatetoContactTabAndSearchContacts(webDriver, aadsData.AADS_VERIFY_SMGR_CONTACT_LIST_ON_SMGR_2);
				for (int i = 1; i<= numberOfContact;i++){
					String lastname = "test0" + i;
					if(SMGRWebDriver.verifyIfContactisExsitedInContactList(webDriver, lastname)){
						logger.info("PASSED");
						
						if(SMGRWebDriver.verifyIfContactisExsitedInContactList(webDriver, "Private Contact")){
							logger.info("PASSED");
						} else{
							logger.error("Contact is not exist: " + lastname);
							throw new Exception("Contact is not exist: " + lastname);
						}
							
					} else{
						logger.error("Contact is not exist: " + lastname);
						throw new Exception("Contact is not exist: " + lastname);
					}
				}
			case "8":
				SMGRWebDriver.smgr8_loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.smgr8_navigatetoUserManagement(webDriver);
				SMGRWebDriver.smgr8_manageUserSearchUserBySipPhone(webDriver, aadsData.AADS_USER_1_NAME_SIP_PHONE);
				SMGRWebDriver.smgr8_manageUserPageActionUser(webDriver, "Edit");
				//SMGRWebDriver.navigatetoContactTabAndSearchContacts(webDriver, aadsData.AADS_VERIFY_SMGR_CONTACT_LIST_ON_SMGR_2);
				for (int i = 1; i<= numberOfContact;i++){
					String lastname = "test0" + i;
					if(SMGRWebDriver.smgr8_verifyIfContactisExsitedInContactList(webDriver, lastname)){
						logger.info("PASSED");
						
						if(SMGRWebDriver.smgr8_verifyIfContactisExsitedInContactList(webDriver, "Private Contact")){
							logger.info("PASSED");
						} else{
							logger.error("Contact is not exist: " + lastname);
							throw new Exception("Contact is not exist: " + lastname);
						}
							
					} else{
						logger.error("Contact is not exist: " + lastname);
						throw new Exception("Contact is not exist: " + lastname);
					}
				}
			}

		} catch (Exception exception) {
			fail("testContactService_061 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		androidClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
