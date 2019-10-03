package libs.clients;

import org.openqa.selenium.By;

public class SMGRLocators {
	
	
	

	
	//###########################################################################################################################
	//Duy
	//###########################################################################################################################
	
	public  By TAB_CONTACT_USER_PROFILE										=	 By.xpath("//a[@href='#tabouterDiv0_3']");
	public  By SHOW_PRIVATE_CONTACT											=	 By.xpath("//img[@alt='Show  Private Contacts Section']");
	public  By NEW_PRIVATE_CONTACT											=	 By.xpath("//table[@id='privateContactsTableControlbar']//button[contains(text(),'New')]");
	public  By INPUT_LAST_NAME_PRIVATE_CONTACT								=	 By.xpath("//label[@for='contactLastName'][contains(text(),'Last Name')]");
	public  By INPUT_FIRST_NAME_PRIVATE_CONTACT								=  	 By.xpath("//label[@for='contactFirstname'][contains(text(),'First Name')]");
	public  By ADD_PRIVATE_CONTACT											=	 By.xpath("//td[@class='xoi'][@valign='top']//button[contains(text(),'Add')]");
	
	public  By COMMIT_AND_CONTINUE 											=	 By.xpath("//button[@class='ant-btn user_editor_comm_cont']");
	public  By SMGR8_USER_MANAGEMENT_PAGE_DISPLAY_NAME_FILTER_INPUT_TXT 	=	 By.xpath("//input[@id='filter_textfield_displayName']");
	public	By SMGR8_USER_MANAGEMENT_PAGE_DISPLAY_NAME_FILTER_BTN			=	 By.xpath("//div[@id='filter_icon_displayName']");
	public  By elementWithTextShow(String string) 								 {return By.xpath("//*[contains(text(),'"+string+"')]");}
	public  By USER_INFORMATION_CONTACTS_TAB_ADD_PRIVATE_BTN 				= 	 By.xpath("//*[@class='ant-btn plus_PCL']");
	public 	By PRIVATE_LAST_NAME 											=	 By.xpath("//input[@placeholder='Last Name  of User']");
	public 	By PRIVATE_FIRST_NAME 											=	 By.xpath("//input[@placeholder='First Name']");
	public  By COMPLEATE_ADD_PRIVATE_CONTACT								= 	 By.xpath("//button[@style='margin-left: 80%; margin-bottom: 8px;']");
																
	//###########################################################################################################################
	//########################################LOCATOR OF WEB ELEMENTS IN LOG-IN PAGE OF SMGR for AWS-AADS test#####################################
	//###########################################################################################################################

	/*	
	public By LOGIN_PAGE_USERNAME_TXT										=	By.xpath("//*[@id='IDToken1']");
	public By LOGIN_PAGE_PASSWORD_TXT										=	By.xpath("//*[@id='IDToken2']");
	public By LOGIN_PAGE_SUBMIT_BTN											=	By.xpath("//*[@id='SubmitButton']");
	*/
	//*[@name = 'IDToken2' and @class='input_text']

	//public By LOGIN_PAGE_USERNAME_TXT										=	By.xpath("//*[@id='j_username']");
	//public By LOGIN_PAGE_PASSWORD_TXT										=	By.xpath("//*[@id='j_password']");
	//public By LOGIN_PAGE_USERNAME_TXT										=	By.xpath("//*[@name = 'IDToken1' and @class='input_text']");
	//public By LOGIN_PAGE_PASSWORD_TXT										=	By.xpath("//*[@name = 'IDToken2' and @class='input_text']");
	//public By LOGIN_PAGE_USERNAME_TXT										=	By.xpath("//*[@id='j_username']");
	//public By LOGIN_PAGE_PASSWORD_TXT										=	By.xpath("//*[@id='j_password']");
	//public By LOGIN_PAGE_USERNAME_TXT										=	By.xpath("//*[@name = 'IDToken1' and @class='input_text']");
	//public By LOGIN_PAGE_PASSWORD_TXT										=	By.xpath("//*[@name = 'IDToken2' and @class='input_text']");

