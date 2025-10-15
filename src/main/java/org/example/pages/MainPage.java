package org.example.pages;

import com.google.inject.Inject;
import org.example.annotations.Path;
import org.example.scoped.GuiceScoped;
import org.openqa.selenium.By;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void clickCourseCategory(String categoryName) {
    actions.moveToElement(getElement(By.xpath("//span[text()='Обучение']/.."))).perform();
    actions.moveToElement(getElement(By.xpath("//a[contains(text(), '" + categoryName + "')]")))
        .click().perform();
  }
}
