package libs.clients;


import io.appium.java_client.AppiumDriver;
import libs.common.DriverManagement;
import libs.common.Selenium;
import testData.aadsData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;



public class SMGRKeywords {
	WebDriver webDriver;
	Selenium selenium = new Selenium();
	
	SMGRLocators SMGRWeb = new SMGRLocators();
	aadsData aadsData = new aadsData();
	int timeout = 300;
	final static Logger logger = LogManager.getLogger("SMGRWebKeywords");
	DriverManagement driverMgnt = new DriverManagement();
	
	
	
	/**
	 * Login AADS Web main page
	 * @param webDriver testing on Web SMGR
	 * @param user 
	 * @param passord
	 * @author ndkhuong
	 * @throws Exception 
	 * 
	 */	
	public void loginSMGRMainPage(WebDriver webDriver, String user, String password) throws Exception{
		logger.info("LoginSMGRMainPage - starting...\n");
		try {
			selenium.inputText(webDriver, SMGRWeb.LOGIN_PAGE_USERNAME_TXT, user);
			selenium.inputText(webDriver, SMGRWeb.LOGIN_PAGE_PASSWORD_TXT, password);
			selenium.clickElement(webDriver, SMGRWeb.LOGIN_PAGE_SUBMIT_BTN);
			
		} catch (Exception exception) {
			logger.error("LoginSMGRMainPage - Failed with Exception: " + exception + "...\n");
			throw new Exception("LoginSMGRMainPage - Failed - Exception occurs: " + exception);
		}
		logger.info("LoginSMGRMainPage - completed...\n");			
    }
	
	public void logofSMGRMainPage(WebDriver webDriver) throws Exception{
		logger.info("LogofSMGRMainPage - starting...\n");
		try {
			selenium.switchToDefaultContent(webDriver);
			Thread.sleep(3000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.LOGOFF_PAGE, 2000);
			selenium.clickElement(webDriver, SMGRWeb.LOGOFF_PAGE);
			Thread.sleep(2000);	
		} catch (Exception exception) { 
			logger.error("LogofSMGRMainPage - Failed with Exception: " + exception + "...\n");
			throw new Exception("LogofSMGRMainPage - Failed - Exception occurs: " + exception);
		}
		logger.info("LogofSMGRMainPage - completed...\n");			
    }
	
