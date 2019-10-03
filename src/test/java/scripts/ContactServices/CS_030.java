package scripts.ContactServices;


import libs.clients.AndroidClientKeywords;
import libs.common.DriverManagement;
import libs.common.Selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excel.ExcelData;
import excel.ExcelUtils;
import io.appium.java_client.android.AndroidDriver;

import static org.junit.Assert.*;
import testData.aadsData;

public class CS_030 {
	
/*#########################################################################################
	 * "Steps to reproduce:
	================
	1. Upgrading ACW to the latest build
	2. Login ACW client using Auto Configure via Web.
	3. Go to contact tab and try to make a communicator contact as favorite or unfavorite a contact
	4. Do AADS search contact, select a contact then hit favorite icon to add contact to favorite contact list
	5. Observe the time response when adding favorite contacts
	6. Go to Favorite contact list and check that contact
	 
	Expected results:
	================
	Step 3: Able make a contact as favorite or unfavorite a contact without issue
	Step 4: Able to seach a contact and add him to favorite contact list
	Step 5: Make sure user can search and add a contact to favorite without deplay.
	Step 6: Make sure the new added contact are present in Favourite contact list"
#######################################################################################*/	

	static AndroidDriver<?> androidClientDriver;
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();


	final static Logger logger = LogManager.getLogger("CS_030");
	String configurationName = "configuration1";
	int numberOfContact = 10;
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	
	@Before
	public void beforeTest_ContactService_030() throws Exception {
		logger.info("beforeTest_AddContact starting...\n");
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		logger.info("beforeTest_AddContact completed...\n");
	}
	
	@Test
	public void testContactService_030() throws Exception {
		logger.info("test ContactService_030 - Starting\n");
		try {
			androidClient.navigateToContactScreen(androidClientDriver);
			androidClient.addContactBySearch(androidClientDriver, aadsData.AADS_ADD_CONTACT_USER1_FULL_NAME);
			androidClient.addFavoriteContact(androidClientDriver, aadsData.AADS_ADD_CONTACT_USER1_FULL_NAME);
			androidClient.backFromContactDetailToContactTab(androidClientDriver);
			boolean n=androidClient.verifyUserIsFavoriteContact(androidClientDriver, aadsData.AADS_ADD_CONTACT_USER1_FULL_NAME);
			androidClient.removeFavoriteContact(androidClientDriver, aadsData.AADS_ADD_CONTACT_USER1_FULL_NAME);
			androidClient.backFromContactDetailToContactTab(androidClientDriver);
			androidClient.removeContactInContactList(androidClientDriver, aadsData.AADS_ADD_CONTACT_USER1_FULL_NAME);
			assertTrue(n);
			if (n=true) {
				System.out.println("Add Favorite Contact - PASSED");
			}else {
				System.out.println("Add Favorite Contact - FAILED");
			}
		} catch (Exception exception) {
			fail("ContactService_030 - Failed - Exception occurs: "	+ exception.toString());
		}
	}
	
	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		androidClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
