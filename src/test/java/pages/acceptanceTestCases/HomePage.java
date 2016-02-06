package pages.acceptanceTestCases;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utlilities.TestBase;

public class HomePage extends TestBase{
	
	final int zebraPrice=13;
	final int lionPrice=20;
	final int elephantPrice=35;
	final int giraffePrice=17;
	protected int zebra;
	protected int lion;
    protected int elephant;
	protected int giraffe;
	protected String State;
	public int subtotal;
	public double salesTax;
	public double total;
	
	public HomePage(EventFiringWebDriver driver, Properties locator){
		this.driver=driver;
		this.locator=locator;
	}
	public void purchase(String ZebraQuantity,String LionQuantity,String ElephantQuantity, String GiraffeQuantity, String StateMethod)
	{
		clearAndSendKeys(By.id(locator.getProperty("home_Page_quantity_zebra_id")), ZebraQuantity);
		clearAndSendKeys(By.id(locator.getProperty("home_Page_quantity_lion_id")), LionQuantity);
		clearAndSendKeys(By.id(locator.getProperty("home_Page_quantity_elephant_id")), ElephantQuantity);
		clearAndSendKeys(By.id(locator.getProperty("home_Page_quantity_giraffe_id")), GiraffeQuantity);
		selectByValue(By.name(locator.getProperty("home_Page_quantity_shipToState_name")), StateMethod);
		click(By.name(locator.getProperty("home_Page_checkout_name")));
		zebra=Integer.parseInt(ZebraQuantity);
		lion=Integer.parseInt(LionQuantity);
		elephant=Integer.parseInt(ElephantQuantity);
		giraffe=Integer.parseInt(GiraffeQuantity);
		State=StateMethod;

	}
	public int totalWithoutTaxes()
	{
		subtotal = (zebraPrice*zebra)+(lionPrice*lion)+(elephantPrice*elephant)+(giraffePrice*giraffe);
		return subtotal;
	}
	public double totalSalesTax()
	{
		if (State=="CA")
		{
			salesTax=subtotal*0.08;
		}
		else if(State=="NY")
		{
			salesTax=subtotal*0.06;
		}
		else if(State=="MN")
		{
			salesTax=subtotal*0.00;
		}
		else
		{
			salesTax=subtotal*0.05;
		}
		return salesTax;
	}
	
	public double totalWithTaxes()
	{
		total = subtotal+salesTax;
		return total;
	}
}
