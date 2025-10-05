package org.example.pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Path("/catalog/courses")
public class CatalogPage extends AbsBasePage<CatalogPage> {

  public CatalogPage(WebDriver driver) {
    super(driver);
  }

  public void checkCategoryFilterIsApplied(String categoryName) {
    assertThat(getElement(By.xpath("//label[text()='" + categoryName + "']/..")).getAttribute("value"))
        .isEqualTo("true");
  }
}
