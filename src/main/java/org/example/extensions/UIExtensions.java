package org.example.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.factory.WebDriverFactory;
import org.example.modules.ComponentGuiceModule;
import org.example.modules.PageGuiceModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class UIExtensions implements BeforeEachCallback, AfterEachCallback {

  private WebDriver driver;
  private Injector injector;

  @Override
  public void afterEach(ExtensionContext context) {
    if (driver != null) {
      driver.quit();
    }
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    driver = new WebDriverFactory().getDriver();
    injector = Guice.createInjector(new PageGuiceModule(driver), new ComponentGuiceModule(driver));
    injector.injectMembers(context.getTestInstance().orElseThrow());
  }
}
