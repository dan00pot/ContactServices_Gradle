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

public class CS_060 {
	
/*###########################################################
 * CS-060 - Verify all contacts in contact list are retrived completly and the contact of deleted user still exist in contact list after user is deleted in SMGR
	"Verify that contact of the deleted user still displayed in contact list 
	*Procedure:
	1. Access to Equinox client by autoconfig login
	2. Add SMGR user to contact list by Enterprise search
	3. Login SMGR WebUI
	4. Navigate to contact tab of client user to verify new added contact
	5. In SGMR webUI, hard delete SMGR user which have been added to contact list
	6. Logout then relogin Equinox Client
	7. Navigate to contact tab to verify contact list of client 
	8. In SMGR webUI, navigate to contact tab of client user and verify that user still exist in SMGR contact tab as private contact
	*Expected result:
	1. Access to Equinox client successfully
	2. Contact of LDAP user can be added and displayed in contact list
	3. Login to SMGR WebUI successfully
	4. The added contact is displayed in contact tab of user detail in SMGR as public contact
	5. User is deleted successfully without errors
	6. Re-login Equinox client without error
	7. All contacts of user are retrived and displayed in contact list completely and the contact of deleted user still exist in contact list
	8. The added contact is displayed in contact tab of user in SMGR as private contact"
 * ###########################################################*/

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

	final static Logger logger = LogManager.getLogger("CS_060");
	String configurationName = "configuration1";
	int numberOfContact = 1;
	String sheet = "Users";
	String SMGRversion = aadsData.SMGR_VERSION;
	ExcelData excel = new ExcelData();
	
	@Before
	public void beforeTest_ContactService_060() throws Exception {
		logger.info("beforeTest_ContactService_060 starting...\n");
		
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		webDriver = driverMgnt.createChromeDriver();
		
		logger.info("beforeTest ContactService_060 completed...\n");
	}
	
	@Test
	public void testContactService_060() throws Exception {
		logger.info("testContactService_060 - Starting\n");
		try {

			for (int i = 1; i<= numberOfContact;i++){
				
				String smgrname = "user0" + i + " smgr";
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
				SMGRWebDriver.navigatetoContactTabAndSearchContacts(webDriver, aadsData.AADS_VERIFY_SMGR_CONTACT_LIST_ON_SMGR);
				//Verify new added contact
				for (int i = 1; i<= numberOfContact;i++){
					String lastname = "user0" + i;
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
				SMGRWebDriver.searchUserByFirstName(webDriver, "user01");
				SMGRWebDriver.manageUserPageActionUser(webDriver, "Delete");
				SMGRWebDriver.hardDeleteSMGRUser(webDriver, "user01");
				SMGRWebDriver.logofSMGRMainPage(webDriver);
			case "8":
				SMGRWebDriver.smgr8_loginSMGRMainPage(webDriver, aadsData.SMGR_ADMIN_USR, aadsData.SMGR_ADMIN_PWD);
				SMGRWebDriver.smgr8_navigatetoUserManagement(webDriver);
				SMGRWebDriver.smgr8_manageUserSearchUserBySipPhone(webDriver, aadsData.AADS_USER_1_NAME_SIP_PHONE);
				SMGRWebDriver.smgr8_manageUserPageActionUser(webDriver, "Edit");
				SMGRWebDriver.smgr8_navigatetoContactTabAndSearchContacts(webDriver, aadsData.AADS_VERIFY_SMGR_CONTACT_LIST_ON_SMGR);
				//Verify new added contact
				for (int i = 1; i<= numberOfContact;i++){
					String lastname = "user0" + i;
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
				SMGRWebDriver.smgr8_searchUserByFirstName(webDriver, "user01");
				SMGRWebDriver.smgr8_manageUserPageActionUser(webDriver, "Delete");
				SMGRWebDriver.smgr8_hardDeleteSMGRUser(webDriver, "user01");
				SMGRWebDriver.smgr8_logofSMGRMainPage(webDriver);
			}
			
			androidClientDriver.resetApp();
			androidClient.autoConfigLogin(androidClientDriver, aadsData.AADS_SERVER_ADDRESS_AUTOCONFIG, aadsData.AADS_USER_1_NAME, aadsData.AADS_USER_PWD);
			Thread.sleep(150000);
			boolean s = androidClient.verifyContactIsExistedInContactList(androidClientDriver, 
					aadsData.AADS_ADD_CONTACT_DISPLAY_IN_CONTACT_LIST_1);
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
				SMGRWebDriver.navigatetoContactTabAndSearchContacts(webDriver, aadsData.AADS_VERIFY_SMGR_CONTACT_LIST_ON_SMGR);
				for (int i = 1; i<= numberOfContact;i++){
					String lastname = "user0" + i;
					if(SMGRWebDriver.verifyIfContactisExsitedInContactList(webDriver, lastname)){
						logger.info("PASSED");
						
						if(SMGRWebDriver.verifyIfContactisExsitedInContactList(webDriver, "Private Contact")){
							logger.info("PASSED");
						} else{
							logger.error("Contact is not exist: " + "123");
							throw new Exception("Contact is not exist: " + "123");
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
				SMGRWebDriver.smgr8_navigatetoContactTabAndSearchContacts(webDriver, aadsData.AADS_VERIFY_SMGR_CONTACT_LIST_ON_SMGR);
				for (int i = 1; i<= numberOfContact;i++){
					String lastname = "user0" + i;
					if(SMGRWebDriver.smgr8_verifyIfContactisExsitedInContactList(webDriver, lastname)){
						logger.info("PASSED");
						
						if(SMGRWebDriver.smgr8_verifyIfContactisExsitedInContactList(webDriver, "Private Contact")){
							logger.info("PASSED");
						} else{
							logger.error("Contact is not exist: " + "123");
							throw new Exception("Contact is not exist: " + "123");
						}
							
					} else{
						logger.error("Contact is not exist: " + lastname);
						throw new Exception("Contact is not exist: " + lastname);
					}
				}
			}	
		} catch (Exception exception) {
			fail("ContactService_060 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		androidClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
