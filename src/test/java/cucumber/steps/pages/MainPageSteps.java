package cucumber.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import org.example.pages.MainPage;

public class MainPageSteps {

  @Inject
  private MainPage mainPage;

  @И("Открыть главную страницу")
  public void openMainPage() {
    mainPage.open();
  }

  @И("^Открыть категорию курсов (.*)")
  public void openCourseCategory(String courseCategory) {
    mainPage.clickCourseCategory(courseCategory);
  }
}
