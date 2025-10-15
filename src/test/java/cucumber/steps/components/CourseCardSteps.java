package cucumber.steps.components;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import org.example.components.CourseCard;
import org.example.data.CourseData;

public class CourseCardSteps {

  @Inject
  private CourseCard courseCard;

  @И("^Кликнуть карточку курса (.*)")
  public void clickCourseCategory(String courseName) {
    courseCard.clickCard(courseName);
  }

  @И("^Поиск курсов, стартующих (.*) или позже")
  public void findCourseByStartDate(String startDate) {
    System.out.printf("Курсы, стартующие %s или позже: %s%n", startDate,
        courseCard.findCoursesByStartDate(startDate).stream()
            .map(CourseData::getCourseNameAndStartDate)
            .toList());
  }

  @И("^Поиск самых дорогих и дешевых курсов")
  public void findCheapestAndMostExpensiveCourses() {
    System.out.printf("Самые дорогие и дешевые курсы: %s%n",
        courseCard.findCheapestAndMostExpensiveCourses().stream()
        .map(CourseData::getCourseNameAndPrice)
        .toList());
  }
}
