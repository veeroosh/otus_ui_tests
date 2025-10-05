package org.example.commons;

import org.example.helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class AbsCommon {

  protected WebDriver driver;
  protected Waiter waiter;
  protected Actions actions;

  public AbsCommon(WebDriver driver) {
    this.driver = driver;
    this.waiter = new Waiter(driver);
    this.actions = new Actions(driver);

    PageFactory.initElements(driver, this);
  }

  protected WebElement getElement(By selector) {
    return driver.findElement(selector);
  }
}
