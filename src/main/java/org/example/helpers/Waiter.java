package org.example.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Waiter {

  private WebDriver driver;
  private final int waiterTimeout = Integer.parseInt(System.getProperty("waiter.timeout", "8"));

  public Waiter(WebDriver driver) {
    this.driver = driver;
  }

  public boolean waitForCondition(ExpectedCondition condition) {
    try {
      new WebDriverWait(driver, Duration.ofSeconds(waiterTimeout)).until(condition);
      return true;
    } catch (TimeoutException ignored) {
      return false;
    }
  }

  public boolean waitForElementPresence(By locator) {
    return this.waitForCondition(ExpectedConditions.presenceOfElementLocated(locator));
  }
}