	//*[@name = 'IDToken2' and @class='input_text']

	
		
	//###########################################################################################################################
	//########################################LOCATOR OF WEB ELEMENTS IN MAIN PAGE OF AADS#####################################
	//###########################################################################################################################
		

	public By mainPageGroupFeaturesLocators(String groupName) {				
		return By.cssSelector("div[class *= 'Tree'] div[id *= '-" + groupName +"-content']");}
		

		
		
	public By MAIN_PAGE_USER_MANAGEMENT										=	By.xpath("//*[@id='Users_UserManagement']/a");
		
		
	//###########################################################################################################################
	//########################################LOCATOR OF WEB ELEMENTS IN USER MANAGEMENT PAGE####################################
	//###########################################################################################################################
		
	public By USER_MANAGEMENT_PAGE_MANAGE_USER								=	By.xpath("//*[@id='table1_core_table_content']/tbody/tr[2]/td[1]/a");
	public By USER_MANAGEMENT_PAGE_IFRAME									=	By.xpath("//*[@id='iframe0']");
	
//	public By USER_MANAGEMENT_PAGE_FILTER_ENABLE_BTN						=	By.xpath("//*[@id='table_1_enableFiltering']");
//	public By USER_MANAGEMENT_PAGE_SIP_FILTER_TXT							=	By.xpath("//*[@id='user_e146_handle_name_filter']");
//	public By USER_MANAGEMENT_PAGE_APPLY_FILTER_BTN							=	By.xpath("//*[@id='table_1_filterColumns']");
	
	public By USER_MANAGEMENT_PAGE_FILTER_ENABLE_BTN						=	By.xpath("//a[text()='Enable']");
	public By USER_MANAGEMENT_PAGE_APPLY_FILTER_BTN							=	By.xpath("//a[text()='Apply']");
	
	public By USER_MANAGEMENT_PAGE_SELECT_USER_BTN							=	By.xpath("//*[@id='table_1:0']");
	public By userManagePageActionbuttonLocators (String action)			{	return By.xpath("//button[text()='"+action+"']");};
	
	public By USER_MANAGEMENT_PAGE_STATUS_BTN								=	By.xpath("//*[@id='statusIcon']/a[2]/span");
	public By USER_MANAGEMENT_PAGE_STATUS_SUCESSFULLY_TXT					=	By.xpath("//span[contains(text(),'User updated successfully.')]");
	//###########################################################################################################################
	//########################################LOCATOR OF WEB ELEMENTS USER INFORMATIOM PAGE######################################
	//###########################################################################################################################
	
	public By VIEW_USER_PAGE_SM_PRIMARY_TXT									=	By.xpath("//*[@id='asm_inputAsm1']");
	public By VIEW_USER_PAGE_SM_SECONDARY_TXT								=	By.xpath("//*[@id='asm_inputAsm2']");
	public By VIEW_USER_PAGE_SIP_PHONE_NUMBER_TXT							=	By.xpath("//*[@id='extn']");
	
	//public By EDIT_USER_PAGE_COMMIT_BTN									=	By.xpath("//button[text()='Commit']");
	public By EDIT_USER_PAGE_COMMIT_BTN										=	By.xpath("//*[@id='btn_bottom1']");
	public By EDIT_USER_PAGE_CANCEL_BTN										=	By.xpath("//*[@id='btn_bottom2']");
	
//	public By USER_INFORMATION_CONTACTS_TAB									=	By.xpath("//div[text()='Contacts']");
	public By USER_INFORMATION_CONTACTS_TAB									=	By.xpath("//div/ul/li/a[contains(text(),'Contacts')]");
	public By USER_INFORMATION_IDENTITY_TAB									=	By.xpath("//*[@id='avTabs0']/ul/li[1]/a");
	public By USER_INFORMATION_COMMUNICATION_PROFILE_TAB					=	By.xpath("//*[@id='avTabs0']/ul/li[2]/a");
	
