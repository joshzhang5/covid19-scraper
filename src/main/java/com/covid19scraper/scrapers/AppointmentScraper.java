package com.covid19scraper.scrapers;

import com.covid19scraper.models.Appointment;
import java.util.List;
import org.openqa.selenium.WebDriver;


public abstract class AppointmentScraper {
  String site;
  WebDriver webDriver;

  public AppointmentScraper(
      String site,
      WebDriver webDriver) {
    this.site = site;
    this.webDriver = webDriver;
  }

  public List<Appointment> fetchAppointments() {
    return scrape(getAppointmentHtml());
  }

  abstract public boolean hasAvailableAppointments();

  abstract String getAppointmentHtml();

  abstract public List<Appointment> scrape(String finalHtml);
}
