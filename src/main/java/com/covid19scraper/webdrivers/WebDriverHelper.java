package com.covid19scraper.webdrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriverHelper {
  private static WebDriverHelper instance = null;

  private WebDriverHelper() {
    initChromeWebDriverSettings();
  }

  public static WebDriverHelper getInstance() {
    if (null == instance) {
      return new WebDriverHelper();
    }
    return instance;
  }

  public WebDriver getChromeWebDriver() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-blink-features=AutomationControlled");
    return new ChromeDriver(options);
  }

  private static void initChromeWebDriverSettings() {
    String exePath = "web-drivers/chromedriver";
    System.setProperty("webdriver.chrome.driver", exePath);
  }
}
