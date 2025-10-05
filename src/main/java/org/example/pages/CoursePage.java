package org.example.pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.constants.Constants.getCourseLocatorByName;

import org.example.data.CourseData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.util.List;

public class CoursePage extends AbsBasePage<CoursePage> {
  public CoursePage(WebDriver driver) {
    super(driver);
  }

  public void checkTittle(String tittleName) {
    assertThat(getElement(By.xpath("//h1")).getText()).isEqualTo(tittleName);
  }

  public void checkCoursesTittlesAndStartDates(List<CourseData> courses) {
    courses.forEach(course -> {
      WebElement element = getElement(By.xpath(getCourseLocatorByName(course.getTitle())));
      actions.moveToElement(element).click().perform();
      Document document;
      String url = driver.getCurrentUrl();
      if (url == null)
        throw new NullPointerException();
      else {
        try {
          document = Jsoup.connect(driver.getCurrentUrl()).get();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        assertThat(document.selectXpath("//h1[1]").text()).isEqualTo(course.getTitle());
        assertThat(document.selectXpath("((//section)[2]/div[3]//p)[1]").text()).isEqualTo(course.getStartDate());
        driver.navigate().back();
        waiter.waitForElementPresence(By.xpath("//h1[normalize-space()='Каталог']"));
      }
    });
  }
}
