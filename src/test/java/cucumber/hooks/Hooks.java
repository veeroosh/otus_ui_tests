package cucumber.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.constants.Browser;
import org.example.factory.WebDriverFactory;
import org.example.scoped.GuiceScoped;

public class Hooks {

  @Inject
  private GuiceScoped guiceScoped;

  @Before
  public void init() {
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    guiceScoped.driver = webDriverFactory.getDriver(Browser.CHROME);
  }

  @After
  public void close() {
    if (guiceScoped.driver != null)
      guiceScoped.driver.quit();
  }
}
