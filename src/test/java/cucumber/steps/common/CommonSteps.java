package cucumber.steps.common;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import org.example.constants.Browser;
import org.example.factory.WebDriverFactory;
import org.example.scoped.GuiceScoped;

public class CommonSteps {

  @Inject
  private GuiceScoped guiceScoped;

  @И("Я открываю браузер chrome")
  public void chooseChromeBrowser() {
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    guiceScoped.driver = webDriverFactory.getDriver(Browser.CHROME);
  }

  @И("Я открываю браузер firefox")
  public void chooseFirefoxBrowser() {
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    guiceScoped.driver = webDriverFactory.getDriver(Browser.FIREFOX);
  }
}
