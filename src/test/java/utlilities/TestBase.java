package utlilities;

import java.io.FileInputStream;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.log4testng.Logger;
import pages.acceptanceTestCases.HomePage;
import pages.acceptanceTestCases.ResultPage;

public class TestBase {
 protected EventFiringWebDriver driver;
 protected WebDriver dr;
 protected Properties locator;
 protected Properties conf;
 private Logger logger = Logger.getLogger(TestBase.class);
 public final String pathLocator = "src\\test\\resources\\configurations\\Locator.properties";
 private DesiredCapabilities capabilities;
 public final String pathConf = "src\\test\\resources\\configurations\\Conf.properties";
 protected String url;
 protected String br;
 protected String strTextValue = null;
 protected String originalWindowHandle;
 protected HomePage ObjTestHomePage;
 protected ResultPage ObjResultPage;
 public static final long PAGELOAD_TIMEOUT = 45L;
 public static final long IMPLICIT_WAIT = 20L;

 //will be called in before class
 public void initBeforeClass() {
   conf = readPropertiesFile(pathConf);
   url = conf.getProperty("url");
   br = conf.getProperty("browser");
  }
  //will be called in before method
 public void initBeforeMethod() {
   //initialize - all before methods
   logger.info("Starting init method");
   locator = readPropertiesFile(pathLocator);
   //Launch webDriver
   
   dr = getWebDriver(br);
   driver = new EventFiringWebDriver(dr);
     //maximize
   driver.manage().window().maximize();
   originalWindowHandle = driver.getWindowHandle();
   //implicit wait, pageloadtimeout

   driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
   driver.manage().timeouts().pageLoadTimeout(PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
   //navigate
   navigateTo(url);
   createObject();
  }
  //close all existing opened browser
 public void closeAllBr() {
  try {
   driver.close();
   driver.quit();
  } catch (Throwable t) {
   logger.error("cannot close browser", t);
  }
 }
 public Properties readPropertiesFile(String path) {
   try {
    logger.info("loading properties file " + path);
    Properties prop = new Properties();
    FileInputStream fis = new FileInputStream(path);

    prop.load(fis);
    return prop;

   } catch (Throwable t) {
    logger.error("File could not be loaded" + path, t);
   }
   return null;
  }
  //Load Browser

 public WebDriver getWebDriver(String br) {
   //make sure capabilities is not null
   logger.info("Starting WebDriver");
   // Set the desired capabilities
   capabilities = setDesiredCapabilites(br);
   // To run test Cases on Firefox Browser.
   if (br.equalsIgnoreCase("firefox")) {
    dr = new FirefoxDriver(capabilities);
    return dr;
   }
   return null;
  }
  /**
   * 
   * @param br
   * purpose: set desiredCapanbilities
   */
 public DesiredCapabilities setDesiredCapabilites(String browserCapability) {
  capabilities = new DesiredCapabilities();

  switch (browserCapability) {
   case "firefox":
    capabilities.setBrowserName("firefox");
    capabilities.setPlatform(Platform.ANY);
    break;

   default:
    try {
     throw new Throwable("no capalities matched");
    } catch (Throwable e) {
     // TODO Auto-generated catch block
     logger.error("no capabilites found", e);
    }
  }
  return capabilities;
 }

 //createing object of all modules to use in test cases. Create object of every class.
 public void createObject() {
   ObjTestHomePage = new HomePage(driver, locator);
   ObjResultPage = new ResultPage(driver, locator);
  }
  //Nullifying all the variables.
 public void nullifyAllVariables() {
  ObjTestHomePage = null;
  ObjResultPage = null;
 }

 //*****************************************************
 //basic method-type, click, double click, drag and drop
 //GetWebElement
 public WebElement getWebElement(By by) {
  try {
   logger.info("finding webelement by " + by);
   return driver.findElement(by);
  } catch (Throwable t) {
   logger.error("could not find webElement by " + by, t);
  }
  return null;
 }


 //-------------------------------------------------------
 //	Click Method.
 public void click(By by) {
  try {
   getWebElement(by).click();
  } catch (Throwable t) {
   logger.error("cannot click by " + by, t);
  }
 }

 //selects by String value
 public void selectByValue(By by, String value) {
  try {

   new Select(getWebElement(by)).selectByValue(value);

  } catch (Throwable t) {
   logger.error("cannot select webelement by " + by, t);
  }
 }

 /*This Method can be used to clear the text field and send the keys after that*/


 public void clearAndSendKeys(By by, String toType) {
  try {
   getWebElement(by).clear();
   getWebElement(by).sendKeys(toType);
  } catch (Throwable t) {
   logger.error("cannot type to " + by, t);
  }
 }

 public String getTextValue(By by) {
  try {
   //String strTextValue= getWebElement(by).getText();
   strTextValue = getWebElement(by).getText();
  } catch (Throwable t) {
   logger.error("cannot type to " + by, t);
  }
  return strTextValue;
 }

 //This method can be used to navigate to specific url
 public void navigateTo(String url) {
  try {
   logger.info("navigating to url--" + url);
   driver.navigate().to(url);
  } catch (Throwable e) {
   logger.error("cannot navigate to url " + url, e);
  }
 }
}