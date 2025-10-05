package uitests;

import com.google.inject.Inject;
import org.example.components.CourseCard;
import org.example.constants.Course;
import org.example.extensions.UIExtensions;
import org.example.pages.CatalogPage;
import org.example.pages.CoursePage;
import org.example.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

@ExtendWith(UIExtensions.class)
public class CatalogPageTest {

  @Inject
  private MainPage mainPage;

  @Inject
  private CatalogPage catalogPage;

  @Inject
  private CoursePage coursePage;

  @Inject
  private CourseCard courseCard;

  @ParameterizedTest
  @EnumSource(Course.class)
  @DisplayName("Открытие страницы курса по клику на карточку курса")
  void checkCoursePageIsOpenedByClickOnCourseCard(Course courseName) {
    catalogPage
        .open();
    courseCard
        .clickCard(courseName.getCourseName());
    coursePage
        .checkTittle(courseName.getCourseName());
  }

  @Test
  @DisplayName("Открытие страниц курсов с самыми ранними и поздними датами старта")
  void checkCourseCardNameForEarliestAndLatestCourses() {
    catalogPage
        .open();
    coursePage
        .checkCoursesTittlesAndStartDates(courseCard.findEarliestAndLatestCourses());
  }

  @ParameterizedTest
  @MethodSource("org.example.constants.EducationCategory#randomEducationCategoryProvider")
  @DisplayName("Открытие рандомного раздела каталога курсов")
  void clickRandomCoursesCategoryAndCheckAppliedFilter(String categoryName) {
    mainPage
        .open()
        .clickCourseCategory(categoryName);
    catalogPage
        .checkCategoryFilterIsApplied(categoryName);
  }
}
