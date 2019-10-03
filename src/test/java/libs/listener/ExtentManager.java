package libs.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports reports;
	private static ExtentTest test;
	private static Common commonListener = new Common();
	public static ExtentReports GetExtent()
	
	
    {
		
    	if (reports != null)
    			return reports;
    	reports = new ExtentReports();
    	reports.attachReporter(getHtmlReporter());
    	return reports;
    }	
    private static ExtentHtmlReporter getHtmlReporter() {

    	//String testingDate = commonListener.getTestingDate();
    	String testingDate = commonListener.getDate();
    	
    	htmlReporter = new ExtentHtmlReporter("C:\\AADS\\AADS_Report\\AADS_Report_"+testingDate+".html");
    	htmlReporter.config().setChartVisibilityOnOpen(false);
    	//htmlReporter.loadXMLConfig("D:\\Khanh-workspace\\AADS_Simple_Framework_Khanh\\src\\extent-config.xml");

/*    private static ExtentHtmlReporter geṭ̣̣HtmlReporter() {
        //htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/MyOwnReport.html");
    	htmlReporter = new ExtentHtmlReporter("D:\\Report_04_26_2018.html");
    	htmlReporter.config().setChartVisibilityOnOpen(true);
*/
    	htmlReporter.config().setReportName("Testsuite");
    	htmlReporter.config().setTheme(Theme.STANDARD);
    	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    	htmlReporter.config().setEncoding("ISO-8859-9");
    	htmlReporter.setAppendExisting(false);
    	return htmlReporter;
    }
    
    public static ExtentTest createTest(String name, String description) {
    	test = reports.createTest(name, description);
    	return test;
    }
	

}