	/**
	 * Navigate to User Management 
	 * @param webDriver testing on Web SMGR
	 * @author ndkhuong
	 * @throws Exception 
	 * 
	 */	
	public void navigatetoUserManagement(WebDriver webDriver) throws Exception{
		logger.info("NavigatetosUserManagement - starting...\n");
		try {
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.MAIN_PAGE_USER_MANAGEMENT, 200);
			selenium.clickElement(webDriver, SMGRWeb.MAIN_PAGE_USER_MANAGEMENT);
			selenium.switchToFrame(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_IFRAME);
			Thread.sleep(4000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_MANAGE_USER, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_MANAGE_USER);
		} catch (Exception exception) { 
			logger.error("NavigatetosUserManagement - Failed with Exception: " + exception + "...\n");
			throw new Exception("NavigatetosUserManagement - Failed - Exception occurs: " + exception);
		}
		logger.info("NavigatetosUserManagement - completed...\n");			
    }	
	
	/**
	 * Search user by sip phone
	 * @param webDriver testing on Web SMGR
	 * @param sip phone of user
	 * @author ndkhuong
	 * @throws Exception 
	 * 
	 */	
	public void manageUserSearchUserBySipPhone(WebDriver webDriver, String sip) throws Exception{
		logger.info("ManageUserSearchUserBySipPhone - starting...\n");
		try {
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_FILTER_ENABLE_BTN, 1000);
			selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_FILTER_ENABLE_BTN);
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.userSearchFilteringTxt("SIP Handle"), 1000);
			selenium.inputText(webDriver, SMGRWeb.userSearchFilteringTxt("SIP Handle"), sip);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_APPLY_FILTER_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_APPLY_FILTER_BTN);
		} catch (Exception exception) { 
			logger.error("ManageUserSearchUserBySipPhone - Failed with Exception: " + exception + "...\n");
			throw new Exception("ManageUserSearchUserBySipPhone - Failed - Exception occurs: " + exception);
		}
		logger.info("ManageUserSearchUserBySipPhone - completed...\n");			
    }
	
	/**
	 * Action with user ( view, edit, remove)
	 * @param webDriver testing on Web SMGR
	 * @param action
	 * @author ndkhuong
	 * @throws Exception 
	 * 
	 */	
	public void manageUserPageActionUser(WebDriver webDriver, String action) throws Exception{
		logger.info("manageUserPageActionUser - starting...\n");
		try {
			Thread.sleep(2000);
			//selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_SELECT_USER_BTN, 200);
			if (selenium.isElementSelected(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_SELECT_USER_BTN)==false)
			{
				selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_SELECT_USER_BTN);
			}
			Thread.sleep(3200);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.userManagePageActionbuttonLocators(action), 2000);
			selenium.clickElement(webDriver, SMGRWeb.userManagePageActionbuttonLocators(action));
		} catch (Exception exception) {
			logger.error("manageUserPageActionUser - Failed with Exception: " + exception + "...\n");
			throw new Exception("manageUserPageActionUser - Failed - Exception occurs: " + exception);
		}
		logger.info("manageUserPageActionUser - completed...\n");			
    }
	
	/**
	 * Get sip phone information
	 * @param webDriver testing on Web SMGR
	 * @author ndkhuong
	 * @throws Exception 
	 * 
	 */	
	public String[] manageUserGetSipPhoneInformation(WebDriver webDriver) throws Exception{
		logger.info("manageUserGetSipPhoneInformation - starting...\n");
		String s[] = new String[11];
		try {
			Thread.sleep(3000);
			s[0]= selenium.getAttribute(webDriver, SMGRWeb.VIEW_USER_PAGE_SM_PRIMARY_TXT, "value");
			s[1]= selenium.getAttribute(webDriver, SMGRWeb.VIEW_USER_PAGE_SM_PRIMARY_TXT, "value");
			s[2]= selenium.getAttribute(webDriver, SMGRWeb.VIEW_USER_PAGE_SIP_PHONE_NUMBER_TXT, "value");
		} catch (Exception exception) { 
			logger.error("manageUserGetSipPhoneInformation - Failed with Exception: " + exception + "...\n");
			throw new Exception("manageUserGetSipPhoneInformation - Failed - Exception occurs: " + exception);
		}
		logger.info("manageUserGetSipPhoneInformation - completed...\n");
		return s;			
    }
	
	/**
	 * Edit Presence Profile
	 * @param webDriver testing on Web SMGR
	 * @param checkbox (true/false)
	 * @param IMgateway
	 * @author ndkhuong
	 * @throws Exception 
	 * 
	 */	
	public String[] manageUserEditPresenceProfile(WebDriver webDriver, boolean checkbox, String IMgateway) throws Exception{
		logger.info("manageUserEditPresenceProfile - starting...\n");
		String s[] = new String[11];
		try {
			Thread.sleep(7000);
			selenium.scrollToElement(webDriver, SMGRWeb.EDIT_USER_PRESENCE_PROFILE_CHECKBOX);
			if(checkbox==true)
			{
				if (selenium.isElementSelected(webDriver, SMGRWeb.EDIT_USER_PRESENCE_PROFILE_CHECKBOX) == false)
				{
					selenium.clickElement(webDriver, SMGRWeb.EDIT_USER_PRESENCE_PROFILE_CHECKBOX);
				
				}
				Thread.sleep(2000);
				selenium.dropDownListBoxByValue(webDriver, SMGRWeb.EDIT_USER_PRESENCE_SYSTEM_DROPLIST, "0");
				Thread.sleep(2000);
				selenium.dropDownListBox(webDriver, SMGRWeb.EDIT_USER_IM_GATEWAY_DROPLIST, IMgateway);
				Thread.sleep(2000);
			}
			else
			{
				if (selenium.isElementSelected(webDriver, SMGRWeb.EDIT_USER_PRESENCE_PROFILE_CHECKBOX) == true)
				{
					selenium.clickElement(webDriver, SMGRWeb.EDIT_USER_PRESENCE_PROFILE_CHECKBOX);
				}	
			}
			Thread.sleep(2000);
			selenium.scrollToElement(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN);
			selenium.clickElement(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN);
			Thread.sleep(7000);
			selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_STATUS_BTN);
			Thread.sleep(5000);
			selenium.elementShouldExisted(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_STATUS_SUCESSFULLY_TXT);
		} catch (Exception exception) { 
			logger.error("manageUserEditPresenceProfile - Failed with Exception: " + exception + "...\n");
			throw new Exception("manageUserEditPresenceProfile - Failed - Exception occurs: " + exception);
		}
		logger.info("manageUserEditPresenceProfile - completed...\n");
		return s;			
    }
	
	/**
	 * Search contact for removing
	 * @param webDriver testing on Web SMGR
	 * @param user
	 * @author ndkhuong
	 * @throws Exception 
	 * 
	 */	
	public void searchUserByFirstName(WebDriver webDriver, String user) throws Exception{
		//search user by First name for synchronized database with smgr 8
		logger.info("searchUserByFirstName - starting...\n");
		try {
			Thread.sleep(15000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_FILTER_ENABLE_BTN, 8000);
			selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_FILTER_ENABLE_BTN);
			Thread.sleep(10000);
//			selenium.waitUntilElementClickable(webDriver, SMGRWeb.userSearchFilteringTxt("Last Name"), 8000);
//			selenium.inputText(webDriver, SMGRWeb.userSearchFilteringTxt("Last Name"), user);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.userSearchFilteringTxt("First Name"), 8000);
			selenium.inputText(webDriver, SMGRWeb.userSearchFilteringTxt("First Name"), user);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_APPLY_FILTER_BTN, 8000);
			selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_APPLY_FILTER_BTN);
		} catch (Exception exception) { 
			logger.error("searchUserByFirstName - Failed with Exception: " + exception + "...\n");
			throw new Exception("searchUserByFirstName - Failed - Exception occurs: " + exception);
		}
		logger.info("searchUserByFirstName - completed...\n");			
    }
	
	/**
	 * Search contact for adding
	 * @param webDriver testing on Web SMGR
	 * @param user
	 * @author ndkhuong
	 * @throws Exception 
	 * 
	 */	
	public void addContactSearchUserByLastName(WebDriver webDriver, String user) throws Exception{
		logger.info("addContactSearchUserByLastName - starting...\n");
		try {
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_FILTER_ENABLE_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_FILTER_ENABLE_BTN);
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.userSearchFilteringTxt("Last Name"), 1000);
			selenium.inputText(webDriver, SMGRWeb.userSearchFilteringTxt("Last Name"), user);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_APPLY_FILTER_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_APPLY_FILTER_BTN);
		} catch (Exception exception) { 
			logger.error("ManageUserSearchUserBySipPhone - Failed with Exception: " + exception + "...\n");
			throw new Exception("ManageUserSearchUserBySipPhone - Failed - Exception occurs: " + exception);
		}
		logger.info("ManageUserSearchUserBySipPhone - completed...\n");			
    }
	
	public void addContactSearchUserByFirstName(WebDriver webDriver, String user) throws Exception{
		logger.info("addContactSearchUserByFirstName - starting...\n");
		try {
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_FILTER_ENABLE_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_FILTER_ENABLE_BTN);
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.userSearchFilteringTxt("First Name"), 1000);
			selenium.inputText(webDriver, SMGRWeb.userSearchFilteringTxt("First Name"), user);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_APPLY_FILTER_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_APPLY_FILTER_BTN);
		} catch (Exception exception) { 
			logger.error("addContactSearchUserByFirstName - Failed with Exception: " + exception + "...\n");
			throw new Exception("addContactSearchUserByFirstName - Failed - Exception occurs: " + exception);
		}
		logger.info("addContactSearchUserByFirstName - completed...\n");			
    }
	
	public void editUserAddNewPrivateContact(WebDriver webDriver, String last, String first) throws Exception{
		logger.info("editUserAddNewContact - starting...\n");
		try {
			Thread.sleep(8000);
			
			selenium.waitUntilElementVisible(webDriver, SMGRWeb.TAB_CONTACT_USER_PROFILE);
			selenium.clickElement(webDriver, SMGRWeb.TAB_CONTACT_USER_PROFILE);
			Thread.sleep(6000);
			selenium.clickElement(webDriver, SMGRWeb.SHOW_PRIVATE_CONTACT);
			selenium.clickElement(webDriver, SMGRWeb.NEW_PRIVATE_CONTACT);
			Thread.sleep(2000);
			selenium.inputText(webDriver, SMGRWeb.INPUT_LAST_NAME_PRIVATE_CONTACT, last);
			selenium.inputText(webDriver, SMGRWeb.INPUT_FIRST_NAME_PRIVATE_CONTACT, first);
			selenium.clickElement(webDriver, SMGRWeb.ADD_PRIVATE_CONTACT);
			Thread.sleep(7000);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_BTN);
			Thread.sleep(5000);
			addContactSearchUserByFirstName(webDriver, first);
			Thread.sleep(5000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_USER_CONTACT_USER_CHECKBOX, 2000);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_USER_CONTACT_USER_CHECKBOX);
			Thread.sleep(5000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_SELECT_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_SELECT_BTN);
			Thread.sleep(15000);
			selenium.scrollToElement(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN);
			Thread.sleep(10000);
			selenium.elementShouldExisted(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_STATUS_SUCESSFULLY_TXT);
		} catch (Exception exception) {
			logger.error("editUserAddNewContact - Failed with Exception: " + exception + "...\n");
			throw new Exception("editUserAddNewContact - Failed - Exception occurs: " + exception);
		}
		logger.info("editUserAddNewContact - completed...\n");			
    }
	


	public void editUserAddNewContact(WebDriver webDriver, String user) throws Exception{
		logger.info("editUserAddNewContact - starting...\n");
		try {
			Thread.sleep(8000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB);
			Thread.sleep(8000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_BTN);
			Thread.sleep(5000);
//			addContactSearchUserByLastName(webDriver, user);
			addContactSearchUserByFirstName(webDriver, user);
			Thread.sleep(5000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_USER_CONTACT_USER_CHECKBOX, 2000);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_USER_CONTACT_USER_CHECKBOX);
			Thread.sleep(5000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_SELECT_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_SELECT_BTN);
			Thread.sleep(15000);
			selenium.scrollToElement(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN);
			Thread.sleep(10000);
			selenium.elementShouldExisted(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_STATUS_SUCESSFULLY_TXT);
			
		} catch (Exception exception) {
			logger.error("editUserAddNewContact - Failed with Exception: " + exception + "...\n");
			throw new Exception("editUserAddNewContact - Failed - Exception occurs: " + exception);
		}
		logger.info("editUserAddNewContact - completed...\n");			
    }
	
	/**
	 * Editer on Remove contact
	 * @param webDriver testing on Web SMGR
	 * @param user
	 * @author ndkhuong
	 * @throws Exception 
	 * 
	 */	
	
	public void editUserRemoveContact(WebDriver webDriver, String user) throws Exception{
		logger.info("editUserRemoveContact - starting...\n");
		try {
			Thread.sleep(8000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB_ADD_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB_ADD_BTN);
			Thread.sleep(5000);
			searchUserByFirstName(webDriver, user);
			Thread.sleep(8000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.userInformationContactsTabRemoveContactCheckboxLocator(user), 200);
			selenium.clickElement(webDriver, SMGRWeb.userInformationContactsTabRemoveContactCheckboxLocator(user));
			Thread.sleep(8000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_REMOVE_BTN, 2000);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_REMOVE_BTN);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_REMOVE_BTN);
			Thread.sleep(10000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN);
			Thread.sleep(10000);
			selenium.elementShouldExisted(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_STATUS_SUCESSFULLY_TXT);
			
		} catch (Exception exception) { 
			logger.error("editUserRemoveContact - Failed with Exception: " + exception + "...\n");
			throw new Exception("editUserRemoveContact - Failed - Exception occurs: " + exception);
		}
		logger.info("editUserRemoveContact - completed...\n");			
    }
	
	/**
	 * Verify Contact is existed in contact list
	 * @param webDriver testing on Web SMGR
	 * @param user
	 * @return true/false
	 * @author ndkhuong
	 * @throws Exception 
	 * 
	 */	
	public boolean verifyNameContactisExsitedInContactList(WebDriver webDriver, String user) throws Exception{
		logger.info("verifyNameContactisExsitedInContactList - starting...\n");
		boolean s= false;
		try {
			Thread.sleep(10000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB, 8000);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB);
			Thread.sleep(5000);
			searchUserByFirstName(webDriver, user);
			Thread.sleep(5000);
			if (selenium.isElementExisted(webDriver, SMGRWeb.userInformationContactsTabRemoveContactCheckboxLocator(user)))
			{
				s = true;
			}
			else s= false;
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN, 8000);
			selenium.clickElement(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN);
			Thread.sleep(13000);
			//selenium.elementShouldExisted(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_STATUS_SUCESSFULLY_TXT);
			
		} catch (Exception exception) {
			logger.error("verifyNameContactisExsitedInContactList - Failed with Exception: " + exception + "...\n");
			throw new Exception("verifyNameContactisExsitedInContactList - Failed - Exception occurs: " + exception);
		}
		logger.info("verifyNameContactisExsitedInContactList - completed...\n");	
		return s;
    }
	
	/**
	 * @author HuyD
	 * To verify contact isn't existed
	 * 
	 * 
	 * */
	
	public boolean verifyNameContactisNotExsitedInContactList(WebDriver webDriver, String user) throws Exception{
		logger.info("verifyNameContactisNotExsitedInContactList - starting...\n");
		boolean s= false;
		try {
			Thread.sleep(5000);
			//selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB);
			Thread.sleep(3000);
			searchUserByFirstName(webDriver, user);
			Thread.sleep(3000);
			if (!selenium.isElementExisted(webDriver, SMGRWeb.userInformationContactsTabRemoveContactCheckboxLocator(user)))
			{
				s = true;
			}
			else s= false;
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.EDIT_USER_PAGE_COMMIT_BTN);
			Thread.sleep(3000);
			selenium.elementShouldExisted(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_STATUS_SUCESSFULLY_TXT);
			
		} catch (Exception exception) {
			logger.error("verifyNameContactisNotExsitedInContactList - Failed with Exception: " + exception + "...\n");
			throw new Exception("verifyNameContactisNotExsitedInContactList - Failed - Exception occurs: " + exception);
		}
		logger.info("verifyNameContactisNotExsitedInContactList - completed...\n");	
		return s;
    }
	
	public void navigatetoContactTabAndSearchContacts(WebDriver webDriver, String searchString) throws Exception{
		logger.info("navigatetoContactTabAndSearchContacts - starting...\n");
		try {
			Thread.sleep(10000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB, 8000);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB);
			Thread.sleep(5000);
			searchUserByFirstName(webDriver, searchString);
			
		} catch (Exception exception) { 
			logger.error("navigatetoContactTabAndSearchContacts - Failed with Exception: " + exception + "...\n");
			throw new Exception("navigatetoContactTabAndSearchContacts - Failed - Exception occurs: " + exception);
		}
		logger.info("navigatetoContactTabAndSearchContacts - completed...\n");	
		
	}
	
	public boolean verifyIfContactisExsitedInContactList(WebDriver webDriver, String user) throws Exception{
		logger.info("verifyIfContactisExsitedInContactList - starting...\n");
		boolean s= false;
		try {
			if (selenium.isElementExisted(webDriver, SMGRWeb.userInformationContactsTabRemoveContactCheckboxLocator(user)))
			{
				s = true;
			}
			else s= false;
			
		} catch (Exception exception) {
			logger.error("verifyIfContactisExsitedInContactList - Failed with Exception: " + exception + "...\n");
			throw new Exception("verifyIfContactisExsitedInContactList - Failed - Exception occurs: " + exception);
		}
		logger.info("verifyIfContactisExsitedInContactList - completed...\n");	
		return s;
    }
	
	/**
	 * @author TrucNguyen
	 * Hard detele SMGR user
	 * 
	 * 
	 * */
	
	public void hardDeleteSMGRUser (WebDriver webDriver, String user) throws Exception
	{
		logger.info("hardDeleteSMGRUser - starting...\n");
		try {
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_DELETE_CONFIRMATION, 2000);
			selenium.clickElement(webDriver, SMGRWeb.USER_DELETE_CONFIRMATION);
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.MORE_ACTION, 2000);
			selenium.clickElement(webDriver, SMGRWeb.MORE_ACTION);
			Thread.sleep(10000);
			WebElement input = webDriver.findElement(SMGRWeb.SHOW_DELETED_USERS);
			new Actions(webDriver).moveToElement(input).click().perform();
			Thread.sleep(2000);
			searchUserByFirstName(webDriver, user);
			manageUserPageActionUser(webDriver,"Delete");
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_DELETE_CONFIRMATION, 2000);
			selenium.clickElement(webDriver, SMGRWeb.USER_DELETE_CONFIRMATION);
			
		} catch (Exception e) {
			logger.error("verifyIfContactisExsitedInContactList - Failed with Exception: " + e + "...\n");
			throw new Exception("verifyIfContactisExsitedInContactList - Failed - Exception occurs: " + e);
		}
		logger.info("verifyIfContactisExsitedInContactList - completed...\n");	
	}
	
