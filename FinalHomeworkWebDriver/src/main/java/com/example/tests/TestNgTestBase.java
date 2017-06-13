package com.example.tests;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Capabilities;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

import com.example.util.PropertyLoader;

/**
 * Base class for TestNG-based test classes
 */
public class TestNgTestBase {

  protected static String gridHubUrl;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  protected WebDriver driver;

  @BeforeSuite
  public void initTestSuite() throws IOException {
    baseUrl = PropertyLoader.loadProperty("site.url");
    gridHubUrl = PropertyLoader.loadProperty("grid.url");
    if ("".equals(gridHubUrl)) {
      gridHubUrl = null;
    }
    capabilities = PropertyLoader.loadCapabilities();
  }

  @BeforeMethod
  /**
   * initWebDriver()
   * This method contains setting of system property - path to Web Driver
   * and setting of driver properties.
   *
   */
  public void initWebDriver() {
    File file = new File("C:/tools/chromedriver.exe");
    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {

    try{
      driver.quit();
    }
    catch (Exception anException)
    {
      anException.printStackTrace();
    }

    //WebDriverPool.DEFAULT.dismissAll();
  }
}
