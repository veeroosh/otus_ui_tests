package org.example.pages;

import com.google.inject.Inject;
import org.example.annotations.Path;
import org.example.scoped.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Path("/catalog/courses")
public class CatalogPage extends AbsBasePage<CatalogPage> {

  @Inject
  public CatalogPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void findCourse(String courseName) {
    WebElement searchInput = driver.findElement(By.xpath("//label[text()='Поиск']/.."));
    actions.moveToElement(searchInput).click().perform();
    actions.moveToElement(searchInput).sendKeys(courseName).perform();
  }
}
