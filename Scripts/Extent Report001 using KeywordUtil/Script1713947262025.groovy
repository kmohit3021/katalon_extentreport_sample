import org.openqa.selenium.WebDriver

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

KeywordUtil.logInfo('Browser Launched')

WebUI.navigateToUrl('https://www.google.com/')

WebUI.maximizeWindow()

KeywordUtil.logInfo('Navigated to www.google.com')

RunConfiguration.getProjectDir()

WebUI.takeScreenshot('Screenshots/' + 'googleScreenshot.png')

KeywordUtil.logInfo('Snapshot below: ' + TestListenerExtentReport.extentTest.addScreenCapture(RunConfiguration.getProjectDir() + '/Screenshots/googleScreenshot.png'))

// get title.
String title = WebUI.getWindowTitle()

println(title)

KeywordUtil.logInfo('Get the WebSite title')

// Verify title.
if (title.equalsIgnoreCase('Google')) {
    KeywordUtil.markPassed('Title verified')
} else {
	StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
	int lineNumber = stackTrace[2].getLineNumber();
    String dest = CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.getScreeshot'(DriverFactory.getWebDriver(), TestListenerExtentReport.execID, "Line:"+lineNumber+"_"+TestListenerExtentReport.testcasename)

    KeywordUtil.markFailed('Error Line : '+ lineNumber + TestListenerExtentReport.extentTest.addScreenCapture(dest))
}

WebUI.closeBrowser()

KeywordUtil.logInfo('Browser closed')
