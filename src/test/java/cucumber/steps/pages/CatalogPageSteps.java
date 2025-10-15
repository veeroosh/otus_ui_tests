package cucumber.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import org.example.pages.CatalogPage;

public class CatalogPageSteps {

  @Inject
  private CatalogPage catalogPage;

  @И("Открыть каталог курсов")
  public void openCatalogPage() {
    catalogPage.open();
  }

  @И("^Поиск курса (.*)")
  public void findCourse(String categoryName) {
    catalogPage.findCourse(categoryName);
  }
}
