package suite;

import libs.common.DriverManagement;
import scripts.ContactServices.*;
import testData.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;



import io.appium.java_client.android.AndroidDriver;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CS_010.class,CS_011.class,CS_017.class,CS_029.class,CS_030.class,CS_031.class,CS_035.class,CS_044.class,CS_053.class,CS_037.class,CS_046.class,CS_055.class
	,CS_016.class,CS_033.class,CS_034.class,CS_035.class,CS_036.class,CS_038.class,CS_039.class,CS_040.class,CS_041.class,CS_043.class,CS_044.class,CS_047.class
	,CS_049.class,CS_050.class,CS_052.class,CS_056.class,CS_058.class,CS_059.class,CS_060.class,CS_061.class			
})

/**
 This is the test suite for All written scripts

 */
public class AADS_Execution {
	final static Logger logger = LogManager.getLogger("DriverManagement Keywords");
	public static AndroidDriver androidClientDriver;

	
	@BeforeClass
	public static void setUpSuite() throws Exception {
		logger.info("setUpSuite - startings");
		DriverManagement driverKws = new DriverManagement();

		logger.info("setUpSuite - completed");
		logger.info("********************************************************");
	}

	@AfterClass
	public static void tearDownSuite() {
		logger.info("********************************************************");
		logger.info("tearDownSuite - clearing cookies");
		//winClient2Driver.quit();
		//winClientDriver.quit();
		//androidClientDriver.quit();
		//iOSClientDriver.quit();
		logger.info("tearDownSuite - completed");
	}

}
