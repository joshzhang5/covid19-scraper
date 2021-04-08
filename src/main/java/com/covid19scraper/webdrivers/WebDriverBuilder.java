package com.covid19scraper.webdrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriverBuilder {
  private static WebDriverBuilder instance = null;

  private WebDriverBuilder() {
    initChromeWebDriverSettings();
  }

  public static WebDriverBuilder getInstance() {
    if (null == instance) {
      return new WebDriverBuilder();
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
