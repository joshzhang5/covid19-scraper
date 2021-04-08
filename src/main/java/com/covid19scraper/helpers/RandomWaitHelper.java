package com.covid19scraper.helpers;

import java.util.function.Consumer;
import org.openqa.selenium.WebElement;


public class RandomWaitHelper {

  public static void actAfterRandomWait(Consumer<WebElement> consumer, WebElement webElement) {
    randomWait();
    consumer.accept(webElement);
  }

  public static void randomWait() {
    int waitTime = (int) (Math.random() * 1000);
    try {
      Thread.sleep(waitTime);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }
}
