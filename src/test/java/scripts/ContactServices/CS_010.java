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
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import libs.listener.ExtentManager;
public class CS_010 {
	
/*########################################################################################################
 	CS-010 Manually add new contact having  the same email address with users on single LDAP of AADS and SMGR
	
	*Configurations:
		1. SMGR does not Sync with any LDAP
		2. AADS connects to only AD2008 LDAP
		3. Email khuong1@aam1.com already exists in single LDAP (Full name: Khuong Nguyen) and SMGR (Full name: Khuong Nguyen1)
		 
	*Procedures:
		1. Try to manually add new user record with name as "abc xyz" with email as khuong1@aam1.com
		 
	*Expected result:
		1. AADS will fetch name from LDAP (Khuong Nguyen) and would save that for new contact instead of name as "abc xyz"
##############################################################################################################*/
	
	static AndroidDriver<?> androidClientDriver;
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	final static Logger logger = LogManager.getLogger("CS_010");
	String configurationName = "configuration1";
	int numberOfContact = 10; 
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	static ExtentReports reports;
	ExtentTest Sresult;
	@Before
	public void beforeTest_ContactService_010() throws Exception {

		logger.info("beforeTest ContactService_010 starting...\n");
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		reports = ExtentManager.GetExtent();
		logger.info("beforeTest ContactService_010 completed...\n");
	}
	
	@Test
	public void testContactService_010() throws Exception {
		logger.info("ContactService_010 - Starting\n");
		Sresult = reports.createTest("[ACA] Contact Services CS_010", "verify add private contact");
		try {
			for (int i=1;i<=numberOfContact;i++) {
			String firstName= "Private";	
			String lastName= "Contact" +i ;
			int b = aadsData.AADS_ADD_PRIVATE_CONTACT_USER1 + i;
			String phoneNumber = String.valueOf(b);
			String emailAddress = ("local"+b+"@aam1.com");
			String mail= excel.User_Switch_MAIL(i, sheet);
			String DN = excel.User_Switch_DN(i, sheet);
			
			//add private contact with fistName lastName phoneNumber and mail(LDAP)
			androidClient.addPrivateContact(androidClientDriver, firstName, lastName, phoneNumber, mail);
			boolean s=androidClient.verifyContactIsExistedInContactList(androidClientDriver, DN);
			if(s=true) {
				System.out.println("Adding: " +firstName+ " " +lastName+ " " +phoneNumber+ " " +mail+ " <=>" +DN+ "- PASSED...");
				Sresult.log(Status.PASS, "Adding: " +firstName+ " " +lastName+ " " +phoneNumber+ " " +mail+ " <=>" +DN+ " - FAILED...");
			}
			else {
				System.out.println("Adding: " +firstName+ " " +lastName+ " " +phoneNumber+ " " +mail+ " <=>" +DN+ "- FAILED...");
				Sresult.log(Status.FAIL, "Adding: " +firstName+ " " +lastName+ " " +phoneNumber+ " " +mail+ " <=>" +DN+" - FAILED...");
				continue;
			}
			assertTrue(s);
			androidClient.removeContactInContactList(androidClientDriver, DN);
			}
		} catch (Exception exception) {
			fail("ContactService_010 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
//		reports.flush();
		androidClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
	
	
}