	//###########################################################################################################################
	//########################################LOCATOR OF PRESENCE PROFILE ELEMENT USER INFORMATIOM PAGE######################################
	//###########################################################################################################################
		
	public By EDIT_USER_PRESENCE_PROFILE_CHECKBOX							=	By.xpath("//span[text()='Presence Profile']/../..//*[@type='checkbox']");
	public By EDIT_USER_PRESENCE_SYSTEM_DROPLIST							=	By.xpath("//*[@id='selectOnePresenceServer']");
	public By EDIT_USER_IM_GATEWAY_DROPLIST									=	By.xpath("//*[@id='selectOneImGatewaySipEntity']");
	
	

	//###########################################################################################################################
	//########################################LOCATOR OF USER INFORMATION PAGE######################################
	//###########################################################################################################################
			
	public By USER_INFORMATION_CONTACTS_TAB_ADD_BTN							=	By.xpath("//*[@id='btnAddContactMember']");
	public By USER_INFORMATION_CONTACTS_TAB_REMOVE_BTN						=	By.xpath("//*[@id='btnRemoveContactMember']");
public By USER_INFORMATION_CONTACTS_TAB_EDIT_BTN							=	By.xpath("//*[@id='btnEditContactMember']");

	
	public By USER_INFORMATION_CONTACTS_TAB_ADD_USER_CONTACT_USER_CHECKBOX	=	By.xpath("//*[@id='publicContactsTable:0']");
	public By USER_INFORMATION_CONTACTS_TAB_ADD_SELECT_BTN					=	By.xpath("//*[@id='btn_select1']");
	
	
	public By userInformationContactsTabRemoveContactCheckboxLocator (String user)			{	return By.xpath("//span[text()='"+user+"']//..//..//*[@type='checkbox']");};
	
	
	public By userSearchFilteringTxt 		(String name)			{	return By.xpath("//input[@title='Enter "+ name +" for Filtering']");};

		
	//###########################################################################################################################
		//########################################LOCATOR OF WEB ELEMENTS IN LOG-IN PAGE OF SMGR - VMWare#####################################
		//###########################################################################################################################

		
		public By LOGIN_PAGE_USERNAME_TXT									=	By.xpath("//*[@id='j_username']");
		public By LOGIN_PAGE_PASSWORD_TXT									=	By.xpath("//*[@id='j_password']");	
		
			
		public By LOGIN_PAGE_SUBMIT_BTN										=	By.xpath("//*[@id='SubmitButton']");
		
		
		public By LOGOFF_PAGE												=	By.xpath("//*[@id='logoff']");
		public By USER_DELETE_CONFIRMATION									= 	By.xpath("//*[@id='deleteButtonBottom']");
		public By MORE_ACTION												=	By.xpath("//*[@id='dropDownButton_button']");
		//public By SHOW_DELETED_USERS										= 	By.xpath("//*[@title='Show Deleted Users']");
		public By SHOW_DELETED_USERS										= 	By.xpath("//a[@title='Show Deleted Users']");
		//public By SHOW_DELETED_USERS										= 	By.xpath("//div[contains(@style,'visible')]//*[@title='Show Deleted Users']/..");
		public By SHOW_DELETED_USERS2										=	By.xpath("//td[@class='r1_dropdown_data_col_onmouseover']/a[@title='Show Deleted Users']");
		public By DELETE_USER_HARD											=   By.xpath("//*[@id='deleteButtonTop']");

