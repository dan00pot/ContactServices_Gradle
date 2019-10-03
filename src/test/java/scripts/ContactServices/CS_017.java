package scripts.ContactServices;


import libs.clients.AndroidClientKeywords;
import libs.clients.IOSClientKeywords;
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
import io.appium.java_client.ios.IOSDriver;

import static org.junit.Assert.*;
import testData.aadsData;

public class CS_017 {
	
/*	########################################################################################
 * "Objective:
	Verify that Communicator Contacts that are manually added from AC-Clients are still displayed in contacts tab of clients.
	 
	Load Lineup:
	1. AC-Android 3.0 build 286
	2. AC-iOS 3.0 build 231
	3. AC-Mac 3.0 build 83
	4. AC-Windows 3.0 build 113
	5. AADS build 7.0.1.0.3064
	 
	User Details:
	1. User A1 logged-into AC-Android
	2. User A2 logged-into AC-iOS
	3. User A3 logged-into AC-Mac
	4. User A4 logged-into AC-Windows
	 
	Steps to Reproduce:
	================
	1. Login users A1 and A2 into device services from respective AC-Clients
	2. From A1 and A2, manually add 1 communicator contact each
	3. Wait and verify contact added from A1 and A2 are reflected at each other’s end
	4. Wait for 10-15 mins and check if newly added contacts are still displayed in contacts tab
	 
	Expected Results
	==============
	Step 4, Communicator Contacts that are manually added from AC-Clients are still displayed in Contact tab and they should not get deleted/removed automatically from AADS"
#############################################################################################################*/

	static AndroidDriver<?> androidClientDriver;
	static IOSDriver iOSClientDriver;
	
	IOSClientKeywords iosClient = new IOSClientKeywords();
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	
	
	final static Logger logger = LogManager.getLogger("CS_017");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	
	@Before
	public void beforeTest_ContactService_017() throws Exception {
		logger.info("beforeTest ContactService_017 starting...\n");
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		iOSClientDriver = driverMgnt.createIOSClientDriver();
		logger.info("beforeTest ContactService_017 completed...\n");
	}
	
	@Test
	public void testContactService_017() throws Exception {
		logger.info("test ContactService_017 - Starting\n");
		try {
			for (int i=1;i<=numberOfContact;i++) {
			String firstName= "Private";	
			String lastNameAndroid= "Android" +i ;
			String lastNameIOS= "iOS" +i ;
			
			String contact1 = firstName + " " + lastNameAndroid;
			String contact2 = firstName + " " + lastNameIOS;
			
			androidClient.addPrivateContact(androidClientDriver, firstName, lastNameAndroid, "1234"+i, "android"+i+"@aam1.com");
			iosClient.addPrivateContact(iOSClientDriver, firstName, lastNameIOS, "4321"+i, "iOS"+i+"@aam1.com");
			
			boolean n1= iosClient.verifyContactIsExistedContactList(iOSClientDriver, contact1);
			boolean n2= androidClient.verifyContactIsExistedInContactList(androidClientDriver, contact2); 
			
			assertTrue(n1 & n2);
		
			}
		} catch (Exception exception) {
			fail("testContactService_017 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown(){
		logger.info("tearDown starting...\n");
		try {
			for (int i=1;i<=numberOfContact;i++) {
				String firstName= "Private";	
				String lastNameAndroid= "Android" +i ;
				String lastNameIOS= "iOS" +i ;
				
				String contact1 = firstName + " " + lastNameAndroid;
				String contact2 = firstName + " " + lastNameIOS;
				
			androidClient.removeContactInContactList(androidClientDriver, contact1);
			iosClient.removeContact(iOSClientDriver, contact2);
			}
		}catch (Exception excep){
			fail("testContactService_017-removeContact - Failed - Exception occurs: "	+ excep.toString());
		}
		androidClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
