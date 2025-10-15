package org.example.components;

import static org.example.constants.Constants.getCourseLocatorByName;

import com.google.inject.Inject;
import org.apache.commons.lang3.tuple.Pair;
import org.example.annotations.Component;
import org.example.data.CourseData;
import org.example.exceptions.ElementNotFoundException;
import org.example.scoped.GuiceScoped;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Component("//a/h6/..")
public class CourseCard extends AbsComponent {

  @Inject
  public CourseCard(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void clickCard(String courseName) {
    actions.moveToElement(getComponentEntity(By.xpath(String.format("//a/h6[normalize-space(.)='%s']", courseName))))
        .click().perform();
  }

  private String getCourseName(WebElement card) {
    return card.findElement(By.xpath("./h6")).getText();
  }

  private Pair<MonthDay, String> getCourseStartDate(String courseName) {
    Document document;
    try {
      document = Jsoup.connect(Objects.requireNonNull(driver.getCurrentUrl())).get();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Element element = document.selectXpath(getCourseLocatorByName(courseName)).first();
    if (element != null) {
      String date = element.text().split(",")[0];
      MonthDay monthDay = MonthDay.parse(date, DateTimeFormatter.ofPattern("dd MMMM", new Locale("ru")));
      return Pair.of(monthDay, date);
    } else throw new ElementNotFoundException();
  }

  public List<CourseData> findCoursesByStartDate(String startDate) {
    List<WebElement> courseCards = getAllComponentEntity();
    courseCards.remove(0);

    MonthDay dateCompareTo = MonthDay.parse(startDate, DateTimeFormatter.ofPattern("dd MMMM", new Locale("ru")));

    return courseCards.stream()
        .filter(courseElement -> getCourseStartDate(getCourseName(courseElement)).getLeft()
            .compareTo(dateCompareTo) >= 0)
        .map(course -> {
          String courseName = getCourseName(course);
          Pair<MonthDay, String> currentDate = getCourseStartDate(courseName);
          return new CourseData(course, courseName, currentDate.getRight(), null);
        })
        .toList();
  }

  public List<CourseData> findCheapestAndMostExpensiveCourses() {
    List<WebElement> courseCards = getAllComponentEntity();
    courseCards.remove(0);

    List<CourseData> courses = courseCards.stream().map(course -> {
      actions.moveToElement(course).click().perform();
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
        String courseTittle = document.selectXpath("//h1[1]").text();
        Integer coursePrice = Integer.valueOf(document.selectXpath("(//h3)[1]/../div[2]").text().split(" ")[0]);
        driver.navigate().back();
        waiter.waitForElementPresence(By.xpath("//h1[normalize-space()='Каталог']"));
        return new CourseData(null, courseTittle, null, coursePrice);
      }
    }).toList();

    List<Integer> prices = courses.stream().map(CourseData::getPrice).toList();
    Integer minPrice = prices.stream().min(Integer::compareTo).orElseThrow();
    Integer maxPrice = prices.stream().max(Integer::compareTo).orElseThrow();

    return courses.stream()
        .filter(course -> course.getPrice().equals(minPrice) || course.getPrice().equals(maxPrice))
        .toList();
  }
}
