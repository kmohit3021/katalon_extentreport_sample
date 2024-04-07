import org.openqa.selenium.WebDriver

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.ExtentReports
import com.relevantcodes.extentreports.ExtentTest
import com.relevantcodes.extentreports.LogStatus as LogStatus

WebUI.openBrowser('')

TestListenerExtentReport.extentTest.log(LogStatus.INFO, 'Browser Launched')

WebUI.navigateToUrl('https://www.gmail.com')

WebUI.maximizeWindow()

TestListenerExtentReport.extentTest.log(LogStatus.INFO, 'Navigated to www.gmail.com')

RunConfiguration.getProjectDir()

WebUI.takeScreenshot('Screenshots/' + 'gmailScreenshot.png')

TestListenerExtentReport.extentTest.log(LogStatus.INFO, 'Snapshot below:' + TestListenerExtentReport.extentTest.addScreenCapture(RunConfiguration.getProjectDir() + '/Screenshots/gmailScreenshot.png'))

// get title.
String title = WebUI.getWindowTitle()
println(title)

TestListenerExtentReport.extentTest.log(LogStatus.INFO, 'Get the WebSite title')

if (title.equalsIgnoreCase('Gmail2')) {
    TestListenerExtentReport.extentTest.log(LogStatus.PASS, 'Title verified')
} else {
	StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
	int lineNumber = stackTrace[2].getLineNumber();
    String dest = CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.getScreeshot'(DriverFactory.getWebDriver(), TestListenerExtentReport.execID, "Line:"+lineNumber+"_"+TestListenerExtentReport.testcasename)
    TestListenerExtentReport.extentTest.log(LogStatus.FAIL, 'Error Line : '+ lineNumber + TestListenerExtentReport.extentTest.addScreenCapture(dest))
}

WebUI.closeBrowser()
TestListenerExtentReport.extentTest.log(LogStatus.INFO, 'Browser closed')
