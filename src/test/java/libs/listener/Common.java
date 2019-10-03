package libs.listener;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

public class Common {

	static WebDriver driver;
	public static String testingDate;
	final static Logger logger = LogManager.getLogger("Listener Common Keywords");

	/**
	 * To get java class name only.
	 * 
	 * @author Quan Nguyen
	 * @param description
	 *            of running method
	 * @return class name
	 */
	public static String getClassName(Description description) {
		// logger.info("getClassName starting...\n");
		String className = null;
		try {
			String fullClassName = description.getClassName();
			String[] listName;
			listName = fullClassName.split("\\.");
			className = listName[listName.length - 1];
		} catch (Exception ex) {
			logger.error("Unable to getClassName ");
			ex.printStackTrace();
		}
		// logger.info("className is: " + className + "...\n");
		// logger.info("getClassName completed...\n");
		return className;
	}

	/**
	 * To get Suite name only.
	 * 
	 * @author Quan Nguyen
	 * @param description
	 *            of running method
	 * @return suite name
	 */
	public static String getSuiteName(Description description) {
		// logger.info("getSuiteName starting...\n");
		String suiteName = null;
		try {
			String fullClassName = description.getClassName();
			String[] listName;
			listName = fullClassName.split("\\.");
			suiteName = listName[listName.length - 2];
		} catch (Exception ex) {
			logger.error("Unable to getSuiteName ");
			ex.printStackTrace();
		}
		// logger.info("getSuiteName is: " + suiteName + "...\n");
		// logger.info("getSuiteName completed...\n");
		return suiteName;
	}

	public static String getDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String dateTime = dateFormat.format(date);
		testingDate = dateTime;
		return dateTime;
	}

	public static String getTestingDate() {
		return testingDate;
	}

	/**
	 * To create new file for failed case.
	 * 
	 * @author Quan Nguyen
	 * @param file
	 *            where the file to be created
	 * @return true when file already created
	 */
	public static boolean createFile(File file) {
		boolean fileCreated = false;
		if (file.exists()) {
			fileCreated = true;
		} else {
			File parentDirectory = new File(file.getParent());
			if (parentDirectory.exists() || parentDirectory.mkdirs()) {
				try {
					fileCreated = file.createNewFile();
				} catch (IOException errorCreatingScreenshot) {
					errorCreatingScreenshot.printStackTrace();
				}
			}
		}

		return fileCreated;
	}
}