		public By SMGR8_LOGIN_PAGE_USERNAME_TXT								=	By.xpath("//*[@id='j_username']");
		public By SMGR8_LOGIN_PAGE_PASSWORD_TXT								=	By.xpath("//*[@id='j_password']");
		public By SMGR8_LOGIN_PAGE_SUBMIT_BTN								=	By.xpath("//*[@id='SubmitButton']");
		public By SMGR8_MAINPAGE_USERS_DROPDOWN_BTN							=   By.xpath("//span[@class='fa fa-user']");
		public By SMGR8_MAINPAGE_USERS_MENU_USER_MANAGEMENT_BTN				=   By.xpath("//*[text() ='User Management']");
		public By SMGR8_MAINPAGE_USERS_MENU_SUBMENU_MANAGE_USER_BTN  		=   By.xpath("//*[text() ='Manage Users']");
		public By SMGR8_USER_MANAGEMENT_PAGE_IFRAME							=	By.xpath("//*[@id='iframe0']");
		public By SMGR8_USER_MANAGEMENT_PAGE_SIP_HANDLE_FILTER_BTN			=  	By.xpath("//span[text()='SIP Handle']//i[@class='anticon anticon-filter']/..");
		public By smgr8filterbutton (String name)	{
			return By.xpath("//span[text()='"+name+"']//i[@class='anticon anticon-filter']/..");};
			
		public By SMGR8_USER_MANAGEMENT_PAGE_SIP_HANDLE_FILTER_INPUT_TXT	=  	By.xpath("//input[@id='filter_textfield_userHandle']");
		public By SMGR8_USER_MANAGEMENT_PAGE_REFRESH_BTN					=   By.xpath("//i[@title='Refresh']");
		public By SMGR8_USER_MANAGEMENT_PAGE_SELECT_USER_BTN				=	By.xpath("//div[@class='ant-table-body']//span[@class='ant-checkbox']");
		public By smgr8_userManagePageActionbuttonLocators (String action)			{	return By.xpath("//button/span[text()='"+action+"']/..");};
		public By SMGR8_USER_INFORMATION_CONTACTS_TAB						=	By.xpath("//*[text()='Contacts']");
		public By SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_BTN			=  	By.xpath("//*[text()='First Name']//*[@id='filter_icon_firstName']");
		public By SMGR8_USER_MANAGEMENT_PAGE_FIRST_NAME_FILTER_INPUT_TXT	=  	By.xpath("//input[@id='filter_textfield_firstName']");
		public By smgr8_userInformationContactsTabRemoveContactCheckboxLocator (String user) {
			return By.xpath("//td[text()='"+user+"']/..//*[@class='ant-checkbox']");
		}

		public By SMGR8_USER_INFORMATION_CONTACTS_TAB_ADD_BTN				=	By.xpath("//button[@class='ant-btn plus_AssCon']");
		public By SMGR8_USER_INFORMATION_ADD_POPUP_FIRSTNAME_FILTER			= 	By.xpath("//div[@class='ant-modal-body']//span[text()='First Name']//i[@class='anticon anticon-filter']/..");
		public By SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_SELECT_USER_BTN		=	By.xpath("//div[@class='ant-modal-body']//div[@class='ant-table-body']//span[@class='ant-checkbox']");
		public By SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_REFRESH_BTN			=   By.xpath("//div[@class='ant-modal-body']//i[@title='Refresh']");
		public By SMGR8_USER_MANAGEMENT_ADD_POPUP_PAGE_OK_BTN				=   By.xpath("//span[text()='OK']/..");
		public By SMGR8_USER_MANAGEMENT_PAGE_COMMIT_BTN						=	By.xpath("//span[text()='Commit']/..");
		public By SMGR8_USER_MANAGEMENT_PAGE_REMOVE_BTN						=	By.xpath("//span[text()='Remove']/..");
		
		public By SMGR8_USER_DELETE_CONFIRMATION							= 	By.xpath("//*[text()='Yes']/parent::button");
		public By SMGR8_SHOW_DELETED_USERS									= 	By.xpath("//*[text()='Show Deleted Users']");
		public By SMGR8_USER_HARD_DELETE_CONFIRMATION						= 	By.xpath("//*[text()='OK']/parent::button");
		public By SMGR8_LOGOFF_PAGE_COVER									=	By.xpath("//div[@class='right-menu-badge']/following::div[1]/span//span");
		public By SMGR8_LOGOFF_PAGE_BTN										=	By.xpath("//*[text()='Log Out']/ancestor::li");
		


}
