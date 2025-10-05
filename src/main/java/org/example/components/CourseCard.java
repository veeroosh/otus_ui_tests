package org.example.components;

import static org.example.constants.Constants.getCourseLocatorByName;

import org.apache.commons.lang3.tuple.Pair;
import org.example.annotations.Component;
import org.example.data.CourseData;
import org.example.exceptions.ElementNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

@Component("//a/h6/..")
public class CourseCard extends AbsComponent {

  public CourseCard(WebDriver driver) {
    super(driver);
  }

  public void clickCard(String courseName) {
    actions.moveToElement(getComponentEntity(By.xpath(String.format("//a/h6[normalize-space(.)='%s']", courseName))))
        .click().perform();
  }

  private String getCourseName(WebElement card) {
    return card.findElement(By.xpath("./h6")).getText();
  }

  public Pair<MonthDay, String> getCourseStartDate(String courseName) {
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

  public List<CourseData> findEarliestAndLatestCourses() {
    List<WebElement> courseCards = getAllComponentEntity();
    courseCards.remove(0);

    List<CourseData> minCourses = new ArrayList<>();
    List<CourseData> maxCourses = new ArrayList<>();
    MonthDay minDate = null;
    MonthDay maxDate = null;

    for (WebElement card : courseCards) {
      String courseName = getCourseName(card);
      Pair<MonthDay, String> date = getCourseStartDate(courseName);
      CourseData currentCourse = new CourseData(card, courseName, date.getRight());

      if (minDate == null || date.getLeft().compareTo(minDate) < 0) {
        minDate = date.getLeft();
        minCourses.clear();
        minCourses.add(currentCourse);
      } else if (date.getLeft().equals(minDate)) {
        minCourses.add(currentCourse);
      }

      if (maxDate == null || date.getLeft().compareTo(maxDate) > 0) {
        maxDate = date.getLeft();
        maxCourses.clear();
        maxCourses.add(currentCourse);
      } else if (date.getLeft().equals(maxDate)) {
        maxCourses.add(currentCourse);
      }
    }

    return Stream.concat(minCourses.stream(), maxCourses.stream()).toList();
  }
}
