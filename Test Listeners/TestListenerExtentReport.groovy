import java.nio.file.Files

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver

import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.SmartWaitWebDriver
import com.relevantcodes.extentreports.ExtentReports
import com.relevantcodes.extentreports.ExtentTest
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

class TestListenerExtentReport {
	
	

	public static ExtentReports extent
	public static ExtentTest extentTest
	public static String execID
	public static String testcasename

	
	@BeforeTestSuite
	def deleteHtmlReport() {
		Path folderPath = Paths.get(RunConfiguration.getProjectDir()+"/Extent/");
        try {
            // Delete everything within the folder
            deleteFolderContents(folderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
		//Files.deleteIfExists(Paths.get(RunConfiguration.getProjectDir()+"/Extent/"));
	}
	
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		execID = RunConfiguration.getExecutionSourceName()
		String tcID = RunConfiguration.getExecutionSourceName().toString()
		
		testcasename = testCaseContext.getTestCaseId().substring(testCaseContext.getTestCaseId().lastIndexOf('/') + 1)
		
		extent = CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.setupExtentReport'(execID)
		extentTest = CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.startExtentTest'(tcID+" : "+testcasename, 'Test Execution : '+testCaseContext.getTestCaseId(),
				extent)
	}

	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		WebDriver driver = DriverFactory.getWebDriver()
		CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.tearDownTest'(driver, extent, extentTest)
	}

	
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {	
	}
	
	
	public static void deleteFolderContents(Path folderPath) throws IOException {
		Files.walkFileTree(folderPath, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file); // Delete files
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				if (exc == null) {
					Files.delete(dir); // Delete directories
					return FileVisitResult.CONTINUE;
				} else {
					throw exc;
				}
			}
		});
	}
}