package tests.acceptanceTestCases;
import java.util.Properties;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import pages.acceptanceTestCases.ResultPage;
import utlilities.TestBase;

public class TestHomePage extends TestBase{
	private Logger logger=Logger.getLogger(TestHomePage.class);
	//private static Logger logger=Logger.getLogger(TestHomePage.class);
	//Logger logger=Logger.getLogger(HomePage.class);

	@BeforeClass
	public void beforeClass(){
		initBeforeClass();
	}
	@BeforeMethod
	public void setUp(){

		initBeforeMethod();
	}//This is New Chang
	
	@AfterMethod
	public void tearDown(ITestResult result){
		closeAllBr();
		nullifyAllVariables();
		System.out.println("Test case with name: " + result.getMethod().getMethodName()+" has finished.");
	}
	
	@Test
	public void testZebraFromCA()throws Throwable
	{
		ObjTestHomePage.purchase("10", "0", "0", "0", "CA");
		validation();
	}
	
	@Test
	public void testLionFromNY()throws Throwable
	{
		ObjTestHomePage.purchase("0", "10", "0", "0", "NY");
		validation();
	}
	
	@Test
	public void testElephantFromMN()throws Throwable
	{
		ObjTestHomePage.purchase("0", "0", "2", "0", "MN");
		validation();
	}
	@Test
	public void testAllBooksWithDefaultTax()throws Throwable
	{
		ObjTestHomePage.purchase("10", "10", "2", "10", "AL");
		validation();
	}
	@Test
	public void testAllBooksWithMaximumAvailableBooksFromWY()throws Throwable
	{
		ObjTestHomePage.purchase("13", "20", "35", "17", "WY");
		validation();
	}
	@Test
	public void testAllBooksWithMinimumAvailableBooksFromTX()throws Throwable
	{
		ObjTestHomePage.purchase("1", "1", "1", "1", "TX");
		validation();
	}
	public void validation()
	{
		ObjTestHomePage.totalWithoutTaxes();
		System.out.println(("Expected taxes is: "+ObjTestHomePage.totalSalesTax()+" and Actual sales tax is: "+ObjResultPage.resultSalesTax()));
		System.out.println(("Expected Total Price is: "+ObjTestHomePage.totalWithTaxes()+" and Actual Total Price is: "+ObjResultPage.resultTotalCost()));
		if(ObjTestHomePage.totalSalesTax()!=ObjResultPage.resultSalesTax())
		{
			Assert.fail("Expected taxes: "+ObjTestHomePage.totalSalesTax()+"and Actual taxe: "+ObjResultPage.resultSalesTax()+" doesn't match. Hence Failing test cases.");
		}
		
		if(ObjTestHomePage.totalWithTaxes()!=ObjResultPage.resultTotalCost())
		{
			Assert.fail("Expected Total Price: "+ObjTestHomePage.totalWithTaxes()+" and Actual Total Price: "+ObjResultPage.resultTotalCost()+" doesn't match. Hence failing test cases.");
		}
	}
	
}