/*#####################################***********SMGR 8*****************###################################*/
	
	public void smgr8_loginSMGRMainPage(WebDriver webDriver, String user, String password) throws Exception{
		logger.info("LoginSMGRMainPage - starting...\n");
		try {
			Thread.sleep(5000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_LOGIN_PAGE_USERNAME_TXT, 2000);
			selenium.inputText(webDriver, SMGRWeb.SMGR8_LOGIN_PAGE_USERNAME_TXT, user);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_LOGIN_PAGE_PASSWORD_TXT, 200);
			selenium.inputText(webDriver, SMGRWeb.SMGR8_LOGIN_PAGE_PASSWORD_TXT, password);
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_LOGIN_PAGE_SUBMIT_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_LOGIN_PAGE_SUBMIT_BTN);
			Thread.sleep(2000);	
		} catch (Exception exception) { 
			logger.error("LoginSMGRMainPage - Failed with Exception: " + exception + "...\n");
			throw new Exception("LoginSMGRMainPage - Failed - Exception occurs: " + exception);
		}
		logger.info("LoginSMGRMainPage - completed...\n");			
    }
	
	public void smgr8_navigatetoUserManagement(WebDriver webDriver) throws Exception{
		logger.info("NavigatetoUSerManagement - starting...\n");
		try {
//			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_MAINPAGE_USERS_DROPDOWN_BTN);
//			selenium.clickElement(webDriver, SMGRWeb.SMGR8_MAINPAGE_USERS_DROPDOWN_BTN);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_MAINPAGE_USERS_DROPDOWN_BTN);
			selenium.mouseHover(webDriver, SMGRWeb.SMGR8_MAINPAGE_USERS_DROPDOWN_BTN);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_MAINPAGE_USERS_MENU_USER_MANAGEMENT_BTN);
			selenium.mouseHover(webDriver, SMGRWeb.SMGR8_MAINPAGE_USERS_MENU_USER_MANAGEMENT_BTN);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_MAINPAGE_USERS_MENU_SUBMENU_MANAGE_USER_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_MAINPAGE_USERS_MENU_SUBMENU_MANAGE_USER_BTN);
			selenium.switchToFrame(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_IFRAME);
		} catch (Exception exception) { 
			logger.error("NavigatetoUSerManagement - Failed with Exception: " + exception + "...\n");
			throw new Exception("NavigatetoUSerManagement - Failed - Exception occurs: " + exception);
		}
		logger.info("NavigatetoUSerManagement - completed...\n");			
    }
	
	public void smgr8_manageUserPageActionUser(WebDriver webDriver, String action) throws Exception{
		logger.info("manageUserPageActionUser - starting...\n");
		try {
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_SELECT_USER_BTN, 200);
			if (selenium.isElementSelected(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_SELECT_USER_BTN)==false)
			{
				selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_SELECT_USER_BTN);
			}
			Thread.sleep(3200);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.smgr8_userManagePageActionbuttonLocators(action), 2000);
			selenium.clickElement(webDriver, SMGRWeb.smgr8_userManagePageActionbuttonLocators(action));
		} catch (Exception exception) { 
			logger.error("manageUserPageActionUser - Failed with Exception: " + exception + "...\n");
			throw new Exception("manageUserPageActionUser - Failed - Exception occurs: " + exception);
		}
		logger.info("manageUserPageActionUser - completed...\n");			
    }
	
	public void smgr8_manageUserSearchUserBySipPhone(WebDriver webDriver, String sip) throws Exception{
		logger.info("ManageUserSearchUserBySipPhone - starting...\n");
		try {
			//selenium.switchToFrame(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_IFRAME);
			Thread.sleep(5000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_SIP_HANDLE_FILTER_BTN, 1000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_SIP_HANDLE_FILTER_BTN);
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_SIP_HANDLE_FILTER_INPUT_TXT, 1000);
			selenium.inputText(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_SIP_HANDLE_FILTER_INPUT_TXT, sip);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_REFRESH_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_REFRESH_BTN);
		} catch (Exception exception) { 
			logger.error("ManageUserSearchUserBySipPhone - Failed with Exception: " + exception + "...\n");
			throw new Exception("ManageUserSearchUserBySipPhone - Failed - Exception occurs: " + exception);
		}
		logger.info("ManageUserSearchUserBySipPhone - completed...\n");			
    }
	
	public void smgr8_navigatetoContactTabAndSearchContacts(WebDriver webDriver, String searchString) throws Exception{
		logger.info("navigatetoContactTabAndSearchContacts - starting...\n");
		boolean s= false;
		try {
			Thread.sleep(10000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB, 8000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB);
			Thread.sleep(5000);
			smgr8_searchUserByFirstName(webDriver, searchString);
		} catch (Exception exception) {
			logger.error("navigatetoContactTabAndSearchContacts - Failed with Exception: " + exception + "...\n");
			throw new Exception("navigatetoContactTabAndSearchContacts - Failed - Exception occurs: " + exception);
		}
		logger.info("navigatetoContactTabAndSearchContacts - completed...\n");	
	}
	
	public void smgr8_addContactSearchUserByFirstName(WebDriver webDriver, String user) throws Exception{
		logger.info("smgr8_addContactSearchUserByFirstName - starting...\n");
		try {
			Thread.sleep(3000);
			selenium.waitUntilElementVisible(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_BTN);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_BTN);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_BTN);
			
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_INFORMATION_ADD_POPUP_FIRSTNAME_FILTER);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_INPUT_TXT, 8000);
			selenium.inputText(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_INPUT_TXT, user);
			Thread.sleep(3000);
			selenium.enter(webDriver);
			selenium.enter(webDriver);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_REFRESH_BTN);
		} catch (Exception exception) { 
			logger.error("ManageUserSearchUserBySipPhone - Failed with Exception: " + exception + "...\n");
			throw new Exception("ManageUserSearchUserBySipPhone - Failed - Exception occurs: " + exception);
		}
		logger.info("ManageUserSearchUserBySipPhone - completed...\n");			
    }
	public void smgr8_addContactSearchUserByDisplayName(WebDriver webDriver, String display) throws Exception{
		logger.info("smgr8_addContactSearchUserByFirstName - starting...\n");
		try {
			Thread.sleep(3000);
			selenium.waitUntilElementVisible(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_DISPLAY_NAME_FILTER_BTN);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_DISPLAY_NAME_FILTER_BTN);
			selenium.inputText(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_DISPLAY_NAME_FILTER_INPUT_TXT, display);
			selenium.enter(webDriver);
			selenium.enter(webDriver);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_REFRESH_BTN);
			Thread.sleep(3000);
		} catch (Exception exception) { 
			logger.error("ManageUserSearchUserBySipPhone - Failed with Exception: " + exception + "...\n");
			throw new Exception("ManageUserSearchUserBySipPhone - Failed - Exception occurs: " + exception);
		}
		logger.info("ManageUserSearchUserBySipPhone - completed...\n");			
    }
	public void smgr8_editUserAddNewContact(WebDriver webDriver, String user) throws Exception{
		logger.info("editUserAddNewContact - starting...\n");
		try {
			Thread.sleep(8000);
			selenium.waitUntilElementVisible(webDriver, SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB);
			WebElement tab = webDriver.findElement(SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB); 
			new Actions(webDriver).moveToElement(tab).click().click().perform();
			
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB_ADD_BTN);
			smgr8_addContactSearchUserByFirstName(webDriver, user);
			
			Thread.sleep(2000);
			tab = webDriver.findElement(SMGRWeb.SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_SELECT_USER_BTN); 
			new Actions(webDriver).moveToElement(tab).click().perform();
			
	//		selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_SELECT_USER_BTN);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_OK_BTN);
		//	selenium.scrollToElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_COMMIT_BTN);
			Thread.sleep(1000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_COMMIT_BTN);
		
		//	selenium.elementShouldExisted(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_STATUS_SUCESSFULLY_TXT);
			
		} catch (Exception exception) {
			logger.error("editUserAddNewContact - Failed with Exception: " + exception + "...\n");
			throw new Exception("editUserAddNewContact - Failed - Exception occurs: " + exception);
		}
		logger.info("editUserAddNewContact - completed...\n");			
    }
	
	public void smgr8_editUserAddNewPrivateContact(WebDriver webDriver, String firstname, String lastname) throws Exception{
		logger.info("editUserAddNewContact - starting...\n");
		try {
			Thread.sleep(8000);
			selenium.waitUntilElementVisible(webDriver, SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB);
			WebElement tab = webDriver.findElement(SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB); 
			new Actions(webDriver).moveToElement(tab).click().click().perform();
			
			selenium.clickElement(webDriver, SMGRWeb.elementWithTextShow("Private Contacts"));
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB_ADD_PRIVATE_BTN);
			Thread.sleep(8000);
			selenium.inputText(webDriver,SMGRWeb.PRIVATE_LAST_NAME, aadsData.FIRSTNAME_PRIVATE_CONTACT);
			selenium.inputText(webDriver,  SMGRWeb.PRIVATE_FIRST_NAME, aadsData.LASTNAME_PRIVATE_CONTACT);
			Thread.sleep(2000);

			selenium.clickElement(webDriver,  SMGRWeb.COMPLEATE_ADD_PRIVATE_CONTACT);
			selenium.clickElement(webDriver, SMGRWeb.COMMIT_AND_CONTINUE);
			Thread.sleep(20000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_INFORMATION_CONTACTS_TAB_ADD_BTN);
			Thread.sleep(8000);
			smgr8_addContactSearchUserByDisplayName(webDriver, lastname);
			Thread.sleep(2000);
			tab = webDriver.findElement(SMGRWeb.SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_SELECT_USER_BTN); 
			new Actions(webDriver).moveToElement(tab).click().perform();
			
	//		selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_SELECT_USER_BTN);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_OK_BTN);
		//	selenium.scrollToElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_COMMIT_BTN);
			Thread.sleep(1000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_COMMIT_BTN);
		
		//	selenium.elementShouldExisted(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_STATUS_SUCESSFULLY_TXT);
			
		} catch (Exception exception) {
			logger.error("editUserAddNewContact - Failed with Exception: " + exception + "...\n");
			throw new Exception("editUserAddNewContact - Failed - Exception occurs: " + exception);
		}
		logger.info("editUserAddNewContact - completed...\n");			
    }
	
	public void smgr8_editUserRemoveContact(WebDriver webDriver, String user) throws Exception{
		logger.info("editUserRemoveContact - starting...\n");
		try {
			Thread.sleep(8000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB, 200);
			selenium.clickElement(webDriver, SMGRWeb.USER_INFORMATION_CONTACTS_TAB);
			Thread.sleep(8000);
			smgr8_searchUserByFirstName(webDriver, user);
			Thread.sleep(8000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.userInformationContactsTabRemoveContactCheckboxLocator(user), 200);
			selenium.clickElement(webDriver, SMGRWeb.userInformationContactsTabRemoveContactCheckboxLocator(user));
			Thread.sleep(8000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_REMOVE_BTN, 2000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_REMOVE_BTN);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_REMOVE_BTN);
			Thread.sleep(10000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_COMMIT_BTN, 200);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_COMMIT_BTN);
			Thread.sleep(10000);
			//selenium.elementShouldExisted(webDriver, SMGRWeb.USER_MANAGEMENT_PAGE_STATUS_SUCESSFULLY_TXT);
			
		} catch (Exception exception) { 
			logger.error("editUserRemoveContact - Failed with Exception: " + exception + "...\n");
			throw new Exception("editUserRemoveContact - Failed - Exception occurs: " + exception);
		}
		logger.info("editUserRemoveContact - completed...\n");			
    }
	
	public boolean smgr8_verifyIfContactisExsitedInContactList(WebDriver webDriver, String user) throws Exception{
		logger.info("verifyIfContactisExsitedInContactList - starting...\n");
		boolean s= false;
		try {
			if (selenium.isElementExisted(webDriver, SMGRWeb.smgr8_userInformationContactsTabRemoveContactCheckboxLocator(user)))
			{
				s = true;
			}
			else s= false;
			
		} catch (Exception exception) { 
			logger.error("verifyIfContactisExsitedInContactList - Failed with Exception: " + exception + "...\n");
			throw new Exception("verifyIfContactisExsitedInContactList - Failed - Exception occurs: " + exception);
		}
		logger.info("verifyIfContactisExsitedInContactList - completed...\n");	
		return s;
    }
	
	public void smgr8_searchUserByFirstName(WebDriver webDriver, String user) throws Exception{
		logger.info("smgr8_searchUserByFirstName - starting...\n");
		try {
			Thread.sleep(15000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_BTN, 8000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_BTN);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_INPUT_TXT, 8000);
			selenium.inputText(webDriver, SMGRWeb.SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_INPUT_TXT, user);
			Thread.sleep(3000);
			selenium.enter(webDriver);
			selenium.enter(webDriver);
		} catch (Exception exception) { 
			logger.error("smgr8_searchUserByFirstName - Failed with Exception: " + exception + "...\n");
			throw new Exception("smgr8_searchUserByFirstName - Failed - Exception occurs: " + exception);
		}
		logger.info("smgr8_searchUserByFirstName - completed...\n");			
    }
	
	public void smgr8_hardDeleteSMGRUser (WebDriver webDriver, String user) throws Exception
	{
		logger.info("hardDeleteSMGRUser - starting...\n");
		try {
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_DELETE_CONFIRMATION, 2000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_DELETE_CONFIRMATION);
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.smgr8_userManagePageActionbuttonLocators("More Actions"), 2000);
			selenium.clickElement(webDriver, SMGRWeb.smgr8_userManagePageActionbuttonLocators("More Actions"));
			Thread.sleep(10000);
			WebElement input = webDriver.findElement(SMGRWeb.SMGR8_SHOW_DELETED_USERS);
			new Actions(webDriver).moveToElement(input).click().perform();
			Thread.sleep(2000);
			smgr8_searchUserByFirstName(webDriver, user);
			smgr8_manageUserPageActionUser(webDriver,"Delete");
			Thread.sleep(2000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_USER_HARD_DELETE_CONFIRMATION, 2000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_USER_HARD_DELETE_CONFIRMATION);
			
		} catch (Exception e) {
			logger.error("hardDeleteSMGRUser - Failed with Exception: " + e + "...\n");
			throw new Exception("hardDeleteSMGRUser - Failed - Exception occurs: " + e);
		}
		logger.info("hardDeleteSMGRUser - completed...\n");	
	}
	
	public void smgr8_logofSMGRMainPage(WebDriver webDriver) throws Exception{
		logger.info("LogofSMGRMainPage - starting...\n");
		try {
			selenium.switchToDefaultContent(webDriver);
			Thread.sleep(3000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_LOGOFF_PAGE_COVER);
			Thread.sleep(1000);
			selenium.waitUntilElementClickable(webDriver, SMGRWeb.SMGR8_LOGOFF_PAGE_BTN, 2000);
			selenium.clickElement(webDriver, SMGRWeb.SMGR8_LOGOFF_PAGE_BTN);
			Thread.sleep(2000);	
		} catch (Exception exception) { 
			logger.error("LogofSMGRMainPage - Failed with Exception: " + exception + "...\n");
			throw new Exception("LogofSMGRMainPage - Failed - Exception occurs: " + exception);
		}
		logger.info("LogofSMGRMainPage - completed...\n");			
    }
	
}
