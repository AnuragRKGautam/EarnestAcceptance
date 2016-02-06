package pages.acceptanceTestCases;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import utlilities.TestBase;

public class ResultPage extends TestBase{

	private Logger logger=Logger.getLogger(ResultPage.class);
	public ResultPage(EventFiringWebDriver driver, Properties locator){
		this.driver=driver;
		this.locator=locator;
	}
	public int resultTotalWithoutTax()
	{
		
		String SubtotalOnResultPageWithDollarSign=getTextValue(By.xpath(locator.getProperty("order_page_subtotal_xpath")));
		String SubtotalOnResultPage=SubtotalOnResultPageWithDollarSign.substring(1);
		double actualSubTotalOnResultPage=Double.parseDouble(SubtotalOnResultPage);
		int stOnresPage= (int) actualSubTotalOnResultPage;
     	return stOnresPage;
	}
	public double resultSalesTax()
	{
       String SalesTaxOnResultPageWithDollarSign=getTextValue(By.xpath(locator.getProperty("order_page_taxes_xpath"))).substring(1);
       double actualSalesTaxOnResultPageWithoutDollarSign=Double.parseDouble(SalesTaxOnResultPageWithDollarSign);
     return actualSalesTaxOnResultPageWithoutDollarSign;
	}
	public double resultTotalCost()
	{
       String TotalResultPageWithDollarSign=getTextValue(By.xpath(locator.getProperty("order_page_total_xpath"))).substring(1).replace(",","");
       double actualTotalOnResultPageWithoutDollarSign=Double.parseDouble(TotalResultPageWithDollarSign);
     	return actualTotalOnResultPageWithoutDollarSign;
	}
}
