import org.openqa.selenium.WebDriver

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.ExtentReports
import com.relevantcodes.extentreports.ExtentTest

WebUI.openBrowser('')

KeywordUtil.logInfo('Browser Launched')

WebUI.navigateToUrl('https://www.katalon.com/')

WebUI.maximizeWindow()

KeywordUtil.logInfo('Navigated to www.katalon.com')

RunConfiguration.getProjectDir()

WebUI.takeScreenshot('Screenshots/' + 'katalonScreenshot.png')

KeywordUtil.logInfo('Snapshot below:' + TestListenerExtentReport.extentTest.addScreenCapture(RunConfiguration.getProjectDir() + '/Screenshots/katalonScreenshot.png'))

// get title.
String title = WebUI.getWindowTitle()

println(title)

KeywordUtil.logInfo('Get the WebSite title')

if (title.equalsIgnoreCase('Katalon AI-augmented Software Quality Management Platform2')) {
    KeywordUtil.markPassed('Title verified')
} else {
	StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
	int lineNumber = stackTrace[2].getLineNumber();
    String dest = CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.getScreeshot'(DriverFactory.getWebDriver(), TestListenerExtentReport.execID, "Line:"+lineNumber+"_"+TestListenerExtentReport.testcasename)
    KeywordUtil.markFailed('Error Line : '+ lineNumber+ TestListenerExtentReport.extentTest.addScreenCapture(dest))
}

WebUI.closeBrowser()
KeywordUtil.logInfo('Browser closed')
