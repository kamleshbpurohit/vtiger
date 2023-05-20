package genericLibraries;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener
{
	public ExtentReports report ;
	public ExtentTest test;
	@Override
	public void onStart(ITestContext context) 
	{
		ExtentSparkReporter spark=new ExtentSparkReporter("./ExtentReports/report.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Vtiger CRM extent reports");
		spark.config().setReportName("vtiger");
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Author", "Kamlesh");
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("Java Version", System.getProperty("java.version"));	
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		RemoteWebDriver remote = (RemoteWebDriver)BaseClass.sdriver;
		Capabilities cap = remote.getCapabilities();
		report.setSystemInfo("Browser", cap.getBrowserName()+" "+cap.getBrowserVersion());
		test=report.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test.pass(result.getMethod().getMethodName()+" "+"Pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName()+" Fail");
		test.fail(result.getThrowable());
		WebDriverUtility web = new WebDriverUtility();
		String screenshotPath = web.getScreenshot(BaseClass.sdriver, BaseClass.sjavaUtil, result.getMethod().getMethodName());
		test.addScreenCaptureFromPath(screenshotPath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName()+" skipped");
		test.fail(result.getThrowable());
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	
}
