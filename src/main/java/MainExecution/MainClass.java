package MainExecution;
import PerformOperation.OperationPerform;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

//@Listeners({ATUReportsListener.class,ConfigurationListener.class,MethodListener.class})
public class MainClass 
{
	/*
	@BeforeMethod
	public static void use() throws Exception
	{
					
	    	  Runtime.getRuntime().exec("TASKKILL /F /IM iexplore.exe");
   	    	  Runtime.getRuntime().exec("TASKKILL /F /IM firefox.exe");		
	}
	*/
 
	static String sBrowser = "";
	static String sBrowser1 = "";
	static String sValue1 = "";
	static String sObjectName = "";
	static String sObjectType = "";
	static String sValue = "";	
	static String sOperation = "";
	HSSFSheet sheet;
	//{
	//System.setProperty("atu.reporter.config", "C:\\Users\\MGHQ1353\\git\\WebDriverTest_Maven\\WebDriverTest_Maven\\atu.properties"); 
	//}	
	public static void main(String args[])throws Exception
	//@Test(priority=1)
	//public void Login(String args[])throws Exception
	{
		MainClass mc= new MainClass();
		mc.Execute("C:\\Scripts\\IS TRACK Regression Suite\\Master Script.xlsx");
		//ExtentReporter er = new ExtentReporter();
		//er.generateReport(xmlSuites, suites, outputDirectory);
		//ExtentTestManager em=new ExtentTestManager();
		//ExtentManager.getReporter();
		//em.getTest();
	
	}
//	public  WebDriver driver()
//	{
//		FirefoxDriver driver=new FirefoxDriver();
//		return driver;
//	}
	@Test(priority=0)
	public void Execute(String FilePath) throws Exception
	{
		//MainClass obj= new MainClass();
		OperationPerform op = new OperationPerform();
		//FileInputStream fisMain = new FileInputStream("C:\\Scripts\\IS TRACK Regression Suite\\Master Script.xlsx");
		FileInputStream fisMain = new FileInputStream(FilePath);
		Workbook wbMain = WorkbookFactory.create(fisMain);
		int totalSheets=wbMain.getNumberOfSheets();
		for(int t=0;t<totalSheets;t++)
			{
			//to iterate over all sheets in driver sheet
				String driverSheet=wbMain.getSheetName(t);
				System.out.println(driverSheet);
				Sheet sheetmain=wbMain.getSheet(driverSheet);
				int rowCount = sheetmain.getLastRowNum()-sheetmain.getFirstRowNum();
				System.out.println(rowCount);
 				for (int i =1; i < rowCount+1; i++)
					{
						Row row = sheetmain.getRow(i);
						System.out.println(row);
						String run =row.getCell(1).toString();
						System.out.println(run);
						if (run.equals("Y"))
						{
							String sSheetName=row.getCell(0).toString();
							System.out.println("Sheet is "+sSheetName);
							FileInputStream fisSheetName = new FileInputStream(sSheetName);
							Workbook wbSheet = WorkbookFactory.create(fisSheetName);
							int noOfSheets=wbSheet.getNumberOfSheets();
							//to iterate over child sheets
							for (int j=0;j<noOfSheets;j++)
							{
								String sheetName=wbSheet.getSheetName(j);
								System.out.println(sheetName);
								Sheet sheet1= wbSheet.getSheet(sheetName);
								//Row row1 = sheet1.getRow(1);
								int rowCount1 = sheet1.getLastRowNum()-sheet1.getFirstRowNum();
								System.out.println(rowCount1);
								for (int k =1; k < rowCount1+1; k++)
								{
									Row row1 = sheet1.getRow(k);		
									System.out.println(row1.getCell(0).toString()+"--"+row1.getCell(1).toString()+"--"+ row1.getCell(2).toString()+"--"+ row1.getCell(3).toString()+"--"+
									row1.getCell(4).toString());
									sBrowser=row1.getCell(0).toString();
									sObjectName=row1.getCell(1).toString();
									sObjectType=row1.getCell(2).toString();
									sOperation=row1.getCell(3).toString();
									sValue=row1.getCell(4).toString();
									op.perform(sBrowser,sObjectName,sObjectType,sOperation,sValue);
								}
							}
						}
					}
				}
	}
	
	
}

