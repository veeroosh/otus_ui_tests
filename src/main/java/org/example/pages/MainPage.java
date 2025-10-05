package org.example.pages;

import org.example.annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  public MainPage(WebDriver driver) {
    super(driver);
  }

  public void clickCourseCategory(String categoryName) {
    actions.moveToElement(getElement(By.xpath("//span[text()='Обучение']/.."))).perform();
    actions.moveToElement(getElement(By.xpath("//a[contains(text(), '" + categoryName + "')]")))
            .click().perform();
  }
}
