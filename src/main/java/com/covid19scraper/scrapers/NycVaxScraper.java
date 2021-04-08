package com.covid19scraper.scrapers;

import com.covid19scraper.models.Appointment;
import com.covid19scraper.webdrivers.WebDriverBuilder;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.covid19scraper.helpers.RandomWaitHelper.*;


public class NycVaxScraper extends AppointmentScraper {
  private static final String URL = "https://vax4nyc.nyc.gov/patient/s/vaccination-schedule";

  public static void main(String[] args) {
    NycVaxScraper nycVaxScraper = new NycVaxScraper();
    nycVaxScraper.fetchAppointments();
  }

  public NycVaxScraper() {
    super(
        URL,
        WebDriverBuilder.getInstance().getChromeWebDriver()
    );
  }

  @Override
  public boolean hasAvailableAppointments() {
    return getAppointmentHtml().contains("No appointments are currently available.");
  }

  @Override
  String getAppointmentHtml() {
    webDriver.get(site);
    // wait for shit
    webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    // page 1
    WebElement body = webDriver.findElements(By.className("vcms-body")).get(1);
    actAfterRandomWait(element -> element.findElement(By.className("slds-checkbox_faux")).click(), body);
    actAfterRandomWait(element -> element.findElements(By.className("slds-radio_faux")).get(1).click(), body);
    actAfterRandomWait(element -> element.findElement(By.className("slds-input")).sendKeys("12/06/1996"), body);
    actAfterRandomWait(element -> element.findElements(By.className("slds-input")).get(1).sendKeys("10001"), body);
    actAfterRandomWait(element -> element.findElements(By.className("slds-radio_faux")).get(2).click(), body);
    actAfterRandomWait(element -> element.findElements(By.className("slds-button")).get(2).click(), body);
    // page 2
    body = webDriver.findElements(By.className("vcms-body")).get(1);
    actAfterRandomWait(element -> element.findElements(By.className("slds-radio_faux")).get(1).click(), body);
    actAfterRandomWait(element -> element.findElement(By.name("vaccineEligibilityConsentName")).sendKeys("Zarwan Hashem"), body);
    actAfterRandomWait(element -> element.findElement(By.className("slds-checkbox_faux")).click(), body);
    actAfterRandomWait(element -> element.findElements(By.className("slds-button")).get(1).click(), body);

    // page 3
    body = webDriver.findElements(By.className("vcms-body")).get(1);
    String htmlText = body
        .findElement(By.className("appointment-section"))
        .getText();
    webDriver.close();
    return htmlText;
  }

  @Override
  public List<Appointment> scrape(String appointmentHtml) {
    return Collections.emptyList();
  }
}
